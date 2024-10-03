import Combatants.Enemy;
import Combatants.Player;
import Systems.Battle;

public class Game {
    public static void main(String[] args) {
        // battle test code
        Player player = new Player("Elliott", 10, 2, 2, 2);
        Enemy enemy = new Enemy("Rat", 2, 1, 1, 1, 3);


        Battle battle = new Battle(player, enemy);
        battle.battleController();
    }
}
