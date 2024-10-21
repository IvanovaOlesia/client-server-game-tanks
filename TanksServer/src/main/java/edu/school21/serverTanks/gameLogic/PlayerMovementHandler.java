package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.GameData;

public class PlayerMovementHandler {
    public static void moveLeft(GameData playerData){
        playerData.addPosition(playerData.getPositionPlayersX().get(0) - 10.00, 0);
    }
    public static void moveRight(GameData playerData){
        playerData.addPosition(playerData.getPositionPlayersX().get(0) + 10.00, 0);
    }
}
