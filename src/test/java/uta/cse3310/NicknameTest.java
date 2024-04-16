package uta.cse3310;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.java_websocket.WebSocket;
import java.util.ArrayList;

class NicknameTest {
    private App app;

     @BeforeEach
     void setUp() {
       app = new App(9880);
     }

  @Test
     void testIsNicknameUnique() {
         // Create sample players
         PlayerType player1 = new PlayerType("Player1", mock(WebSocket.class));
         PlayerType player2 = new PlayerType("Player2", mock(WebSocket.class));

         // Add players to the playerList
         app.playerList.add(player1);
         app.playerList.add(player2);

        // unique nickname
         Assertions.assertTrue(app.isNicknameUnique("Player3"));

//         // non-unique nickname
//         Assertions.assertFalse(app.isNicknameUnique("Player1"));
//     }
// }
