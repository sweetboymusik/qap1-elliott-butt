package Enemies;

import Misc.Combatant;
import Player.Player;

public abstract class Enemy implements Combatant {
    // instance variables
    private String name;
    private final int maxHealth;
    private int health;

    private boolean alive;
    private boolean defending;

    private int specialCooldown;
    private int healCooldown;


    // constructors
    public Enemy(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;

        this.alive = true;
        this.defending = false;

        this.specialCooldown = 2;
        this.healCooldown = 3;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public abstract int getStrength();

    public abstract int getDefence();


    // methods
    @Override
    public int takeDamage(int damage) {
        int finalDamage = damage;

        if (defending) {
            finalDamage = damage - getDefence();
        }

        this.health -= finalDamage;

        if (health <= 0) {
            this.health = 0;
            this.alive = false;
        }

        return finalDamage;
    }

    @Override
    public void defend() {
        this.defending = true;
    }

    public int chooseAction() {
        // heal if below 1/3 health
        if (health <= (Math.ceil((double) maxHealth / 3)) && healCooldown == 0) {
            return 4;// heal
        }

        double choice1 = Math.random() * 10;

        // if random number is between 0-3
        if (choice1 <= 2) {
            double choice2 = Math.random() * 10;

            // if random number is between 0-5 and special is not on cooldown
            if (choice2 <= 8 && specialCooldown == 0) {
                return 3; // special
            } else {
                return 2; // defend
            }
        } else {
            return 1; // attack
        }
    }
}
