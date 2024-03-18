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

public class HttpServer {

    // Directory where HTML files are stored \\
    private static final String HTML = "./html";
    int port = 8080;
    String dirname = HTML;

    // Constructor to initialize the HttpServer \\
    public HttpServer(int portNum, String dirName) {
        // Initialize port number and directory name \\
        this.port = portNum;
        this.dirname = dirName;
    }

    
    public void start() {
        // Implementation for starting the HTTP server added here
    }
}