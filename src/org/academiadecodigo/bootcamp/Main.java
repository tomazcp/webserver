package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.server.Server;

public class Main {

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        server.start();
    }
}
