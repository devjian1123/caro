package main.java.me.devjian.caro.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    public static void handleRequest(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ResponseHandler responseHandler = new ResponseHandler(out);

        String requestLine = in.readLine();
        System.out.println("Received: " + requestLine);

        if (requestLine == null || requestLine.isEmpty()) {
            responseHandler.sendErrorResponse(400, "Bad Request");
            return;
        }

        String[] requestParts = requestLine.split(" ");
        if (requestParts.length < 3) {
            responseHandler.sendErrorResponse(400, "Bad Request");
            return;
        }

        String method = requestParts[0];
        String path = requestParts[1];
        String protocol = requestParts[2];

        // 헤더 파싱
        Map<String, String> headers = new HashMap<>();
        String headerLine;
        while (!(headerLine = in.readLine()).isEmpty()) {
            String[] headerParts = headerLine.split(": ", 2);
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            }
        }

        // 경로에 따른 간단한 응답
        if ("/test".equals(path)) {
            responseHandler.sendResponse(200, "This is Test Controller!");
        } else if ("/time".equals(path)) {
            String currentTime = LocalDateTime.now().toString();
            responseHandler.sendResponse(200, "Current time is: " + currentTime);
        } else {
            responseHandler.sendErrorResponse(404, "Not Found");
        }

        clientSocket.close();
    }
}
