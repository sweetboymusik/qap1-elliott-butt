package Misc;

public interface Combatant {
    String getName();
    int getStrength();
    int getHealth();
    int takeDamage(int damage);
}
