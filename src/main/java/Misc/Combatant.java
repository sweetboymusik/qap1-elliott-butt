package Misc;

public interface Combatant {
    String getName();

    // getters and setters
    int getStrength();
    int getHealth();

    // methods
    int takeDamage(int damage);
    void defend();
}
