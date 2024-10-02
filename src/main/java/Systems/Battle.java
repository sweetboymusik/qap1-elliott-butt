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
        int turn = 1;
        Combatant winner;

        System.out.println("Fight!");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println();
            System.out.println("-------- Turn " + turn + " --------");


            if (player.isAlive()) {
                playerTurn();
            }

            if (enemy.isAlive()) {
                enemyTurn();
            }

            turn++;
        }

        winner = player.isAlive() ? player : enemy;
        endBattle(winner);
    }

    private void playerTurn() {
        // reset player defending state
        player.setDefending(false);

        // display action menu for player
        messageDelay(500);
        System.out.println();
        System.out.print("Player Turn: ");
        System.out.print(player.getName() + " (HP: " + player.getHealth() + "/" + player.getMaxHealth() + ")\n");
        System.out.println("1. Attack - 2. Defend - 3. Special - 4. Heal ");
        System.out.print("Enter your option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                attack(player, enemy, false);
                break;
            case 2:
                defend(player);
                break;
            case 3:
                attack(player, enemy, true);
                break;
            case 4:
                player.heal();
                break;
        }
    }

    private void enemyTurn() {
        enemy.setDefending(false);

        // display action menu for player
        messageDelay(500);
        System.out.println();
        System.out.print("Enemy Turn: ");
        System.out.print(enemy.getName() + " (HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth() + ")\n");
        messageDelay(500);

        attack(enemy, player, false);

    }

    private void attack(Combatant attacker, Combatant defender, boolean special) {
        int damageTaken = 0;

        if (special) {
            damageTaken = defender.takeDamage(attacker.getStrength() * 2);
        } else {
            damageTaken = defender.takeDamage(attacker.getStrength());
        }

        System.out.print("Attacking");
        messageBreak();
        System.out.println(defender.getName() + " took " + damageTaken + " damage from " + attacker.getName() + "!");
    }

    private void defend(Combatant combatant) {
        combatant.defend();
        System.out.println(combatant.getName() + " defended!");
        System.out.println();
    }


    private void messageDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void messageBreak() {
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            messageDelay(300);
        }

        System.out.print(" ");
    }

    private void endBattle(Combatant combatant) {
        messageDelay(500);
        System.out.println();
        System.out.println(combatant.getName() + " has won! Battle has ended.");
    }
}

