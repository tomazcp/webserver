package org.academiadecodigo.bootcamp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {

    private int serverPort;

    public WebServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(serverPort);
            clientSocket = serverSocket.accept();

            while (!serverSocket.isClosed()) {

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
