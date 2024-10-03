package Combatants;

public class Enemy extends Combatant{
    public Enemy(String name, int maxHealth, int strength, int defence, int intelligence) {
        super(name, maxHealth, strength, defence, intelligence);
    }

    public int chooseAction() {
        // heal if below 1/3 health
        if (getHealth() <= (Math.ceil((double) getMaxHealth() / 3)) && getHealCooldown() == 0) {
            return 4;// heal
        }

        double choice1 = Math.random() * 10;

        // if random number is between 0-3
        if (choice1 <= 2) {
            double choice2 = Math.random() * 10;

            // if random number is between 0-5 and special is not on cooldown
            if (choice2 <= 8 && getSpecialCooldown() == 0) {
                return 3; // special
            } else {
                return 2; // defend
            }
        } else {
            return 1; // attack
        }
    }
}
