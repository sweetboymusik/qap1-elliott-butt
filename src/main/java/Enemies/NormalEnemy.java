package Enemies;

public class NormalEnemy extends Enemy{
    // instance variables
    private int strength;

    // constructors
    public NormalEnemy(String name, int maxHealth) {
        super(name, maxHealth);
        this.strength = 2;
    }

    // getters and setters
    @Override
    public int getStrength() {
        return strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

}
