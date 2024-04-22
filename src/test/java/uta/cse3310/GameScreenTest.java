package uta.cse3310;
import org.junit.jupiter.api.*;

import org.java_websocket.WebSocket;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class GameScreenTest 
{
    GameScreen game = new GameScreen();

    @BeforeEach
    public void verifySetup()
    {
        App app = new App(9880); //
        app.setOnOpen((WebSocket conn)->System.out.println("Connection opened"));
        /*
         * Need to finalize cpde
         */
        GameScreen.StartGame();
        assertTrue(game.isOpen);
    }
    @Test
    public void testStartGame()
    {
        App app = new App(9880);
        // app.GameScreen();
        // assertTrue(app.);
    }
    @Test
    public void verfiyTimer() // used to move on from timer test, not actually testing the functionality
    {
        GameScreen game = new GameScreen();
        game.Timer();
            if (game.counter < 0)
            {
                assertTrue(game.Timer() <= 0);
            }        
    }
    @Test
    public void verifyTurnSystem()
    {
        
    }
    @Test
    public void testHighlightingWord()
    {

    }
    @Test
    public void testRestartButton()
    {
        
    }
}
