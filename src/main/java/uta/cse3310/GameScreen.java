package uta.cse3310;

import java.util.Random;
import java.util.*;
public class GameScreen {

    public String[] Msg;
    public int GameId;
    public boolean isOpen;

    private String[] numOfUsers = {"User 1", "User 2", "User 3", "User 4"};
    private static String gridString = "GRID:";
    private static String chat = "Chat:";

    private final static int sizeOfArray = 50;



    static WordGrid wordGrid = new WordGrid(sizeOfArray);

    public static void StartGame() 
    {
        System.out.println(gridString);
        char[][] grid = wordGrid.generateRandomGrid(sizeOfArray, sizeOfArray);
        wordGrid.printGrid(grid);
        // for (int i = 1; i <= 4; i++ )
        // {
        //     String s;
        //     s =num++);
        // }
        return;
    }


    public void CheckWord() {
        return;
    }

    public void CheckVertical() {
        return;
    }

    public void CheckHorizontal() {
        return;
    }

    public void CheckDiagonal() {
        return;
    }

    public void CheckReverse() {
        return;
    }

    public void Tick() {
        return;
    }

    public static void main(String[] args) 
    {
        Timer();
        StartGame();
    }

    private static void Timer() {
        Timer timer = new Timer();
        int delay = 1000; // 1 second
        int period = 1000; // 1 second

        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 30; // Should be set to 30. Se

            public void run() {
                System.out.println("Seconds left: " + counter);
                counter--;

                if (counter < 0) {
                    System.out.println("Time's up!");
                    timer.cancel(); // Terminate the timer when countdown is complete
                }
            }
        }, delay, period);
    }
}


