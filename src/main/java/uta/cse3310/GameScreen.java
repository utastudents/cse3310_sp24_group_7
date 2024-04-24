package uta.cse3310;

import java.util.Random;
import java.util.*;
public class GameScreen {

    public String[] Msg;
    public int GameId;
    public boolean isOpen;
    public PlayerType Players;
    public PlayerType CurrentTurn;
    public PlayerType[] Button;

    private String[] numOfUsers = {"User 1", "User 2", "User 3", "User 4"};
    public int counter;
    public static boolean[] isUserActive;
    public static int index = 0;
    // public static boolean[] isUserActive = new boolean[numOfUsers.length];
    private static String gridString = "GRID:";
    private static String chat = "Chat:";

    private final static int sizeOfArray = 50;



    static WordGrid wordGrid = new WordGrid(656);
    GameScreen()
    {
        Button = new PlayerType[625];
        // initialize it
        for (int i = 0; i < Button.length; i++) {
            Button[i] = PlayerType.NOUSER;
        }

        Msg = new String[2];
        // chatBox = new ChatBox();
        Players = PlayerType.USER1;
        CurrentTurn = PlayerType.NOUSER;

        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";    
    }
    public static void StartGame() 
    {
        isUserActive[index] = true;
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
    public void TurnSystem()
    {
        int j;
        if (isUserActive[0] == true)
        {
            for (int i = 1; i < isUserActive.length; i++)
            {
                isUserActive[i] = false;
            }
        }
        else if (isUserActive[1] == true)
        {
            for (int i = 2; i < isUserActive.length; i++)
            {
                isUserActive[0] = false;
                isUserActive[i] = false;
            }
        }
        else if (isUserActive[2] = true)
        {
            for (int i = 2; i < isUserActive.length; i++)
            {
                isUserActive[0] = false;
                isUserActive[i] = false;
            }
        }
        else if (isUserActive[3] = true)
        {
            for (int i = 3; i < isUserActive.length; i++)
            {
                isUserActive[0] = false;
                isUserActive[1] = false;
                isUserActive[2] = false;
                isUserActive[i] = false;
            }
        }
    }


    public void CheckBoard() {
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



    public void Timer() {
        Timer timer = new Timer();
        int delay = 1000; // 1 second
        int period = 1000; // 1 second

        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 30; // Should be set to 30.

            public void run() {
                System.out.println("Seconds left: " + counter);
                counter--;

                if (counter < 0) {
                    System.out.println("Time's up!");
                    index++;

                    counter = 30;

                    // timer.cancel(); // Terminate the timer when countdown is complete
                }
            }
        }, delay, period);
    }
    private boolean CheckLine(int i, int j, int k, PlayerType player) {
        return player == Button[i] && player == Button[j] && player == Button[k]; 
    }

    private boolean CheckHorizontal(PlayerType player) {
        return CheckLine(0, 1, 2, player) | CheckLine(3, 4, 5, player) | CheckLine(6, 7, 8, player); //Needs to be changed
    }

    private boolean CheckVertical(PlayerType player) {
        return CheckLine(0, 3, 6, player) | CheckLine(1, 4, 7, player) | CheckLine(2, 5, 8, player); //Needs to be changed
    }

    private boolean CheckDiagonal(PlayerType player) {
        return CheckLine(0, 4, 8, player) | CheckLine(2, 4, 6, player); // Needs to be changed
    }

    private boolean CheckBoard(PlayerType player) {
        return CheckHorizontal(player) | CheckVertical(player) | CheckDiagonal(player);
    }
    

    private boolean CheckDraw(PlayerType player) {
        // how to check for a draw?
        // Are all buttons are taken ?
        int count = 0;
        for (int i = 0; i < Button.length; i++) {
            if (Button[i] == PlayerType.NOUSER) {
                count = count + 1;
            }
        }

        return count == 0;
    }
    public void Update(UserEvent U) {
        System.out.println("The user event is " + U.PlayerIdx + "  " + U.Button + " " /* + U.ChatMsg*/);
        // chatBox.setMessageSent(false);

        if(U.Button == 625)
        {
            // chatBox.addMessage(U.ChatMsg);
            // chatBox.setMessageSent(true);
        }
        else if ((CurrentTurn == U.PlayerIdx) && (CurrentTurn == PlayerType.USER1 || CurrentTurn == PlayerType.USER2 || CurrentTurn == PlayerType.USER3 || CurrentTurn == PlayerType.USER4)) {
            // Move is legitimate, lets do what was requested

            // Is the button not taken by X or O?
            if (Button[U.Button] == PlayerType.NOUSER)
            {
                System.out.println("the button was 0, setting it to " + U.PlayerIdx);
                Button[U.Button] = U.PlayerIdx;
                if (U.PlayerIdx == PlayerType.USER1)
                {
                    CurrentTurn = PlayerType.USER2;
                    Msg[1] = "Other Players Move.";
                    Msg[0] = "Your Move.";
                }
                else if (U.PlayerIdx == PlayerType.USER2)
                {
                    CurrentTurn = PlayerType.USER3;
                    Msg[0] = "Other Players Move.";
                    Msg[1] = "Your Move.";
                }
                else if (CurrentTurn == PlayerType.USER3)
                {
                    CurrentTurn = PlayerType.USER4;
                    Msg[0] = "Other Players Move.";
                    Msg[1] = "Your Move.";                
                }
                else if (CurrentTurn == PlayerType.USER4)
                {
                    CurrentTurn = PlayerType.USER1;
                    Msg[0] = "Other Players Move.";
                    Msg[1] = "Your Move."; 
                }
            }
            else
            {
                // Msg[PlayerToIdx(U.PlayerIdx)] = "Not a legal move.";
            }

            // Check for winners, losers, and a draw

            if (CheckBoard(PlayerType.USER1)) //User 1 earns points
            {
                // gamesPlayed++;
                // gamesWonAsX++;
                // concurrentGames--;
                Msg[0] = "You Win!";
                Msg[1] = "You Lose!";
                CurrentTurn = PlayerType.NOUSER;
            } 
            else if (CheckBoard(PlayerType.USER2)) //User 2 earns points
            {
                // gamesPlayed++;
                // gamesWonAsY++;
                // concurrentGames--;
                Msg[1] = "You Win!";
                Msg[0] = "You Lose!";
                CurrentTurn = PlayerType.NOUSER;
            } 
            else if (CheckBoard(PlayerType.USER3)) //User 3 earns points
            {
                // gamesPlayed++;
                // gamesWonAsY++;
                // concurrentGames--;
                Msg[1] = "You Win!";
                Msg[0] = "You Lose!";
                CurrentTurn = PlayerType.NOUSER;
            } 
            else if (CheckBoard(PlayerType.USER4)) //User 4 earns points
            {
                // gamesPlayed++;
                // gamesWonAsY++;
                // concurrentGames--;
                Msg[1] = "You Win!";
                Msg[0] = "You Lose!";
                CurrentTurn = PlayerType.NOUSER;
            } 
            else if (CheckDraw(U.PlayerIdx)) {
                // gamesPlayed++;
                // gamesDrawn++;
                // concurrentGames--;
                Msg[0] = "Draw";
                Msg[1] = "Draw";
                CurrentTurn = PlayerType.NOUSER;
            }
        }
    }

    // public void Tick() {
    //     // this function can be called periodically if a
    //     // timer is needed.

    // }

    // public ChatBox getChatBox() {
    //     return chatBox;
    // }
}


