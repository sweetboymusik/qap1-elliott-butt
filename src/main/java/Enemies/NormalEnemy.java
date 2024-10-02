package Enemies;

public class NormalEnemy extends Enemy{
    // instance variables
    private int strength;
    private int defence;

    // constructors
    public NormalEnemy(String name, int maxHealth) {
        super(name, maxHealth);
        this.strength = 2;
        this.defence = 1;
    }

    // getters and setters
    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

}
