import Enemies.NormalEnemy;
import Player.Player;
import Systems.Battle;

public class Game {
    public static void main(String[] args) {
        // battle test code
        Player player = new Player("Elliott", 10, 2, 2, 2);
        NormalEnemy enemy = new NormalEnemy("Rat", 6);

        Battle battle = new Battle(player, enemy);
        battle.battleController();
    }
}
