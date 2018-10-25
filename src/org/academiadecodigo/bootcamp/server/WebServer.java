package org.academiadecodigo.bootcamp.server;

import java.io.IOException;
import java.net.ServerSocket;


public class WebServer {

    private int serverPort;

    public WebServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
