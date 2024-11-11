package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameConstants;
import edu.school21.serverTanks.model.GameData;

public class CollisionDetector {
    private CollisionDetector() {}
    public static boolean checkTankBoundaryLeft(GameData gameData){
        return (gameData.getPositionPlayersX().get(0) - GameConstants.TANK_MOVEMENT_OFFSET >= 0.00);
    }
    public static boolean checkTankBoundaryRight(GameData gameData){
        return (gameData.getPositionPlayersX().get(0) + GameConstants.TANK_WIDTH + GameConstants.TANK_MOVEMENT_OFFSET <= GameConstants.FIELD_WIDTH);
    }
    public static boolean checkBulletBoundaryUp(Bullet bullet){
        return (bullet.getY() - GameConstants.MUZZLE_LENGTH >= 0.00);
    }

    public static boolean checkBulletBoundaryDown(Bullet bullet){
        return (bullet.getY() + GameConstants.BULLET_LENGTH+ GameConstants.MUZZLE_LENGTH <= GameConstants.FIELD_WIDTH);
    }
    public static boolean checkBulletCollisionWithPlayerTank(GameData gameData, Bullet bullet){
        return (bullet.getX() >= gameData.getPositionPlayersX().get(0)) &&
                (bullet.getX() <= gameData.getPositionPlayersX().get(0) + GameConstants.TANK_WIDTH ) &&
                (bullet.getY() + GameConstants.BULLET_LENGTH >= GameConstants.PLAYER_Y_POSITION );
    }

    public static boolean checkBulletCollisionWithEnemyTank(GameData gameData, Bullet bullet){
        return (bullet.getX() >= gameData.getPositionPlayersX().get(1)) &&
                (bullet.getX() <= gameData.getPositionPlayersX().get(1) + GameConstants.TANK_WIDTH) &&
                (bullet.getY() <= GameConstants.ENEMY_Y_POSITION );
    }
}
