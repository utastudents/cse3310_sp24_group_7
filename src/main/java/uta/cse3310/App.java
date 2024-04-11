package uta.cse3310;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class App extends WebSocketServer {

    GameScreen[] games = new GameScreen[5];

    // List of players 
    public ArrayList<PlayerType> playerList = new ArrayList<PlayerType>();
    int playerCount = 0;
    String[] nicknames = new String[20];

    public App(int port){
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message from " + conn.getRemoteSocketAddress() + ": " + message);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
      GsonBuilder builder = new GsonBuilder();
      Gson gson = builder.create();
      boolean playerFound = false;
      // read connection to find the player that dc'ed
      // and delete them
      // this gets rid of having to do another code check for disconnects

      // if player ready, decrease lobby numReady by 1

      for(int i = 0; i < playerList.size(); i++){
          if(playerList.get(i).playerConn == conn){
              playerFound = true;
              System.out.println("Player exiting.");
              playerList.remove(i);
              break;
          }
        }
      if(playerFound){
          System.out.printf("[");
          for(PlayerType x: playerList){
              System.out.printf(" %s", x.name);
          }
      }
      System.out.println(conn + " has closed");
      if(playerList.size() == 0){
          System.out.println("[RESETTING...]");
          // reset the games
          for(int i = 0; i < 5; i++){
              games[i] = new GameScreen();
          }
      }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
      ex.printStackTrace();
    }

    @Override
    public void onStart() {
      System.out.println("Server started!");
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