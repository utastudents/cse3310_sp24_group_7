package uta.cse3310;

import java.util.ArrayList;
public class PickedLetters {
    public PlayerType playerId;
    public int row;
    public int col;
    public boolean found;
    public transient ArrayList<WordLocation> letters = new ArrayList<>();

    public PickedLetters( int row, int col, PlayerType playerId){
         this.row = row;
         this.col = col;
         this.playerId = playerId;
    }
}
