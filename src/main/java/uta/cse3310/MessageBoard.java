package uta.cse3310;
import java.util.List;
import java.util.ArrayList;

public class MessageBoard
{
    private List<String> messages; //Stores all chat messages (maybe could be an array)
    private String[] usersOnline; //Stores users currently connected to chat (maybe Set)
    private boolean isChatEnabled; //Has the user disabled the chat
    private int userCount; //For the usersOnline array

    private final static int MAX_USERS = 4;

    //Constructor
    public MessageBoard()
    {
        this.messages = new ArrayList<>();
        this.usersOnline = new String[MAX_USERS];
        this.isChatEnabled = true; //Chat is enabled by default
        this.userCount = 0;
    }

    //Connects user to the chat when they enter the lobby
    public void connectToChat(String user)
    {
        //Add the user to the usersOnline array
        //Load existing chat messages
    }

    //Send a message to the chat
    public void sendMessage(String user, String message)
    {
        //Add the message to the messages list if chat is enabled
        //Display the message in the chat interface
    }
    
    //Display messages in the chat box
    public void displayMessages()
    {
        //Print messages if chat is enabled
    }
    
    //Enable or disable chat
    public void toggleChat()
    {
        //Toggle the isChatEnabled boolean
        //Maybe clear the chat interface if disabled and load messages if enabled
    }

    //Checks if the chat is enabled for the user
    public boolean isChatEnabled()
    {
        return this.isChatEnabled;
    }

    //Disconnects user from the chat when they exit
    public void disconnectFromChat(String user)
    {
        //Remove the user from the usersOnline array
        //Optionally, handle any cleanup or notifications to other users
    }
}
