package edu.school21.serverTanks.server;

import edu.school21.serverTanks.gameLogic.PlayerActionHandler;
import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameConstants;
import edu.school21.serverTanks.model.GameData;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Server {
    private final int PORT = 8081;
    private GameData gameData;
    private ClientHandler clientHandlerOne;
    private ClientHandler clientHandlerTwo;



    public void startServer() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        List<Socket> clientList = new LinkedList<>();
        BlockingQueue<ClientData> eventQueue  = new LinkedBlockingQueue<>();
        while (true) {
            createConnection(serverSocket, clientList, eventQueue);
            inputDataProcessing(clientList, eventQueue);
        }
    }

    private void inputDataProcessing(List<Socket> clientList, BlockingQueue<ClientData> eventQueue) throws InterruptedException, IOException {
        while (clientList.size() > 1) {
            ClientData event = eventQueue.poll(100, TimeUnit.MILLISECONDS);
            if (event != null && event.getAction().equals("exit")) {
                clientList.get(event.getId() - 1).close();
                clientList.remove(event.getId() - 1);
            }else {
                if (event != null && event.getId() == 2) {
                    reverseData(gameData);
                }
                PlayerActionHandler.handlePlayerAction(event, gameData);
                sendGameData(event);

            }
        }
    }

    private void sendGameData(ClientData event) throws IOException {
        if (event != null) {
            if (event.getId() == 1) {
                sendGameDataToClientOne(gameData);
            }
            if (event.getId() == 2) {
                sendGameDataToClientTwo(gameData);
            }
        } else {
            sendGameDataToClientOne(gameData);
        }
    }

    private void createConnection(ServerSocket serverSocket, List<Socket> clientList, BlockingQueue<ClientData> eventQueue) throws IOException {
        while (clientList.size() < 2) {
            clientList.add(serverSocket.accept());
        }
        gameData = new GameData();
        clientHandlerOne = new ClientHandler(clientList.get(0), gameData, 1, eventQueue);
        clientHandlerTwo = new ClientHandler(clientList.get(1), gameData, 2, eventQueue);
        new Thread(clientHandlerOne).start();
        new Thread(clientHandlerTwo).start();
    }

    private void sendGameDataToClientTwo(GameData gameData) throws IOException {
        clientHandlerTwo.sendMessageToPlayer(gameData);
        reverseData(gameData);
        clientHandlerOne.sendMessageToPlayer(gameData);
    }

    private void sendGameDataToClientOne(GameData gameData) throws IOException {
        clientHandlerOne.sendMessageToPlayer(gameData);
        reverseData(gameData);
        clientHandlerTwo.sendMessageToPlayer(gameData);
        reverseData(gameData);

    }

    private void reverseData(GameData gameData) {
        Collections.reverse(gameData.getPositionPlayersX());
        invertPlayerPositions(gameData);
        swapList(gameData);
        invertBulletPosition(gameData);
        invertHp(gameData);
        gameData.setPlayerWin(!gameData.isPlayerWin());
    }

    private void invertHp(GameData gameData) {
        double temp = gameData.getHealthPlayer();
        gameData.setHealthPlayer(gameData.getHealthEnemy());
        gameData.setHealthEnemy(temp);
    }

    private void invertPlayerPositions(GameData gameData) {
        gameData.getPositionPlayersX().set(1,  GameConstants.MAX_X - gameData.getPositionPlayersX().get(1) - GameConstants.TANK_WIDTH );
        gameData.getPositionPlayersX().set(0,  GameConstants.MAX_X - gameData.getPositionPlayersX().get(0) - GameConstants.TANK_WIDTH );
    }
    private void invertBulletPosition(GameData gameData) {
        gameData.getBulletListPlayer().forEach(bullet -> bullet.setX(GameConstants.MAX_X - bullet.getX() - GameConstants.BULLET_WIDTH));
        gameData.getBulletListPlayer().forEach(bullet -> bullet.setY(GameConstants.MAX_Y  - bullet.getY() - GameConstants.BULLET_LENGTH));
        gameData.getBulletListEnemy().forEach(bullet -> bullet.setX(GameConstants.MAX_X - bullet.getX() - GameConstants.BULLET_WIDTH));
        gameData.getBulletListEnemy().forEach(bullet -> bullet.setY(GameConstants.MAX_Y - bullet.getY() - GameConstants.BULLET_LENGTH));
    }


    private void swapList(GameData gameData) {
        List<Bullet> temp = gameData.getBulletListPlayer();
        gameData.setBulletListPlayer(gameData.getBulletListEnemy());
        gameData.setBulletListEnemy(temp);

    }

}
