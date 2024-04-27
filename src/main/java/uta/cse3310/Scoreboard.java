package uta.cse3310;
//import java.util.Map;
//import java.util.HashMap;

//test for github

public class Scoreboard
{
    private int[] scores;

    public Scoreboard(int numPlayers){
        scores = new int[numPlayers];
    }

    public int getScore(int playerIdx){
        return scores[playerIdx];
    }

    public void addScore(int playerIdx){
        scores[playerIdx] +=1;
    }
}