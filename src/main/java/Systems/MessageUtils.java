package Systems;

import Combatants.Player;

public class MessageUtils {
    public static void messageDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void messageBreak() {
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            messageDelay(300);
        }

        System.out.print(" ");
    }

    static void displayLevelUpMessage(int levelsToGain, Player player) {
        if (levelsToGain > 0) {
            int[] statsGained = player.levelUp(levelsToGain);
            System.out.print("Leveling up");
            MessageUtils.messageBreak();
            System.out.print("\n");
            System.out.println(player.getName() + " gained " + statsGained[0] + " max health!");
            MessageUtils.messageDelay(250);
            System.out.println(player.getName() + " gained " + statsGained[1] + " strength!");
            MessageUtils.messageDelay(250);
            System.out.println(player.getName() + " gained " + statsGained[2] + " defence!");
            MessageUtils.messageDelay(250);
            System.out.println(player.getName() + " gained " + statsGained[3] + " intelligence!");
        }
    }
}
