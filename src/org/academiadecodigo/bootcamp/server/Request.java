package org.academiadecodigo.bootcamp.server;

class Request {

    private RequestType requestType;
    private String resource;

    Request(String requestType, String resource) {
        this.requestType = RequestType.getRequestType(requestType);
        if (resource.equals("/")) {
            this.resource = "/index";
        } else {
            this.resource = resource;
        }
    }

    String getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return "requestType: " + requestType +
                "\nresource: " + resource;
    }

    RequestType getRequestType() {
        return requestType;
    }
}
