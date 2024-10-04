import Combatants.Enemy;
import Combatants.Player;
import Systems.Battle;
import Systems.Travel;

import java.util.Random;

public class Game {
    public static void main(String[] args) {
        // battle test code
        Player player = new Player("Hero", 10, 2, 2, 2);
        Enemy enemy = new Enemy("Rat", 2, 1, 1, 1, 1, 3);

        Travel travel = new Travel(player, new Random(1));
        travel.choosePath();
    }
}
