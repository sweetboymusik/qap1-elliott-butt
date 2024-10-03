
import Combatants.Enemy;
import Combatants.Player;
import Systems.Battle;

public class Game {
    public static void main(String[] args) {
        // battle test code
        Player player = new Player("Elliott", 10, 2, 2, 2);
        Enemy enemy = new Enemy("Rat", 10, 2, 2, 2);

        Battle battle = new Battle(player, enemy);
        battle.battleController();
    }
}
