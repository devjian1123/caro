package main.java.me.devjian.caro.server;

import java.io.PrintWriter;

public class ResponseHandler {
    private final PrintWriter out;

    public ResponseHandler(PrintWriter out) {
        this.out = out;
    }

    public void sendResponse(int statusCode, String body) {
        String statusText = (statusCode == 200) ? "OK" : "Error";
        out.println("HTTP/1.1 " + statusCode + " " + statusText);
        out.println("Content-Type: text/plain");
        out.println("Content-Length: " + body.length());
        out.println();
        out.println(body);
        out.flush();
    }

    public void sendErrorResponse(int statusCode, String message) {
        sendResponse(statusCode, message);
    }
}
