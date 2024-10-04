package Combatants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new Enemy("Goblin", 50, 10, 5, 3, 1, 10);
    }

    @Test
    void testInitialValues() {
        assertEquals(1, enemy.getLevel(), "Initial level should be 1");
        assertEquals(10, enemy.getExpValue(), "Initial exp value should be 10");
        assertEquals(50, enemy.getMaxHealth(), "Initial max health should be 50");
        assertEquals(50, enemy.getHealth(), "Initial health should be full");
    }

    @Test
    void testChooseActionAttack() {
        enemy.setHealth(40);
        int action = enemy.chooseAction();
        assertTrue(action == 1 || action == 2 || action == 3, "Action should be attack, defend, or special");
    }

    @Test
    void testChooseActionHeal() {
        enemy.setHealth(10);
        enemy.setHealCooldown(0);
        int action = enemy.chooseAction();
        assertEquals(4, action, "Action should be heal when health is low and heal cooldown is 0");
    }

    @Test
    void testSpecialCooldownPreventsSpecial() {
        enemy.setHealth(40);
        enemy.setSpecialCooldown(1);
        int action = enemy.chooseAction();
        assertNotEquals(3, action, "Action should not be special since it's on cooldown");
    }

    @Test
    void testRandomnessInAction() {
        boolean hasAttacked = false;
        boolean hasDefended = false;
        boolean hasUsedSpecial = false;

        for (int i = 0; i < 100; i++) {
            int action = enemy.chooseAction();
            if (action == 1) hasAttacked = true;
            if (action == 2) hasDefended = true;
            if (action == 3) hasUsedSpecial = true;
        }

        assertTrue(hasAttacked, "Enemy should attack sometimes");
        assertTrue(hasDefended, "Enemy should defend sometimes");
        assertTrue(hasUsedSpecial, "Enemy should use special sometimes (when not on cooldown)");
    }
}
