package org.academiadecodigo.bootcamp.server;

public enum RequestType {
    GET, POST, PUT, INVALID_VERB;

    public static RequestType getRequestType(String type) {
        RequestType requestType = null;
        switch (type) {
            case "GET":
                requestType = RequestType.GET;
                break;

            case "POST":
                requestType = RequestType.POST;
                break;

            case "PUT":
                requestType = RequestType.PUT;
                break;

            default:
                requestType = RequestType.INVALID_VERB;
                break;
        }
        return requestType;
    }
}
