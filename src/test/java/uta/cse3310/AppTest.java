package uta.cse3310;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.java_websocket.WebSocket;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testOnClosePlayerDisconnected() {
        // Mock WebSocket and dependencies
        WebSocket mockConn = Mockito.mock(WebSocket.class);

        // Create a list of players with a test player
        ArrayList<PlayerType> playerList = new ArrayList<>();
        PlayerType testPlayer = new PlayerType("TestPlayer", mockConn);
        playerList.add(testPlayer);

        // Create a mock LobbyScreen 
        LobbyScreen mockLobby = Mockito.mock(LobbyScreen.class);

        // Instance App with mocked dependencies
        App app = new App(9880);
        app.playerList = playerList;
        app.lobby = mockLobby;

        // Call the onClose method for the test player
        app.onClose(mockConn, 1000, "Client disconnected", true);

        // Verify that the player was removed from the playerList
        assertFalse(app.playerList.contains(testPlayer)); 

        // Verify lobby and game updates
        Mockito.verify(mockLobby, times(1)).updateLobby(app.playerList);
        Mockito.verify(mockLobby, times(1)).updateGamesAvailable(app.games);
        // Verify broadcast message
        Mockito.verify(mockConn, times(1)).send(anyString());
        // Verify connection closure
        Mockito.verify(mockConn, times(1)).closeConnection(anyInt(), anyString());
    }
}
