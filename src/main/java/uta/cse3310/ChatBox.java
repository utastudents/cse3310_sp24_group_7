package uta.cse3310;

import java.util.ArrayList;
import java.util.List;

public class ChatBox {
    private List<String> messages = new ArrayList<>();
    private boolean messageSent = false;

    // Method to add a message to the chat history
    public void addMessage(String message) {
        messages.add(message);
    }
    
    // Method to get the list of messages
    public List<String> getMessages() {
        return messages;
    }

    // Method to set if a message is sent or not
    public void setMessageSent(boolean ms) {
        messageSent = ms;
    }
}
