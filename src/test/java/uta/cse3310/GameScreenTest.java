package uta.cse3310;

import org.junit.jupiter.api.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class GameScreenTest {


    //It checks if the timer is properly implemented
    @Test
    public void testTimer() {
        GameScreen game = new GameScreen();
        game.Timer();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(game.counter <= 3); // Adjust the expected value based on your Timer logic
    }

    //tests when a user sends a message to the chat
    @Test
    public void testChatUpdate()
    {
        GameScreen game = new GameScreen();
        UserEvent XChatMsg = new UserEvent();
        XChatMsg.PlayerIdx = PlayerType.XPLAYER;
        XChatMsg.GameId = 1;
        XChatMsg.Button = 9;
        XChatMsg.Msg = "Test!";
        game.Update(XChatMsg);
    }

    //tests when a user clicks a button on the grid
    @Test
    public void testGridClickUpdate()
    {
        GameScreen game = new GameScreen();
        UserEvent XGridClick = new UserEvent();
        XGridClick.PlayerIdx = PlayerType.XPLAYER;
        XGridClick.GameId = 1;
        XGridClick.GridRow = 4;
        XGridClick.GridColumn = 3;

        game.Update(XGridClick);
    }

}
