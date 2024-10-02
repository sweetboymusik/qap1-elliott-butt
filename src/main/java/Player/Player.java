package Player;

import Enemies.Enemy;

public class Player {
    // instance variables
    private final int maxHealth;
    private int health;
    private int strength;

    // constructors
    public Player(int maxHealth, int strength) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
    }

    // getters and setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    // methods
    public void attack(Enemy enemy) {
    }

    public void takeDamage(int amount) {
        int newHealth = health - amount;

        if (newHealth < 0)
            newHealth = 0;

        setHealth(newHealth);
    }

    public void heal(int amount) {
        int newHealth = health + amount;

        if (newHealth > maxHealth)
            newHealth = maxHealth;

        setHealth(newHealth);
    }
}
