package uta.cse3310;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreBoardTest
{
    @Test
    public void toTestGetScore()
    {
        Scoreboard scoreboard = new Scoreboard(4);
        assertEquals(0, scoreboard.getScore(0));
        assertEquals(0, scoreboard.getScore(1));
        assertEquals(0, scoreboard.getScore(2));
        assertEquals(0, scoreboard.getScore(3));
    }

    @Test
    public void toTestAddScore()
    {
        Scoreboard scoreboard = new Scoreboard(4);
        assertEquals(0, scoreboard.getScore(0));
        scoreboard.addScore(0);
        assertEquals(1, scoreboard.getScore(0));
        scoreboard.addScore(1);
        scoreboard.addScore(1);
        assertEquals(2, scoreboard.getScore(1));
    }
}

