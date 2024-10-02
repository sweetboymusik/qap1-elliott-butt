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

    // constructors
    public Enemy(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.alive = true;
        this.defending = false;
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

    // methods
    public abstract int getStrength();
    public abstract int getDefence();

    @Override
    public int takeDamage(int damage) {
        int finalDamage = damage;

        if (defending) {
            finalDamage = damage - getDefence();
        }

        this.health -=  finalDamage;

        if (health <= 0) {
            this.health = 0;
            this.alive = false;
        }

        return finalDamage;
    }
}
