package Systems;

import Enemies.Enemy;
import Misc.Combatant;
import Player.Player;

import java.util.Scanner;

public class Battle {
    private final Player player;
    private final Enemy enemy;
    private final Scanner scanner;

    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
    }

    public void battleController() {
        while (true) {
            playerTurn();
            enemyTurn();
        }
    }

    public void playerTurn() {
        // reset player defending state
        player.setDefending(false);

        // display action menu for player
        System.out.println("Player Turn: ");
        System.out.println("1. Attack - 2. Defend - 3. Special - 4. Heal ");
        System.out.print("Enter your option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                attack(player, enemy, false);
                break;
            case 2:
                player.defend();
                break;
            case 3:
                attack(player, enemy, true);
                break;
            case 4:
                player.heal();
                break;
        }
    }

    public void attack(Combatant attacker, Combatant defender, boolean special) {
        int damageTaken = 0;

        if (special) {
            damageTaken = defender.takeDamage(attacker.getStrength() * 2);
        } else {
            damageTaken = defender.takeDamage(attacker.getStrength());
        }

        System.out.print(defender.getName() + " took " + damageTaken + " damage from" + attacker.getName());
        System.out.println("New defender health: " + defender.getHealth());
    }

    public void enemyTurn() {
    }
}
