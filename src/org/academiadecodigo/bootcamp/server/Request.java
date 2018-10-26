package org.academiadecodigo.bootcamp.server;

public class Request {

    private String verb;
    private String route;

    Request(String verb, String route) {
        this.verb = verb;
        this.route = route;
    }

    public String getVerb() {
        return verb;
    }

    public String getRoute() {
        return route;
    }
}
