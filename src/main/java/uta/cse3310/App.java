package uta.cse3310;

import java.net.InetSocketAddress;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Arrays;
import java.util.ArrayList;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class App extends WebSocketServer {

    GameScreen[] games = new GameScreen[5];

    // List of players 
    public ArrayList<PlayerType> playerList = new ArrayList<PlayerType>();
    int playerCount = 0;
    private LobbyScreen lobby;
    String[] nicknames = new String[20];

    public App(int port){
        super(new InetSocketAddress(port));
        this.lobby = new LobbyScreen(playerList);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
    System.out.println("Message from " + conn.getRemoteSocketAddress() + ": " + message);
    Gson gson = new Gson();
    JsonObject jsonMessage = gson.fromJson(message, JsonObject.class);

    String messageType = jsonMessage.get("type").getAsString();
    if (messageType.equals("nickname")) {
        String nickname = jsonMessage.get("value").getAsString();
        System.out.println("Received nickname from client: " + nickname);

        // Check if the nickname is already in use
        if (isNicknameUnique(nickname)) {
            // Save the nickname to the server
            System.out.println("Player Added!");
            PlayerType player = new PlayerType(nickname, conn);
            playerList.add(player);
            playerCount++;

            // Respond back to the client
            JsonObject response = new JsonObject();
            response.addProperty("type", "acknowledge");
            response.addProperty("message", "Nickname received and stored.");
            conn.send(gson.toJson(response));
        } else {
            // Send error response to the client
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("type", "error");
            errorResponse.addProperty("message", "Nickname already in Use.");
            conn.send(gson.toJson(errorResponse));
        }
    }
}



    // Method to validate nickname uniqueness
    private boolean isNicknameUnique(String nickname) {
        for (PlayerType player : playerList) {
            if (player.getName().equals(nickname)) {
                System.out.println("Player not added.");
                return false; // Nickname is not unique
            }
        }
        return true; // Nickname is unique
    }


    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String disconnectedPlayerNickname = null;

        // Find the nickname of the disconnected player
        for (PlayerType player : playerList) {
            if (player.getPlayerConn().equals(conn)) {
                disconnectedPlayerNickname = player.getName();
                break;
            }
        }

        if (disconnectedPlayerNickname != null) {
            System.out.println(disconnectedPlayerNickname + " has Disconnected.");
        } 
        else {
            System.out.println("Player with connection " + conn + " has Disconnected.");
        }

        // Remove player from playerList
        playerList.removeIf(player -> player.getPlayerConn().equals(conn));

        // Update lobby and games available
        lobby.updateLobby(playerList);
        lobby.updateGamesAvailable(games);
        Gson gson = new GsonBuilder().create();
        broadcast(gson.toJson(lobby));
        
        // Reset games and update lobby if no players connected
        if (playerList.isEmpty()) {
            System.out.println("\n0 Players Detected.");
            System.out.println("CLEARING LOBBY AND RESETTING GAMES...");
            for (int i = 0; i < games.length; i++) {
                games[i] = new GameScreen();
            }
            lobby.updateLobby(playerList);
        }
    }


    @Override
    public void onError(WebSocket conn, Exception ex) {
      ex.printStackTrace();
    }

    @Override
    public void onStart() {
      System.out.println("Server has started!");
      setConnectionLostTimeout(0);
      for(int i = 0; i < 5; i++){
          games[i] = new GameScreen();
      }
    }

    public static void main(String[] args) {
      // HTTP_PORT environment variable
      String httpPortEnv = System.getenv("HTTP_PORT");
      int httpPort = (httpPortEnv != null) ? Integer.parseInt(httpPortEnv) : 9080;
  
      // WEBSOCKET_PORT environment variable
      String websocketPortEnv = System.getenv("WEBSOCKET_PORT");
      int websocketPort = (websocketPortEnv != null) ? Integer.parseInt(websocketPortEnv) : 9880;
  
      // Set up HTTP server
      HttpServer httpServer = new HttpServer(httpPort, "./html");
      httpServer.start();
      System.out.println("HTTP Server started on port: " + httpPort);
  
      // start WebSocket server
      App app = new App(websocketPort);
      app.start();
      System.out.println("WebSocket Server started on port: " + websocketPort);
  }
}
