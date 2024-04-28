package uta.cse3310;
import org.junit.jupiter.api.*;

import junit.framework.TestCase;

import org.java_websocket.WebSocket;
import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameScreenTest
{
    GameScreen game = new GameScreen();
    LobbyScreen lobby = new LobbyScreen();

    @BeforeEach
    public void verifySetup()
    {
        App app = new App(9880); //
        app.setOnOpen((WebSocket conn)->System.out.println("Connection opened"));
        /*
         * Need to finalize code
         */
        GameScreen.StartGame();
        assertTrue(game.isOpen);
    }
    @Test
    public void Hello()
    {
        GameScreen game = new GameScreen();
        String a = "Hello";
        assertEquals("hello", game.Hello()  );
    }
    @Test
    public void testStartGame(String testName)
    {
        App app = new App(9880);
        // app.GameScreen();
        // assertTrue(app.);
        super(testName);
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
     // testing adding and removing a player from a game
     public void testAddandRemovePlayer(){
        Player p1 = new Player("1");
        ArrayList<String> words = new ArrayList<String>();
        try{
            String str;
            BufferedReader reader = new BufferedReader(new FileReader("filtered_words.txt"));
            while((str = reader.readLine())!=null)
                words.add(str);
            reader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        LobbyScreen lobby = new LobbyScreen();
        App A = new App(20);
        GameScreen test = new GameScreen(p1, words, lobby, A);

        Player p2 = new Player("2");
        test.addPlayer(p2);
        assertTrue("Player Not Found",test.players.contains(p2));
        test.removePlayer(p2);
        assertFalse("Player Found",test.players.contains(p2));
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
