package main.java.me.devjian.caro.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler {
    public static void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String requestLine = in.readLine();
        System.out.println("Received: " + requestLine);

        while (!in.readLine().isEmpty()) {

        }

        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: 13\r\n" +
                "\r\n" +
                "Hello, World!";
        out.write(httpResponse);
        out.flush();
        clientSocket.close();
    }
}
