package uta.cse3310;
import org.java_websocket.WebSocket;

public class Player {
    public PlayerType playerType;
    public String nickname;
    public WebSocket webSocket;
    public Boolean isReady;

    public Player(PlayerType playerType, String nickname, WebSocket webSocket) {
        this.playerType = playerType;
        this.nickname = nickname;
        this.webSocket = webSocket;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getNickname() {
        return nickname;
    }

    public WebSocket getPlayerConn() {
        return webSocket;
    }
}
