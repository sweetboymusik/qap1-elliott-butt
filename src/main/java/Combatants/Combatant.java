package Combatants;

public abstract class Combatant {
    // info
    private String name;
    private int maxHealth;
    private final int specialMaxCooldown;
    private final int healMaxCooldown;

    // stats
    private int health;
    private int strength;
    private int defence;
    private int intelligence;

    // state
    private boolean alive;
    private boolean defending;
    private int specialCooldown;
    private int healCooldown;

    // constructor
    public Combatant(String name, int maxHealth, int strength, int defence, int intelligence) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.specialMaxCooldown = 4;
        this.healMaxCooldown = 4;

        this.health = maxHealth;
        this.strength = strength;
        this.defence = defence;
        this.intelligence = intelligence;

        this.alive = true;
        this.defending = false;
        this.specialCooldown = 0;
        this.healCooldown = 0;
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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getSpecialMaxCooldown() {
        return specialMaxCooldown;
    }

    public int getHealMaxCooldown() {
        return healMaxCooldown;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public int getSpecialCooldown() {
        return specialCooldown;
    }

    public void setSpecialCooldown(int specialCooldown) {
        this.specialCooldown = specialCooldown;

        if (this.specialCooldown <= 0) {
            this.specialCooldown = 0;
        }
    }

    public int getHealCooldown() {
        return healCooldown;
    }

    public void setHealCooldown(int healCooldown) {
        this.healCooldown = healCooldown;

        if (this.healCooldown <= 0) {
            this.healCooldown = 0;
        }
    }

    // methods
    public void defend() {
        this.defending = true;
    }

    public boolean special() {
        if (specialCooldown == 0) {
            specialCooldown = specialMaxCooldown;
            return true;
        } else {
            return false;
        }
    }

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

    public int heal() {
        if (healCooldown == 0) {
            if (health == maxHealth) {
                return -1;
            }

            healCooldown = healMaxCooldown;

            int newHealth = health + intelligence;

            if (newHealth > maxHealth)
                newHealth = maxHealth;

            setHealth(newHealth);

            return 1;
        } else {
            return 0;
        }
    }

    public void cooldowns() {
        setSpecialCooldown(specialCooldown - 1);
        setHealCooldown(healCooldown - 1);
    }
}
