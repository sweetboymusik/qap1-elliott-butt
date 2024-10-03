package Systems;

import Combatants.Combatant;
import Combatants.Enemy;
import Combatants.Player;

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

    public boolean battleController() {
        int turn = 1;
        Combatant winner;

        System.out.println();
        System.out.println("-------- Battle --------");
        System.out.println(player.getName() + " (Lv. " + player.getLevel() + ") vs. " + enemy.getName() + " (Lv. " + enemy.getLevel() + ")");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println();
            System.out.println("--- Turn " + turn + " ---");


            if (player.isAlive()) {
                playerTurn();
            }

            if (enemy.isAlive()) {
                enemyTurn();
            }

            turn++;
        }

        winner = player.isAlive() ? player : enemy;
        return endBattle(winner);
    }

    private void playerTurn() {
        // reset player defending state and cooldowns
        player.setDefending(false);
        player.cooldowns();

        // display action menu for player
        MessageUtils.messageDelay(500);
        System.out.println();
        System.out.print("Player Turn: ");
        System.out.print(player.getName() + " (HP: " + player.getHealth() + "/" + player.getMaxHealth() + ")\n");
        System.out.println("1. Attack - 2. Defend - 3. Special - 4. Heal ");

        while (true) {
            System.out.print("Enter your option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            boolean successfulAction = battleAction(choice, player, enemy);

            if (successfulAction) {
                break;
            }
        }
    }

    private void enemyTurn() {
        // reset enemy attack state and cooldowns
        enemy.setDefending(false);
        enemy.cooldowns();

        // display enemy info
        MessageUtils.messageDelay(500);
        System.out.println();
        System.out.print("Enemy Turn: ");
        System.out.print(enemy.getName() + " (HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth() + ")\n");
        MessageUtils.messageDelay(500);

        int choice = enemy.chooseAction();
        battleAction(choice, enemy, player);
    }

    private boolean battleAction(int action, Combatant actor, Combatant target) {
        return switch (action) {
            case 1 -> attack(actor, target, false);
            case 2 -> defend(actor);
            case 3 -> attack(actor, target, true);
            case 4 -> heal(actor);
            default -> false;
        };
    }

    // battle actions
    private boolean attack(Combatant attacker, Combatant defender, boolean special) {
        int damageTaken = 0;

        if (special) {
            boolean canSpecial = attacker.special();

            if (canSpecial) {
                damageTaken = defender.takeDamage(attacker.getStrength() * 2);
                System.out.print("Special");
            } else {
                System.out.println("Special cannot be used for " + attacker.getSpecialCooldown() + " more turn(s).");
                return false;
            }
        } else {
            System.out.print("Attacking");
            damageTaken = defender.takeDamage(attacker.getStrength());
        }

        MessageUtils.messageBreak();
        System.out.println(defender.getName() + " took " + damageTaken + " damage from " + attacker.getName() + "!");
        return true;
    }

    private boolean defend(Combatant actor) {
        actor.defend();
        System.out.print("Defending");
        MessageUtils.messageBreak();
        System.out.println(actor.getName() + " defended!");
        return true;
    }

    private boolean heal(Combatant actor) {
        int canHeal = actor.heal();

        switch (canHeal) {
            case -1:
                System.out.println("Already at full health!");
                return false;
            case 0:
                System.out.println("Heal cannot be used for " + actor.getHealCooldown() + " more turn(s).");
                return false;
            case 1:
                System.out.print("Healing");
                MessageUtils.messageBreak();
                System.out.println(actor.getName() + " recovered " + actor.getIntelligence() + " HP!");
                return true;
            default:
                return false;
        }
    }

    private boolean endBattle(Combatant combatant) {
        MessageUtils.messageDelay(500);

        System.out.println();
        System.out.println(combatant.getName() + " has won!");

        if (combatant == player) {
            int levelsToGain = player.gainExp(enemy.getExpValue());

            System.out.println(player.getName() + " gained " + enemy.getExpValue() + " experience!");

            MessageUtils.displayLevelUpMessage(levelsToGain, player);

            return true;
        } else {
            return false;
        }
    }
}

