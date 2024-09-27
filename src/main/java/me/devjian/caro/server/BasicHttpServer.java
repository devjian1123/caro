package main.java.me.devjian.caro.server;

import main.java.me.devjian.caro.config.ConfigLoader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicHttpServer {
    public static void main(String[] args) {
        int port = ConfigLoader.loadPortFromConfig();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: [" + port + "]");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    RequestHandler.handleRequest(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error handling client request: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println();
        }
    }
}
