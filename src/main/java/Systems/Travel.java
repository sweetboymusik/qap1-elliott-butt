package Systems;

import Combatants.Enemy;
import Combatants.Player;

import java.util.Random;
import java.util.Scanner;

public class Travel {
    // instance variables
    private final Player player;
    private int floor;
    private final Random random;

    // constructors
    public Travel(Player player) {
        this.player = player;
        this.floor = 1;
        this.random = new Random();
    }

    public Travel(Player player, Random random) {
        this.player = player;
        this.floor = 1;
        this.random = random;
    }

    // getters and setters
    public Player getPlayer() {
        return player;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    // methods
    public void choosePath() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------- Travel --------");
        System.out.println("Floor: " + this.floor);
        System.out.println();

        Event[] paths = generatePaths();
        System.out.println("Before you are " + paths.length + " paths.");

        while (true) {
            System.out.print("Please choose a path, wary wanderer (1-" + paths.length + "): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > paths.length || choice < 1) {
                System.out.println("That is not a valid path choice, wanderer!");
            } else {
                floor++;
                System.out.print("Travelling");
                MessageUtils.messageBreak();

                switch (paths[choice - 1]) {
                    case BATTLE:
                        battleEvent();
                        break;
                    case HEAL:
                        healEvent();
                        break;
                    case TRAP:
                        trapEvent();
                        break;
                    case EXP:
                        expEvent();
                        break;
                }
                break;
            }
        }
    }

    public Battle generateBattle() {
        Enemy enemy = spawnEnemy();
        return new Battle(player, enemy);
    }

    public Event[] generatePaths() {
        int pathsToGenerate = random.nextInt(2) + 2;

        Event[] paths = new Event[pathsToGenerate];

        for (int i = 0; i < pathsToGenerate; i++) {
            paths[i] = spawnPath();
        }

        return paths;
    }

    public Enemy spawnEnemy() {
        int maxHealth = random.nextInt(player.getLevel() * 3) + 1;
        int strength = random.nextInt(player.getLevel()) + 1;
        int defence = random.nextInt(player.getLevel()) + 1;
        int intelligence = random.nextInt(player.getLevel()) + 1;
        int level = random.nextInt(player.getLevel()) + 1;
        int exp = random.nextInt(player.getLevel()) + 1;

        return new Enemy("Baddie", maxHealth, strength, defence, intelligence, level, exp);
    }

    public Event spawnPath() {
        Event path = Event.BATTLE;
        int choice = random.nextInt(4) + 1;

        path = switch (choice) {
            case 1 -> Event.BATTLE;
            case 2 -> Event.HEAL;
            case 3 -> Event.TRAP;
            case 4 -> Event.EXP;
            default -> path;
        };

        return path;
    }

    public void battleEvent() {
        System.out.println("an enemy approaches!");
        MessageUtils.messageDelay(500);

        Battle battle = generateBattle();

        if (battle.battleController()) {
            choosePath();
        } else {
            gameOver();
        }
    }

    public void healEvent() {
        System.out.println("you feel a calming presence!");

        int health = random.nextInt(player.getLevel()) + 2;
        player.setHealth(player.getHealth() + health);

        System.out.println();
        System.out.println("-------- Event --------");
        System.out.println("You are bestowed a blessing from the presence.");
        System.out.println("You healed " + health + " health!");
        System.out.println();

        choosePath();
    }

    public void trapEvent() {
        System.out.println("it's a trap!");

        int damage = random.nextInt(player.getLevel()) + 2;
        player.takeDamage(damage);


        System.out.println();
        System.out.println("-------- Event --------");
        System.out.println("You are struck by some spikes in the floor.");
        System.out.println("You take " + damage + " damage.");
        System.out.println();

        MessageUtils.messageDelay(1000);

        if (!player.isAlive()) {
            gameOver();
        } else {
            choosePath();
        }
    }

    public void expEvent() {
        System.out.println("you see something interesting!");

        int exp = random.nextInt(player.getLevel()) + 1;

        System.out.println();
        System.out.println("-------- Event --------");
        System.out.println("You investigate the curio.");
        System.out.println("You gain " + exp + " experience!");
        System.out.println();

        int levelsToGain = player.gainExp(exp);
        MessageUtils.displayLevelUpMessage(levelsToGain, player);
        System.out.println();

        choosePath();
    }

    public void gameOver() {
        System.out.println();
        System.out.println("-------- Game Over --------");
        System.out.println("You have died.");
        System.out.println("Game over!");
    }
}
