package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.GameConstants;
import edu.school21.serverTanks.model.GameData;

public class PlayerMovementHandler {
    private PlayerMovementHandler() {}
    public static void moveLeft(GameData gameData){
        if(CollisionDetector.checkTankBoundaryLeft(gameData)){
            gameData.addPosition(gameData.getPositionPlayersX().get(0) - GameConstants.TANK_MOVEMENT_OFFSET, 0);
        }
    }
    public static void moveRight(GameData gameData){
        if(CollisionDetector.checkTankBoundaryRight(gameData)){
            gameData.addPosition(gameData.getPositionPlayersX().get(0) + GameConstants.TANK_MOVEMENT_OFFSET, 0);
        }
    }
}
