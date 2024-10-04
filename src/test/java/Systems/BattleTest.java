package Systems;

import Combatants.Player;
import Combatants.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    private Player player;
    private Enemy enemy;
    private Battle battle;

    @BeforeEach
    void setUp() {
        player = new Player("Hero", 100, 20, 10, 5); // name, maxHealth, strength, defence, intelligence
        enemy = new Enemy("Goblin", 50, 15, 8, 2, 1, 10); // name, maxHealth, strength, defence, intelligence, level, expValue
        battle = new Battle(player, enemy);
    }

    @Test
    void testPlayerTurn() {
        battle.battleAction(1, player, enemy); // Player attacks enemy
        assertTrue(enemy.getHealth() < enemy.getMaxHealth(), "Enemy should have taken damage from player's attack.");
    }

    @Test
    void testEnemyTurn() {
        battle.enemyTurn();
        assertTrue(player.getHealth() < player.getMaxHealth(), "Player should have taken damage from enemy's attack.");
    }

    @Test
    void testPlayerWinsBattle() {
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        enemy.setHealth(1);
        battle.battleController();

        assertTrue(player.isAlive(), "Player should be alive after the battle.");
        assertFalse(enemy.isAlive(), "Enemy should be dead after the battle.");
    }

    @Test
    void testEnemyWinsBattle() {
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        player.setHealth(1);
        boolean result = battle.battleController();

        assertFalse(result, "Player should have lost the battle.");
        assertTrue(enemy.isAlive(), "Enemy should be alive.");
        assertFalse(player.isAlive(), "Player should be dead.");
    }

    @Test
    void testBattleActionAttack() {
        boolean successful = battle.attack(player, enemy, false); // Regular attack
        assertTrue(successful, "Attack should be successful.");
        assertTrue(enemy.getHealth() < enemy.getMaxHealth(), "Enemy should take damage from player's attack.");
    }

    @Test
    void testBattleActionSpecial() {
        boolean successful = battle.attack(player, enemy, true); // Special attack
        assertTrue(successful, "Special attack should be successful.");
        assertTrue(enemy.getHealth() < enemy.getMaxHealth(), "Enemy should take damage from player's special attack.");
    }

    @Test
    void testBattleActionDefend() {
        boolean successful = battle.defend(player);
        assertTrue(successful, "Defend action should be successful.");
        assertTrue(player.isDefending(), "Player should be in defending state.");
    }

    @Test
    void testBattleActionHeal() {
        player.setHealth(50); // Lower player's health
        boolean successful = battle.heal(player);
        assertTrue(successful, "Heal action should be successful.");
        assertTrue(player.getHealth() > 50, "Player should have gained health from healing.");
    }

    @Test
    void testEndBattle() {
        boolean battleResult = battle.endBattle(player);
        assertTrue(battleResult, "Player should be declared the winner.");
    }
}
