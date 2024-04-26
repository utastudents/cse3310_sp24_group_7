package uta.cse3310;

/*
import java.util.ArrayList;
import java.util.List;


public class Game {
    public PlayerType Players;
    public PlayerType CurrentTurn;
    public PlayerType[] Button;
    public int GridRow;
    public int GridColumn;
    public String[] Msg;
    public int GameId;
    private ChatBox chatBox;
    private WordBank wordBank;
    private List<PickedLetters> pickedLetters;
    public int[] start;
    public int[] end;

    Game() {
        wordBank = new WordBank();
        wordBank.addWordsFromFile("words.txt");
        wordBank.generateGrid(25, 25);
        wordBank.printGrid();

        Msg = new String[2];

        chatBox = new ChatBox();

        pickedLetters = new ArrayList<>();

        Players = PlayerType.XPLAYER;
        CurrentTurn = PlayerType.NOPLAYER;

        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";

        GridRow = -1;
        GridColumn = -1;

        start = new int[]{-1, -1};
        end = new int[]{-1, -1};
    }

    public void StartGame() {
        // X player goes first. Because that is how it is.
        Msg[0] = "You are X. Your turn";
        Msg[1] = "You are O. Other players turn";
        CurrentTurn = PlayerType.XPLAYER;
    }

    // This function returns an index for each player
    // It does not depend on the representation of Enums
    public int PlayerToIdx(PlayerType P) {
        int retval = 0;
        if (P == PlayerType.XPLAYER) {
            retval = 0;
        } else {
            retval = 1;
        }
        return retval;
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
                chatBox.addMessage(U.PlayerIdx + ": " + U.Msg);
                chatBox.setMessageSent(true);
            }
        }
    }

}
// In windows, shift-alt-F formats the source code
// In linux, it is ctrl-shift-I
*/