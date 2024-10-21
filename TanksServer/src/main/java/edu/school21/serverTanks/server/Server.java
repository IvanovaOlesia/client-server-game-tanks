package edu.school21.serverTanks.server;

import edu.school21.serverTanks.gameLogic.PlayerActionHandler;
import edu.school21.serverTanks.model.GameData;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {
    private final int PORT = 8081;
    private GameData gameData;
    private final BlockingQueue<ClientData> eventQueue  = new LinkedBlockingQueue<>();



    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket playerOne = serverSocket.accept();
        Socket playerTwo = serverSocket.accept();
        gameData = new GameData();
        ClientHandler clientHandlerOne = new ClientHandler(playerOne, gameData, 1);
        ClientHandler clientHandlerTwo = new ClientHandler(playerTwo, gameData, 2);
        new Thread(clientHandlerOne).start();
        new Thread(clientHandlerTwo).start();
        while (true) {
            ClientData event = eventQueue.poll();
            if (event != null) {
                if (event.getId() == 1) {
                    PlayerActionHandler.handlePlayerAction(event, gameData);
                    clientHandlerOne.sendMessageToPlayer(gameData);
                    Collections.reverse(gameData.getPositionPlayersX());
                    gameData.getPositionPlayersX().set(1,  1025.00 - gameData.getPositionPlayersX().get(1) - 81.00 );
                    gameData.getPositionPlayersX().set(0,  1025.00 - gameData.getPositionPlayersX().get(0) - 81.00 );

                    clientHandlerTwo.sendMessageToPlayer(gameData);
                    Collections.reverse(gameData.getPositionPlayersX());
                    gameData.getPositionPlayersX().set(0,  1025.00 - gameData.getPositionPlayersX().get(0) - 81.00 );
                    gameData.getPositionPlayersX().set(1,  1025.00 - gameData.getPositionPlayersX().get(1) - 81.00 );


                }
                if(event.getId() == 2){
                    Collections.reverse(gameData.getPositionPlayersX());
                    gameData.getPositionPlayersX().set(1,  1025.00 - gameData.getPositionPlayersX().get(1) - 81.00 );
                    gameData.getPositionPlayersX().set(0,  1025.00 - gameData.getPositionPlayersX().get(0) - 81.00 );
                    PlayerActionHandler.handlePlayerAction(event, gameData);
                    clientHandlerTwo.sendMessageToPlayer(gameData);
                    Collections.reverse(gameData.getPositionPlayersX());
                    gameData.getPositionPlayersX().set(0,  1025.00 - gameData.getPositionPlayersX().get(0) - 81.00 );
                    gameData.getPositionPlayersX().set(1,  1025.00 - gameData.getPositionPlayersX().get(1) - 81.00 );
                    clientHandlerOne.sendMessageToPlayer(gameData);
                }
            }
        }
    }


}
