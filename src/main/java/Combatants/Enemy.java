package Combatants;

import java.util.Random;

public class Enemy extends Combatant {
    private int level;
    private int expValue;

    public Enemy(String name, int maxHealth, int strength, int defence, int intelligence, int level, int expValue) {
        super(name, maxHealth, strength, defence, intelligence);
        this.level = level;
        this.expValue = expValue;
    }

    // getters and setters
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExpValue() {
        return expValue;
    }

    public void setExpValue(int expValue) {
        this.expValue = expValue;
    }

    public int chooseAction() {
        Random rand = new Random();

        // heal if below 1/3 health
        if (getHealth() <= (Math.ceil((double) getMaxHealth() / 3)) && getHealCooldown() == 0) {
            return 4;// heal
        }

        double choice1 = rand.nextInt(11);

        // if random number is between 0-3
        if (choice1 <= 2) {
            double choice2 = rand.nextInt(11);

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
