package uta.cse3310;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class HttpServerTest 
{

    @Test
    public void startTheTestServer() 
    {
        int port = 1234;
        String directory = "./workspaces/cse3310_sp24_group_7/html";
        HttpServer httpServer = new HttpServer(port, directory);
        httpServer.start();
        assertNotNull(httpServer);
    }
}