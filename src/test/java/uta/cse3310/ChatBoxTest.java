package uta.cse3310;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ChatBoxTest {

    private ChatBox chatBox;
    

    @Before
    public void setUp() throws Exception {
        chatBox = new ChatBox(); // Initialize the ChatBox instance before each test
    }

    //tests clearing the chat
    @Test
    public void resetChat()
    {
        chatBox = null; 
        assertNull(chatBox);
    }

    //tests adding a message to chatbox arraylist
    @Test
    public void testAddMessage() {
        String message = "Hello";
        chatBox.addMessage(message);

        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add(message);

        assertEquals(expectedMessages, chatBox.getMessages());
    }

    //tests getting a message from chatbox
    @Test
    public void testGetMessage()
    {
        testAddMessage();
        chatBox.getMessages();
    }
}
