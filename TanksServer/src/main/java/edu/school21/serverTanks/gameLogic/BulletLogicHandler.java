package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameConstants;
import edu.school21.serverTanks.model.GameData;

public class BulletLogicHandler {
    private BulletLogicHandler() {}
    public static void spawnBullet(GameData gameData) {
        Bullet bullet = new Bullet(gameData.getPositionPlayersX().get(0) + 29, GameConstants.PLAYER_Y_POSITION - GameConstants.MUZZLE_LENGTH);
        gameData.addBulletPlayer(bullet);
    }
    public static void moveBulletPlayer(GameData gameData) {
        gameData.getBulletListPlayer().removeIf(bullet -> {
        if (CollisionDetector.checkBulletBoundaryUp(bullet) && !CollisionDetector.checkBulletCollisionWithEnemyTank(gameData, bullet)) {            bullet.setY(bullet.getY() - 10.00);
            return false;
        } else if(CollisionDetector.checkBulletCollisionWithEnemyTank(gameData, bullet)) {
            gameData.setHealthEnemy(gameData.getHealthEnemy() - 0.1);
            if (gameData.getHealthEnemy() <= 0) {
                gameData.setPlayerWin(true);
            }
            return true;
        }else{
            return true;
        }
    });}
    public static void moveBulletEnemy(GameData gameData) {
        gameData.getBulletListEnemy().removeIf(bullet -> {
            if(CollisionDetector.checkBulletBoundaryDown(bullet) && !CollisionDetector.checkBulletCollisionWithPlayerTank(gameData, bullet)) {
            bullet.setY(bullet.getY() + 10.00);
            return false;
        }else if (CollisionDetector.checkBulletCollisionWithPlayerTank(gameData, bullet)){
                gameData.setHealthPlayer(gameData.getHealthPlayer() - 0.1);
                if (gameData.getHealthPlayer() <= 0) {
                    gameData.setEnemyWin(true);
                }
            return true;
            }else{
            return true;
            }
        });
    }

}
