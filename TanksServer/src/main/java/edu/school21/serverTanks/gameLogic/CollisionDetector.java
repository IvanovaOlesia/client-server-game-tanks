package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameData;

public class CollisionDetector {
    public static boolean checkTankBoundaryLeft(GameData gameData){
        return (gameData.getPositionPlayersX().get(0) - 10.00 >= 0.00);
    }
    public static boolean checkTankBoundaryRight(GameData gameData){
        return (gameData.getPositionPlayersX().get(0) + 81.00 + 10.00 <= 1024.00);
    }
    public static boolean checkBulletBoundaryUp(Bullet bullet){
        return (bullet.getY() - 20.00 >= 0.00);
    }

    public static boolean checkBulletBoundaryDown(Bullet bullet){
        return (bullet.getY() + 11.00 + 20.00 <= 1024);
    }
    public static boolean checkBulletCollisionWithPlayerTank(GameData gameData, Bullet bullet){
        return (bullet.getX() >= gameData.getPositionPlayersX().get(0)) &&
                (bullet.getX() <= gameData.getPositionPlayersX().get(0) + 81.00) &&
                (bullet.getY() + 11.00 >= 860);
    }

    public static boolean checkBulletCollisionWithEnemyTank(GameData gameData, Bullet bullet){
        return (bullet.getX() >= gameData.getPositionPlayersX().get(1)) &&
                (bullet.getX() <= gameData.getPositionPlayersX().get(1) + 81.00) &&
                (bullet.getY() <= 30);
    }
}
