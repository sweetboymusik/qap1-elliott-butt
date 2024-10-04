package Systems;

import Combatants.Enemy;
import Combatants.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Random;

public class TravelTest {
    private Travel travel;

    @BeforeEach
    public void setUp() {
        Player player = new Player("Hero", 100, 10, 5, 3); // Assume appropriate constructor
        travel = new Travel(player, new Random(1));
    }

    @Test
    void generateBattle() {
        Battle battle = travel.generateBattle();
        assertNotNull(battle, "Battle should not be null");
    }

    @Test
    void generatePaths() {
        Event[] path = travel.generatePaths();
        assertTrue(path.length > 0, "Paths should not be empty");
    }

    @Test
    void spawnEnemy() {
        Enemy enemy = travel.spawnEnemy();
        assertEquals(1, enemy.getMaxHealth(), "Max health should be 1");

        enemy = travel.spawnEnemy();
        assertNotEquals(2, enemy.getMaxHealth(), "Max health should NOT be 1");
    }

    @Test
    void spawnPath() {
        Event event = travel.spawnPath();
        assertEquals(Event.TRAP, event, "Event should be trap.");

        event = travel.spawnPath();
        assertEquals(Event.BATTLE, event, "Event should be battle.");

        event = travel.spawnPath();
        assertEquals(Event.HEAL, event, "Event should be heal.");
    }
}
