package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.server.WebServer;

public class Main {

    public static void main(String[] args) {
        int port = 8080;
        WebServer webServer = new WebServer(port);
        webServer.start();
    }
}
