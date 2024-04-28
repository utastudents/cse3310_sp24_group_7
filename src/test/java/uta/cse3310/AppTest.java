package uta.cse3310;

import static junit.framework.Assert.assertEquals;

public class AppTest {

    //Test Socket
    public void testWebSocketPort() {

        //Setexpected # for port
        int initialPort = 9107;

        //Create Instance Of App
        App app = new App(initialPort);

        int portRunning = app.getPort();

        // If initialPort = portRunning then TRUE, else FALSE
        assertEquals(initialPort, portRunning);
    }
}