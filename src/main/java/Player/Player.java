package Player;

import Enemies.Enemy;
import Misc.Combatant;

public class Player implements Combatant {
    // instance variables
    private String name;
    private final int maxHealth;
    private int health;
    private int strength;
    private int defence;
    private int intelligence;
    private boolean alive;
    private boolean defending;

    // constructors


    public Player(String name, int maxHealth, int strength, int defence, int intelligence) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.defence = defence;
        this.intelligence = intelligence;
        this.alive = true;
        this.defending = false;
    }

    // getters and setters
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public boolean isDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    // methods
    public void attack(Enemy enemy) {
    }

    public void special(Enemy enemy){}

    public void defend(){
        this.defending = true;
    }

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

    public void heal() {
        int newHealth = health + intelligence;

        if (newHealth > maxHealth)
            newHealth = maxHealth;

        setHealth(newHealth);
    }
}
