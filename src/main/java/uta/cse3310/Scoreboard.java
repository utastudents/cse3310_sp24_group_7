package uta.cse3310;
import java.util.Map;
import java.util.HashMap;


public class Scoreboard
{
    private Map<String, Integer> scores; //Mapping of player names to their scores

    private final static int maxPlayers = 4; //Maximum number of players

    //Constructor
    public Scoreboard()
    {
        scores = new HashMap<>();
    }

    //Adds a player to the scoreboard
    public void addPlayer(String playerName)
    {
        //Add player
    }

    //Increments the score of a player
    public void incrementScore(String playerName, int points)
    {
        //Increment score
    }

    //Gets the score of a player
    public int getScore(String playerName)
    {
        //Get score
        return -1;
    }

    //Retrieves the current state of the scoreboard. Players and their scores
    public String getScoreboard()
    {
        //Retrieve scoreboard
        return "";
    }
}
