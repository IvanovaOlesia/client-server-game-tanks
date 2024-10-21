package edu.school21.serverTanks.app;


import edu.school21.serverTanks.server.Server;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.startServer();
    }
}
