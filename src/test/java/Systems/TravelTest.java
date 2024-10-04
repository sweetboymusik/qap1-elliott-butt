package Systems;

import Combatants.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TravelTest {
    private Player player;
    private Travel travel;

    @BeforeEach
    public void setUp() {
        player = new Player("Hero", 100, 10, 5, 3); // Assume appropriate constructor
        travel = new Travel(player);
    }

    @Test
    void testChoosePathBattleEvent() {
        // Simulate input for choosing a path that leads to a battle
        String simulatedInput = "1\n"; // Assuming there's a battle at index 1
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        travel.choosePath();

        assertTrue(player.getHealth() < 100, "Player's health should decrease after a battle.");
        assertEquals(2, travel.getFloor(), "Player should advance to the next floor.");
    }

    @Test
    void testChoosePathHealEvent() {
        // Simulate input for a heal event
        String simulatedInput = "1\n"; // Assuming there's a heal at index 1
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        travel.choosePath();

        assertTrue(player.getHealth() > 100, "Player's health should increase after a healing event.");
        assertEquals(2, travel.getFloor(), "Player should advance to the next floor.");
    }

    @Test
    void testChoosePathTrapEvent() {
        // Simulate input for a trap event
        String simulatedInput = "1\n"; // Assuming there's a trap at index 1
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Set initial health to a low value to ensure damage occurs
        player.setHealth(5);
        travel.choosePath();

        assertTrue(player.getHealth() < 5, "Player's health should decrease after a trap event.");
        assertFalse(player.isAlive(), "Player should be dead after the trap event.");
    }

    @Test
    void testChoosePathExpEvent() {
        // Simulate input for an experience event
        String simulatedInput = "1\n"; // Assuming there's an EXP event at index 1
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        travel.choosePath();

        assertTrue(player.getExp() > 0, "Player should gain experience from the event.");
        assertEquals(2, travel.getFloor(), "Player should advance to the next floor.");
    }

    // Additional assertions for console output can be added if needed
}

