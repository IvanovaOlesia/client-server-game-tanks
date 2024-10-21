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
    private ClientHandler clientHandlerOne;
    private ClientHandler clientHandlerTwo;
    private final BlockingQueue<ClientData> eventQueue  = new LinkedBlockingQueue<>();



    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket playerOne = serverSocket.accept();
        Socket playerTwo = serverSocket.accept();
        gameData = new GameData();
        clientHandlerOne = new ClientHandler(playerOne, gameData, 1, eventQueue);
        clientHandlerTwo = new ClientHandler(playerTwo, gameData, 2, eventQueue);
        new Thread(clientHandlerOne).start();
        new Thread(clientHandlerTwo).start();
        while (true) {
            ClientData event = eventQueue.poll();
            if (event != null) {
                if (event.getId() == 1) {
                    handlerActionClientOne(event,gameData );
                }
                if(event.getId() == 2){
                    handlerActionClientTwo(event,gameData);
                }
            }
        }
    }

    private void handlerActionClientTwo(ClientData event, GameData gameData) throws IOException {
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);
        PlayerActionHandler.handlePlayerAction(event, gameData);
        clientHandlerTwo.sendMessageToPlayer(gameData);
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);
        clientHandlerOne.sendMessageToPlayer(gameData);
    }



    private void handlerActionClientOne(ClientData event,GameData gameData) throws IOException {
        PlayerActionHandler.handlePlayerAction(event, gameData);
        clientHandlerOne.sendMessageToPlayer(gameData);
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);
        clientHandlerTwo.sendMessageToPlayer(gameData);
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);

    }
    private void invertPlayerPositions(GameData gameData) {
        gameData.getPositionPlayersX().set(1,  1025.00 - gameData.getPositionPlayersX().get(1) - 81.00 );
        gameData.getPositionPlayersX().set(0,  1025.00 - gameData.getPositionPlayersX().get(0) - 81.00 );
    }

}
