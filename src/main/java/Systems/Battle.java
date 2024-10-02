package Systems;

import Enemies.Enemy;
import Player.Player;

import java.util.Scanner;

public class Battle {
    private final Player player;
    private Enemy enemy;
    private int turnCounter;
    private Scanner scanner;

    public Battle(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.turnCounter = 1;
        this.scanner = new Scanner(System.in);
    }

    public void battleController(){}

    public void playerTurn(){
        System.out.println("Player Turn: ");
        System.out.println("1. Attack - 2. Defend ");
        System.out.print("Enter your option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();


    }

    public void enemyTurn(){}
}
