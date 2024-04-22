package uta.cse3310;

import org.java_websocket.WebSocket;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;


public class ChatBox {
    private List<String> messages = new ArrayList<>();
    private boolean messageSent = false;

    // Method to add a message to the chat history
    public void addMessage(String message) {
        messages.add(message);
    }

    public void setMessageSent(boolean ms)
    {
        messageSent = ms;
    }
}