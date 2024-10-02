package Systems;

import Enemies.Enemy;
import Misc.Combatant;
import Player.Player;

import java.util.Scanner;

public class Battle {
    // instance variables
    private final Player player;
    private final Enemy enemy;
    private final Scanner scanner;

    // constructors
    public Battle(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
    }

    // battle controller
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

        battleAction(choice, player, enemy);
    }

    private void enemyTurn() {
        // reset player attack state
        enemy.setDefending(false);

        // display action menu for player
        messageDelay(500);
        System.out.println();
        System.out.print("Enemy Turn: ");
        System.out.print(enemy.getName() + " (HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth() + ")\n");
        messageDelay(500);

        int choice = enemy.chooseAction();
        battleAction(choice, enemy, player);
    }

    private void battleAction(int action, Combatant actor, Combatant target) {
        switch (action) {
            case 1:
                attack(actor, target, false);
                break;
            case 2:
                defend(actor);
                break;
            case 3:
                attack(actor, target, true);
                break;
            case 4:
                heal(actor);
                break;
        }
    }

    // battle actions
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

    private void defend(Combatant actor) {
        actor.defend();
        System.out.print("Defending");
        messageBreak();
        System.out.println(actor.getName() + " defended!");
    }

    private void heal(Combatant actor) {
        System.out.print("Healing");
        messageBreak();
        System.out.println(actor.getName() + " healed!");
    }


    private void endBattle(Combatant combatant) {
        messageDelay(500);
        System.out.println();
        System.out.println(combatant.getName() + " has won! Battle has ended.");
    }

    // helper methods
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


}

