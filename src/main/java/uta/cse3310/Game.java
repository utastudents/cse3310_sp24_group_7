package uta.cse3310;
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

    Game() {
        wordBank = new WordBank();
        wordBank.addWordsFromFile("words.txt");
        wordBank.generateGrid(25, 25);
        wordBank.printGrid();

        Msg = new String[2];

        chatBox = new ChatBox();

        Players = PlayerType.XPLAYER;
        CurrentTurn = PlayerType.NOPLAYER;

        Msg[0] = "Waiting for other player to join";
        Msg[1] = "";

        GridRow = -1;
        GridColumn = -1;
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

        
        if(U.Button == 9)
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
