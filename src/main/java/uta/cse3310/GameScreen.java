package uta.cse3310;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
//known bugs
//while one player has a letter selected, the other player cannot score any words

public class GameScreen {
    public PlayerType Players;
    public String playerNames[];
    public int GameId;
    private ChatBox chatBox;
    private WordBank wordBank;
    private List<PickedLetters> pickedLetters;
    public Scoreboard scoreBoard;
    public int[] start;
    public int[] end;
    public int playersJoined;
    int counter = 3;
    public String version;

    //Created by Abubakar Kassim
    public ArrayList<PlayerType> players;
    public boolean[] playerIsPlaying = new boolean[4]; // Need to implement to enter lobby when game is full

    public GameScreen() 
    {
        // Created by Abubakar Kassim
        this.players = new ArrayList<PlayerType>();

        version = "The Word Search Game";
        if(System.getenv("VERSION") != null)
        {
            version = System.getenv("VERSION");
        }

        playerNames = new String[4];

        wordBank = new WordBank();
        List<String> wordList = new ArrayList<>();
        wordBank.addWordsFromFile("words.txt", wordList);
        wordBank.generateGrid(25, 25, wordList);

        chatBox = new ChatBox();
        scoreBoard = new Scoreboard(4);

        pickedLetters = new ArrayList<>();

        if(playersJoined == 0)
        {
            Players = PlayerType.PLAYER1;
        }
        else if(playersJoined == 1)
        {
            Players = PlayerType.PLAYER2;
        }
        else if(playersJoined == 2)
        {
            Players = PlayerType.PLAYER3;
        }
        else
        {
            Players = PlayerType.PLAYER4;
        }
        

        playersJoined = 0;

        start = new int[]{-1, -1};
        end = new int[]{-1, -1};

        
    }

    public void StartGame() {
        // X player goes first. Because that is how it is.
        //Timer();
    }

    // This function returns an index for each player
    // It does not depend on the representation of Enums
    public int PlayerToIdx(PlayerType P) {
        int retval = 0;
        if (P == PlayerType.PLAYER1) {
            retval = 0;
        } 
        else if (P == PlayerType.PLAYER2) 
        {
            retval = 1;
        }
        else if (P == PlayerType.PLAYER3) 
        {
            retval = 2;
        }
        else if (P == PlayerType.PLAYER4) 
        {
            retval = 3;
        }
        return retval;
    }

    public String PlayerToNickname(PlayerType P)
    {
        String retval = "UNKNOWN";
        if (P == PlayerType.PLAYER1) {
            retval = playerNames[0];
        } 
        else if (P == PlayerType.PLAYER2) 
        {
            retval = playerNames[1];
        }
        else if (P == PlayerType.PLAYER3) 
        {
            retval = playerNames[2];
        }
        else if (P == PlayerType.PLAYER4) 
        {
            retval = playerNames[3];
        }
        return retval;
    }

    public void Timer() {
        
        Timer timer = new Timer();
        int delay = 1000; // 1 second
        int period = 1000; // 1 second

        timer.scheduleAtFixedRate(new TimerTask() {
            

            public void run() {
                System.out.println("Seconds left: " + counter);
                counter--;

                if (counter < 0) {
                    System.out.println("Time's up!");
                    counter++;

                    counter = 30;

                     timer.cancel(); // Terminate the timer when countdown is complete
                }
            }
        }, delay, period);
    }

    public void Update(UserEvent U) {
        System.out.println("The user event is " + U.PlayerIdx + " " + U.Button + " " + U.Msg);
        System.out.println("The user event is " + U.PlayerIdx + " " + U.GridRow + " " + U.GridColumn);
        chatBox.setMessageSent(false);

        List<WordLocation> wordLocations = wordBank.getWordLocations();

        if(U.Button == -1)
        {
            if(start[0] == -1)
            {
                start[0] = U.GridRow;
                start[1] = U.GridColumn;
            }
            else if(end[0] == -1)
            {
                end[0] = U.GridRow;
                end[1] = U.GridColumn;

                WordLocation location;
                for(int i = 0; i < wordLocations.size(); i++)
                {
                    location = wordLocations.get(i);
                    if(location.getStartRow() == start[0] && location.getStartCol() == start[1])
                    {
                        if(location.getEndRow() == end[0] && location.getEndCol() == end[1])
                        {
                            for(int j = 0; j < location.getWord().length(); j++)
                            {
                                if(location.getDirection() == 0)
                                {
                                    pickedLetters.add(new PickedLetters(start[0]+j, start[1], U.PlayerIdx));
                                }
                                else if(location.getDirection() == 1)
                                {
                                    pickedLetters.add(new PickedLetters(start[0], start[1]+j, U.PlayerIdx));
                                }
                                else if(location.getDirection() == 2)
                                {
                                    pickedLetters.add(new PickedLetters(start[0]-j, start[1], U.PlayerIdx));
                                }
                                else if(location.getDirection() == 3)
                                {
                                    pickedLetters.add(new PickedLetters(start[0], start[1]-j, U.PlayerIdx));
                                }
                                else
                                {
                                    pickedLetters.add(new PickedLetters(start[0]+j, start[1]+j, U.PlayerIdx));
                                }
                                scoreBoard.addScore(PlayerToIdx(U.PlayerIdx));
                            }
                            wordLocations.remove(i);
                            i = wordLocations.size();
                        }
                    }
                }

                start[0] = -1;
                start[1] = -1;
                end[0] = -1;
                end[1] = -1;
            }

        }
        else if(U.Button == 9)
        {
            if(!U.Msg.isEmpty())
            {
                chatBox.addMessage(PlayerToNickname(U.PlayerIdx) + ": " + U.Msg);
                chatBox.setMessageSent(true);
            }
        }
    }

}
// In windows, shift-alt-F formats the source code
// In linux, it is ctrl-shift-I
