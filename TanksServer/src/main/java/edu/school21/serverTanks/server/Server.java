package edu.school21.serverTanks.server;

import edu.school21.serverTanks.gameLogic.BulletLogicHandler;
import edu.school21.serverTanks.gameLogic.PlayerActionHandler;
import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameData;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Server {
    private final int PORT = 8081;
    private GameData gameData;
    private ClientHandler clientHandlerOne;
    private ClientHandler clientHandlerTwo;
    private final BlockingQueue<ClientData> eventQueue  = new LinkedBlockingQueue<>();



    public void startServer() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket playerOne = serverSocket.accept();
        Socket playerTwo = serverSocket.accept();
        gameData = new GameData();
        clientHandlerOne = new ClientHandler(playerOne, gameData, 1, eventQueue);
        clientHandlerTwo = new ClientHandler(playerTwo, gameData, 2, eventQueue);
        new Thread(clientHandlerOne).start();
        new Thread(clientHandlerTwo).start();
        while (true) {
            ClientData event = eventQueue.poll(500, TimeUnit.MILLISECONDS);
            if (event != null) {
                if(event.getId() == 2){
                    reverseDataForEnemy(gameData);
                }
            }
            PlayerActionHandler.handlePlayerAction(event, gameData);
            if (event != null) {
                if (event.getId() == 1) {
                    sendGameDataToClientOne(gameData);
                }
                if(event.getId() == 2){
                    sendGameDataToClientTwo(gameData);
                }
            }else{
                sendGameDataToClientOne(gameData);
            }
        }
    }

    private void sendGameDataToClientTwo(GameData gameData) throws IOException {
        clientHandlerTwo.sendMessageToPlayer(gameData);
        reverseDataForPlayer(gameData);
        clientHandlerOne.sendMessageToPlayer(gameData);
    }

    private void sendGameDataToClientOne(GameData gameData) throws IOException {
        clientHandlerOne.sendMessageToPlayer(gameData);
        reverseDataForPlayer(gameData);
        if (!gameData.getBulletListPlayer().isEmpty()) {
            System.out.println("client2 " + gameData.getBulletListPlayer().get(0).getY());
        }
        clientHandlerTwo.sendMessageToPlayer(gameData);
        reverseDataForEnemy(gameData);

    }

    private void reverseDataForEnemy(GameData gameData) {
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);
        swapList(gameData);
        invertBulletPositionPlayer(gameData);
    }
    private void reverseDataForPlayer(GameData gameData) {
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);
        swapList(gameData);
        invertBulletPositionEnemy(gameData);
    }
    private void invertPlayerPositions(GameData gameData) {
        gameData.getPositionPlayersX().set(1,  1025.00 - gameData.getPositionPlayersX().get(1) - 81.00 );
        gameData.getPositionPlayersX().set(0,  1025.00 - gameData.getPositionPlayersX().get(0) - 81.00 );
    }
    private void invertBulletPositionPlayer(GameData gameData) {
        gameData.getBulletListPlayer().forEach(bullet -> bullet.setY(1025 - bullet.getY() - 11));
        gameData.getBulletListEnemy().forEach(bullet -> bullet.setY(1025 - bullet.getY() - 11));
    }

    private void invertBulletPositionEnemy(GameData gameData) {
        gameData.getBulletListPlayer().forEach(bullet -> bullet.setY(1025 - bullet.getY() - 11));
        gameData.getBulletListEnemy().forEach(bullet -> bullet.setY(1025 - bullet.getY() - 11));
    }
    private void swapList(GameData gameData) {
        List<Bullet> temp = gameData.getBulletListPlayer();
        gameData.setBulletListPlayer(gameData.getBulletListEnemy());
        gameData.setBulletListEnemy(temp);

    }

}
