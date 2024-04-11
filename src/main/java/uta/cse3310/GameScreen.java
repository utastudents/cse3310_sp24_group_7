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

    public static char[][] generateRandomGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = (char) (rand.nextInt(26) + 'a'); // Generating random lowercase letters
            }
        }
        return grid;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) // populates the columns
            {
                System.out.print(grid[i][j] + " "); // print an array
            }
            System.out.println(); // creates a new row
        }
    }

    public static void StartGame() 
    {
        System.out.println(gridString);
        char[][] grid = generateRandomGrid(sizeOfArray, sizeOfArray);
        printGrid(grid);
        return;
    }

    public void Update() {
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


