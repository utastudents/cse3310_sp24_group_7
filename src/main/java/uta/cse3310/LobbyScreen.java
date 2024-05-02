package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class LobbyScreen {
    private List<Player> players;
    private int lobbyId;
    //private ChatBox chatBox;
    private int playersJoined;

    public LobbyScreen() {
        players = new ArrayList<>();
        playersJoined = 0;
        //chatBox = new ChatBox();
    }

    public void addPlayer(Player player) {
        if (playersJoined < 4) {
            players.add(player);
            playersJoined++;
            System.out.println(player + " joined the lobby.");
        } else {
            System.out.println("Lobby is full. Cannot add more players.");
        }
    }

    public void clearTable() {
        players.clear();
        playersJoined = 0;
        System.out.println("Lobby Table cleared. Starting new Game!");
    }

    public int getGameId() {
        return lobbyId;
    }


    public void update(UserEvent U) {
        System.out.println("Received update from Player " + U.PlayerIdx + ": " + U.Msg);
        // Handle the update logic here, such as updating UI or broadcasting to other players
    }

    // Getters and setters for lobbyId and chatBox omitted for brevity
}
