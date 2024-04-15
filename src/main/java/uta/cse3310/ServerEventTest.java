package uta.cse3310;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServerEventTest 
{

    @Test
    public void testGetGameId() 
    {
        int expectedGameId = 123;
        ServerEvent serverEvent = new ServerEvent();
        serverEvent.setGameId(expectedGameId);
        int actualGameId = serverEvent.getGameId();
        assertEquals(expectedGameId, actualGameId);
    }
}