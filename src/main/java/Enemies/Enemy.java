package Enemies;

import Player.Player;

public abstract class Enemy {
    // instance variables
    private String name;
    private final int maxHealth;
    private int health;

    // constructors
    public Enemy(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
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

    public void attack(Player player) {
        int strength = getStrength();
        player.setHealth(strength);
    }

}
