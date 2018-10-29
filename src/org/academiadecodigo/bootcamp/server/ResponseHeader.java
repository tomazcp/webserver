package org.academiadecodigo.bootcamp.server;

public class ResponseHeader {

    private int statusCode;
    private String message;
    private long fileSize;
    private String contentType;

    ResponseHeader(int statusCode, String message, long fileSize, String contentType) {
        this.statusCode = statusCode;
        this.message = message;
        this.fileSize = fileSize;
        this.contentType = contentType;
    }

    ResponseHeader(int statusCode, long fileSize, String message) {
        this.statusCode = statusCode;
        this.fileSize = fileSize;
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "HTTP/1.0 " + statusCode + " " + message + "\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + fileSize + " \r\n" + "\r\n";
    }
}
