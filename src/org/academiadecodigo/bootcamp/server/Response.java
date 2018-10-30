package org.academiadecodigo.bootcamp.server;

public class Response {

    private ResponseHeader responseHeader;
    private byte[] data;

    Response(byte[] data, ResponseHeader responseHeader) {
        this.data = data;
        this.responseHeader = responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return responseHeader.toString() +
                "data: " + data.length;
    }
}
