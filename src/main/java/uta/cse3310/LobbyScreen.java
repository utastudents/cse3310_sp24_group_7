package uta.cse3310;
import java.util.ArrayList;

public class LobbyScreen 
{
    public ArrayList<String> playerNames = new ArrayList<String>();
    public ArrayList<Boolean> playerStatus = new ArrayList<Boolean>();
    public int playerNum = 0;
    public int playersReady = 0;
    public int gamesAvailable = 0;

    // Constructor
    public LobbyScreen(ArrayList<PlayerType> playerList)
    {
      this.playerNames = new ArrayList<>();
      this.playerStatus = new ArrayList<>();
      this.playerNum = 0;
      this.playersReady = 0;
      this.gamesAvailable = 0;

      updateLobby(playerList);
    }

    public void startGame()
    {
        return;
    }
    public void createLobby()
    {
        return;
    }
    public void waitingLobby()
    {
        return;
    }
    public void addPlayer(String nickname)
    {
        //Adding a player and displaying their information
        return;
    }
    public void removePlayer(String nickname)
    {
        return;
    }

    public void updateGamesAvailable(GameScreen[] games){
      gamesAvailable = 0;
      for(GameScreen g: games){
        if(g.isOpen == true){
          gamesAvailable++;
        }
      }
    }

    public String enterMessage(String message)
    {
        return message;
    }
    public String displayMessage (String nickname, String message)
    {
        //Display the username with the following information
        return "message";
        /*
         * displayMessage("RSG", "Nice Score"):
         * EX: RSG: Nice Score
         */
    }

    public void updateLobby(ArrayList<PlayerType> playerList){
         // Clear previous lobby state
         this.playerNames.clear();
         this.playerStatus.clear();
         this.playerNum = 0;
         this.playersReady = 0;
 
         // Populate lobby state with the new player list
         for (PlayerType player : playerList) {
             this.playerNames.add(player.name);
             this.playerStatus.add(player.isReady);
             this.playerNum++;
 
             if (player.isReady) {
                 this.playersReady++;
        }
      }
}
}
