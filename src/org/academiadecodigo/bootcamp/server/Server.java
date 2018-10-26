package org.academiadecodigo.bootcamp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private int port;
    private ServerSocket socket;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        //BufferedReader bufferedReader = null;
        try {
            socket = new ServerSocket(port);
            while (true) {
                listen();
            }
            //bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void listen() {
        Request req = null;
        try {
            Socket clientSocket = socket.accept();
            System.out.println("caralho");
            if (!clientSocket.isClosed()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                req = createRequest(reader.readLine());

                clientSocket.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Request createRequest(String header) {
        String[] headerInfo = header.split(" ");

        return new Request(headerInfo[0], headerInfo[1]);
    }
}
