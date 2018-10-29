package org.academiadecodigo.bootcamp.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static final String DIR = "www";

    private int port;
    private ServerSocket socket;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            socket = new ServerSocket(port);

            while (true) {
                listen();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void listen() {
        try {
            Socket clientSocket = socket.accept();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            Request req = newRequest(reader.readLine());

            //System.out.println(req.toString());

            Response res = processRequest(req);
            //System.out.println(res.getResponseHeader().toString());
            send(res, clientSocket);

            clientSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private Request newRequest(String header) {
        String[] headerInfo = header.split(" ");

        return new Request(headerInfo[0], headerInfo[1]);
    }

    private Response processRequest(Request req) {
        Response res = null;
        //System.out.println(req.getResource());
        switch (req.getRequestType()) {
            case GET:
                File file = fetchResources(req.getResource());
                System.out.println(file.getName());
                if (file.exists()) {
                    ResponseHeader responseHeader =
                            new ResponseHeader(
                                    200, file.length(), "Document Follows");

                    String extension = file.getName().substring(file.getName().indexOf(".") + 1);

                    responseHeader.setContentType(
                            ContentTypeMapper.getContentType(extension));

                    res = new Response(fileToBytes(file), responseHeader);

                }
                break;

            default:
                System.out.println("default");
                break;
        }
        //System.out.println(res);
        return res;
    }

    private void send(Response res, Socket clientSocket) {
        try (DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            out.writeBytes(res.getResponseHeader().toString());
            out.write(res.getData());

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private File fetchResources(String resource) {
        File file;
        if (!resource.matches("\\.")) {
            file = new File(DIR + resource + ".html");
        } else {
            file = new File(DIR + resource);
        }
        return file;
    }

    private byte[] fileToBytes(File file) {
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            fileInputStream.read(buffer);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return buffer;
    }
}
