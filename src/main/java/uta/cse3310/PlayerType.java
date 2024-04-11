package uta.cse3310;
import org.java_websocket.WebSocket;

public class PlayerType {
  public String name;
  public int score;
  public int index;
  public int gameNum;
  public boolean isReady;
  public WebSocket playerConn;
  
  PlayerType(String name, WebSocket newConn){
    this.name = name;
    this.isReady = false;
    this.playerConn = newConn;
    this.score = 0;
  }
}
    
