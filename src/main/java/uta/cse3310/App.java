package uta.cse3310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.handshake.ClientHandshake;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList; // Import ArrayList
import java.util.List; // Ensure List is also imported if it's used
import java.util.Set;


public class App extends WebSocketServer {
  // All games currently underway on this server are stored in
  // the vector ActiveGames
  Vector<GameScreen> ActiveGames = new Vector<GameScreen>();
  public ArrayList<Player> playerList = new ArrayList<>();
  Set<Player> activeUsers = new HashSet<>();
  int gameId = 1;
  LobbyScreen lobby;
  int playerId = 0;
  


  public App(int port) {
    super(new InetSocketAddress(port));
  }

  public App(InetSocketAddress address) {
    super(address);
  }

  public App(int port, Draft_6455 draft) {
    super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
  }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {

    System.out.println("New Player Connected: " + conn.getRemoteSocketAddress().getAddress().getHostAddress());

  }

  @Override
  public void onMessage(WebSocket conn, String message) {


    // !ENTRY SCREEN! \\


    Gson gson = new Gson();
    JsonObject jsonMessage = gson.fromJson(message, JsonObject.class);

    if(jsonMessage.get("type") != null)
    {

    String messageType = jsonMessage.get("type").getAsString();

    if (messageType.equals("nickname")) {
      String nickname = jsonMessage.get("value").getAsString();

        // Check if the nickname is already in use
      if (isNicknameUnique(nickname)) {
        GameScreen G = null;
        for (GameScreen i : ActiveGames) {
          if (i.Players == uta.cse3310.PlayerType.PLAYER1 || i.Players == uta.cse3310.PlayerType.PLAYER2 || i.Players == uta.cse3310.PlayerType.PLAYER3) {
            G = i;
            System.out.println("found a match");
          }
        }

    // No matches ? Create a new Game.
        if (G == null) {
          G = new GameScreen();
          G.GameId = gameId;
          gameId++;
      // Add the first player
          G.Players = uta.cse3310.PlayerType.PLAYER1;
          G.playersJoined++;
          G.playerNames[0] = nickname;
          ActiveGames.add(G);
          System.out.println(" creating a new Game");
        } else if (G.Players == PlayerType.PLAYER1) {
      // join an existing game
          System.out.println(" not a new game");
          G.Players = uta.cse3310.PlayerType.PLAYER2;
          G.playersJoined++;
          G.playerNames[1] = nickname;
        } else if (G.Players == PlayerType.PLAYER2) {
      // join an existing game
          System.out.println(" not a new game");
          G.Players = uta.cse3310.PlayerType.PLAYER3;
          G.playersJoined++;
          G.playerNames[2] = nickname;
        }
        else {
      // join an existing game
          System.out.println(" not a new game");
          G.Players = uta.cse3310.PlayerType.PLAYER4;
          G.playersJoined++;
          G.playerNames[3] = nickname;
        }

        System.out.println("G.players is " + G.Players);

    // allows the websocket to give us the Game when a message arrives
        conn.setAttachment(G);

        Player player = new Player(G.Players, nickname, conn);
        playerList.add(player);
        activeUsers.add(player); // Add the player to the active users set

            // Respond back to the client
        JsonObject response = new JsonObject();
        response.addProperty("type", "acknowledge");
        response.addProperty("YouAre", G.Players.name());
        response.addProperty("GameId", G.GameId);
        response.addProperty("message", "Nickname received and stored.");

            // Gather active users and include them in the response
        JsonArray activeUsersArray = getActiveUsersArray();
        response.add("activeUsers", activeUsersArray);

        conn.send(gson.toJson(response));

            // Broadcast the new player's nickname to all connected clients
        broadcastPlayerJoined(nickname, conn);
        broadcastPlayerList(activeUsers);

        if(G.playersJoined == 4)
        {
          String jsonString;
          jsonString = gson.toJson(G);

          System.out.println(jsonString);
          broadcast(jsonString);
        }

      } 
      else {
            // Send error response to the client: Nickname not Unique
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("type", "nickname_error");
        errorResponse.addProperty("message", "Nickname Already in Use.");
        conn.send(gson.toJson(errorResponse));
      }
    }
  }

    else {
      GsonBuilder builder = new GsonBuilder();
      gson = builder.create();
      UserEvent U = gson.fromJson(message, UserEvent.class);
      System.out.println(U.Button);

      // Get our Game Object
      GameScreen G = conn.getAttachment();
      if(G.playersJoined == 4)
      {
        G.playersJoined = 5;
      }
      G.Update(U);

      // send out the game state every time
      // to everyone
      String jsonString;
      jsonString = gson.toJson(G);

      // System.out.println(jsonString);
      broadcast(jsonString);
    }











    // !GAME SCREEN! \\


    // Inside the 'onMessage' method

  }  

