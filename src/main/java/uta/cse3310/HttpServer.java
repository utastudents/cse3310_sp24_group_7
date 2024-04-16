package uta.cse3310;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.freeutils.httpserver.HTTPServer;
import net.freeutils.httpserver.HTTPServer.ContextHandler;
import net.freeutils.httpserver.HTTPServer.FileContextHandler;
import net.freeutils.httpserver.HTTPServer.Request;
import net.freeutils.httpserver.HTTPServer.Response;
import net.freeutils.httpserver.HTTPServer.VirtualHost;
public class HttpServer 
{

    private static final String HTML = "./html";
    int port = 8080;
    String dirname = HTML;

    public HttpServer(int portNum, String dirName) 
    {
        System.out.println("creating http server port " + portNum);
        port = portNum;
        dirname = dirName;
    }

    public void start() 
    {
        System.out.println("in http server start");
        try {
            File dir = new File(dirname);
            if (!dir.canRead())
                throw new FileNotFoundException(dir.getAbsolutePath());

            HTTPServer server = new HTTPServer(port);
            VirtualHost host = server.getVirtualHost(null);
            host.setAllowGeneratedIndex(true);
            host.addContext("/", new FileContextHandler(dir));
            server.start();
            System.out.println("HTTPServer is listening on port " + port);
        } catch (Exception e) 
        {
            System.err.println("error: " + e);
        }
    }
}