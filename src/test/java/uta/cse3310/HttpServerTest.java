package uta.cse3310;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import org.junit.Test;

public class HttpServerTest 
{

    //testing that when the server starts after being given a port, it starts
    @Test
    public void startTheTestServer() 
    {
        int port = 1234;
        String directory = "./workspaces/cse3310_sp24_group_7/html";
        HttpServer httpServer = new HttpServer(port, directory);
        httpServer.start();
        assertNotNull(httpServer);
    }

    //teting that the server doesnt run too slow
    @Test
    public void testServerTime()
    {
        long startTime = System.currentTimeMillis();
        startTheTestServer();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime; // Should be less than 2 seconds.

        assertTrue(timeElapsed <= 2000);
    }
}