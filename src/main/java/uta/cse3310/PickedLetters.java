package uta.cse3310;

import java.util.ArrayList;
public class PickedLetters {
    public int row;
    public int col;
    public PlayerType playerId;

    public PickedLetters(int row, int col, PlayerType playerId){
         this.row = row;
         this.col = col;
         this.playerId = playerId;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public PlayerType getPlayer()
    {
        return playerId;
    }
    
}
