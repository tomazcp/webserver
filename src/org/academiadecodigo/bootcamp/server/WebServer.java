package org.academiadecodigo.bootcamp.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WebServer {

    private static final String DOCUMENT_ROOT = "www/";

    private int port;
    private ServerSocket socket;

    public WebServer(int port) {
        this.port = port;
    }

    /**
     * Starts the server
     */
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

    /**
     * Listens for requests
     */
    private void listen() {
        try {
            Socket clientSocket = socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Request req = newRequest(reader.readLine());
            Response res = processRequest(req);
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            send(res, out);
            clientSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates a new request object from the incoming header
     *
     * @param header the request header
     * @return
     */
    private Request newRequest(String header) {
        String[] headerInfo = header.split(" ");
        return new Request(headerInfo[0], headerInfo[1]);
    }

    /**
     *
     * @param req
     * @return
     */
    private Response processRequest(Request req) {
        Response res = null;
        switch (req.getRequestType()) {
            case GET:
                File file = fetchResources(req.getResource());
                if (file.exists()) {
                    ResponseHeader responseHeader =
                            new ResponseHeader(
                                    200, file.length(), "Document Follows");

                    String extension = file.getName().substring(file.getName().indexOf(".") + 1);
                    responseHeader.setContentType(ContentTypeMapper.getContentType(extension));
                    res = new Response(fileToBytes(file), responseHeader);
                }
                break;

            default:
                System.out.println("default");
                break;
        }
        return res;
    }

    private void send(Response res, DataOutputStream out) throws IOException {
        out.writeBytes(res.getResponseHeader().toString());
        out.write(res.getData());
        out.close();
    }

    /**
     * Replace with
     * //Pattern pattern = Pattern.compile("(\\.[^.]+)$");
     * //Matcher matcher = pattern.matcher(filePath);
     *
     * @param resource
     * @return
     */
    private File fetchResources(String resource) {
        File file;
        if (resource.lastIndexOf(".") != -1) {
            file = new File(DOCUMENT_ROOT + resource);
        } else {
            file = new File(DOCUMENT_ROOT + resource + ".html");
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
