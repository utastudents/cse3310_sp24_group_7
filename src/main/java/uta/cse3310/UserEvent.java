package uta.cse3310;
// User events are sent from the webpage to the server

public class UserEvent {
    PlayerType PlayerIdx; // either an XPLAYER or an OPLAYER

    int GameId; // the game ID on the server
    //add LobbyId ?
    int Button; // button number from 0 to 8
    int GridRow; //row index of grid letter pushed
    int GridColumn; //column index of grid letter pushed
    String Msg; // chat message sent
}
