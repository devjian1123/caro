package main.java.me.devjian.caro.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static int loadPortFromConfig() {
        Properties properties = new Properties();
        int port = 8080;

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
            port = Integer.parseInt(properties.getProperty("server.port"));
        } catch (IOException ex) {
            System.err.println("Could not read config file: " + ex.getMessage());
        }

        return port;
    }
}
