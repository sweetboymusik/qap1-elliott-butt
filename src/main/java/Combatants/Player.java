package Combatants;

import java.util.Random;

public class Player extends Combatant {
    // instance variables
    private int level;
    private int exp;
    private int expToNextLevel;

    // constructor
    public Player(String name, int maxHealth, int strength, int defence, int intelligence) {
        super(name, maxHealth, strength, defence, intelligence);
        this.level = 1;
        this.exp = 0;
        this.expToNextLevel = 2;
    }

    // getters and setters
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    // methods

    public int gainExp(int exp) {
        int tempExp = this.exp + exp;
        int targetExp = level + 2;
        int levelsToGain = 0;

        System.out.println("Temp exp: " + tempExp);
        System.out.println("Target exp: " + targetExp);

        if (tempExp >= targetExp) {
            while (tempExp >= targetExp) {
                this.exp = tempExp - targetExp;
                tempExp = this.exp;
                targetExp++;
                levelsToGain++;
            }
            return levelsToGain;
        } else {
            this.exp = tempExp;
        }

        return levelsToGain;
    }

    public int[] levelUp(int levelsToGain) {
        int[] statUp = new int[4];

        for (int i = 0; i < levelsToGain; i++) {
            this.level++;

            statUp[0] += rollStat();
            statUp[1] += rollStat();
            statUp[2] += rollStat();
            statUp[3] += rollStat();
        }

        setMaxHealth(getMaxHealth() + statUp[0]);
        setHealth(getHealth() + statUp[0]);
        setStrength(getStrength() + statUp[1]);
        setDefence(getDefence() + statUp[2]);
        setIntelligence(getIntelligence() + statUp[3]);

        return statUp;
    }

    public int rollStat(){
        Random rand = new Random();
        return rand.nextInt(level) + 1;
    }
}
