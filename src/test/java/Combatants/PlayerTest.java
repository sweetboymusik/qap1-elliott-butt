package Combatants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Hero", 100, 10, 10, 10);
    }

    @Test
    void testInitialValues() {
        assertEquals(1, player.getLevel(), "Initial level should be 1");
        assertEquals(0, player.getExp(), "Initial experience should be 0");
        assertEquals(2, player.getExpToNextLevel(), "Initial exp to next level should be 2");
        assertEquals(100, player.getMaxHealth(), "Initial max health should be 100");
    }

    @Test
    void testGainExpWithoutLevelUp() {
        player.gainExp(1);
        assertEquals(1, player.getExp(), "Experience should increase to 1");
        assertEquals(1, player.getLevel(), "Level should remain 1");

        player.gainExp(4);
        assertEquals(2, player.getExp(), "Experience should increase to 1");
        assertEquals(1, player.getLevel(), "Level should remain 1");

    }

    @Test
    void testGainExpWithLevelUp() {
        int levelsGained = player.gainExp(3);
        assertEquals(1, levelsGained, "Player should gain 1 level");
        assertTrue(levelsGained >= 1, "At least one level should be gained");
        assertTrue(player.getExp() < player.getExpToNextLevel(), "Remaining exp should be less than exp to next level");
    }

    @Test
    void testLevelUp() {
        int[] statIncrease = player.levelUp(3);

        assertEquals(4, player.getLevel(), "Player should be at level 4 after leveling up 3 times");

        assertTrue(statIncrease[0] > 0, "Max health should increase");
        assertTrue(statIncrease[1] > 0, "Strength should increase");
        assertTrue(statIncrease[2] > 0, "Defence should increase");
        assertTrue(statIncrease[3] > 0, "Intelligence should increase");
    }

    @Test
    void testRollStat() {
        int rolledStat = player.rollStat();
        assertTrue(rolledStat >= 1 && rolledStat <= player.getLevel(), "Roll stat should return a value between 1 and player's level");
    }
}
