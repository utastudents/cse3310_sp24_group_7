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
        // app.setOnOpen((WebSocket conn)->System.out.println("Connection opened"));
        /*
         * Need to finalize cpde
         */
        game.StartGame();
        assertTrue(game.isOpen);
    }
    @Test
    public void testStartGame()
    {

        assertTrue(false);
    }
    @Test
    public void verfiyTimer() // is this trivial
    {
        GameScreen game = new GameScreen();
        
        assertTrue(false);
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