    // Method to get a JsonArray of active user nicknames
  private JsonArray getActiveUsersArray() {
    JsonArray activeUsersArray = new JsonArray();
    for (Player activePlayer : activeUsers) {
      activeUsersArray.add(activePlayer.getNickname());
    }
    return activeUsersArray;
  }

    // Method to broadcast the updated active users list to all connected clients
  private void broadcastPlayerList(Set<Player> activeUsers) {
    Gson gson = new Gson();
    JsonObject playerListUpdate = new JsonObject();
    playerListUpdate.addProperty("type", "player_list_update");

    JsonArray activeUsersArray = getActiveUsersArray();
    playerListUpdate.add("players", activeUsersArray);

    for (WebSocket conn : getConnections()) {
      conn.send(gson.toJson(playerListUpdate));
    }
  }


    // Method to broadcast the "player_joined" message to all connected clients
  private void broadcastPlayerJoined(String nickname, WebSocket senderConn) {
    Gson gson = new Gson();
    JsonObject playerJoinedMessage = new JsonObject();
    playerJoinedMessage.addProperty("type", "player_joined");
      playerJoinedMessage.addProperty("nickname", nickname); // Include the nickname of the player who joined

      for (WebSocket conn : getConnections()) {
        if (!conn.equals(senderConn)) {
          conn.send(gson.toJson(playerJoinedMessage));
        }
      }
    }

    // Add a new method to broadcast game state to all players
    private void broadcastGameState(String gameState) {
      for (Player player : playerList) {
        player.getPlayerConn().send(gameState);
      }
    }

    // Method to validate nickname uniqueness
    private boolean isNicknameUnique(String nickname) {
      for (Player player : playerList) {
        if (player.getNickname().equals(nickname)) {
          System.out.println("Nickname not unique.");
            return false; // Nickname is not unique
          }
        }
    return true; // Nickname is unique
  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    String disconnectedPlayerNickname = null;

        // Find and remove the disconnected player from activeUsers and playerList
    for (Player player : playerList) {
      if (player.getPlayerConn().equals(conn)) {
        disconnectedPlayerNickname = player.getNickname();
        activeUsers.remove(player);
        playerList.remove(player);
        break;
      }
    }

    if (disconnectedPlayerNickname != null) {
      System.out.println(disconnectedPlayerNickname + " has Disconnected.");
    } else {
      System.out.println("Player with connection " + conn + " has Disconnected.");
    }

        // Broadcast the updated active users list to all remaining connected clients
    broadcastPlayerList(activeUsers);

        // Reset games and update lobby if no players connected
    if (playerList.isEmpty()) {
      System.out.println("\n0 Players Detected.");
      System.out.println("CLEARING LOBBY AND RESETTING GAMES...");
    }
    playerId--;
    gameId--;
  }


  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    System.out.println(conn + ": " + message);
  }


  @Override
  public void onError(WebSocket conn, Exception ex) {
    ex.printStackTrace();
    if (conn != null) {
      // some errors like port binding failed may not be assignable to a specific
      // websocket
    }
  }

  @Override
  public void onStart() {
    System.out.println("Server has started!");
  }

  public static void main(String[] args) {

    // Set up the http server
    int port = 9007;
    HttpServer H = new HttpServer(port, "./html");
    H.start();
    System.out.println("http Server started on port:" + port);

    // create and start the websocket server

    port = 9107;
    App A = new App(port);
    A.start();
    System.out.println("websocket Server started on port: " + port);

  }
}
