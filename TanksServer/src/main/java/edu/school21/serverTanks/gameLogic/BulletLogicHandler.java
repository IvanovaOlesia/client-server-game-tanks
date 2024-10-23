package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameData;

public class BulletLogicHandler {
    public static void spawnBullet(GameData gameData) {
        Bullet bullet = new Bullet(gameData.getPositionPlayersX().get(0) + 38, 864);
        gameData.addBulletPlayer(bullet);
    }

    public static void moveBulletPlayer(GameData gameData) {
        gameData.getBulletListPlayer().forEach(bullet -> {
            if(CollisionDetector.checkBulletBoundaryUp(bullet)) {
                if (!CollisionDetector.checkBulletCollisionWithEnemyTank(gameData, bullet)) {
                    bullet.setY(bullet.getY() - 20.00);
                }
            }
        });
    }

    public static void moveBulletEnemy(GameData gameData) {
        gameData.getBulletListEnemy().forEach(bullet -> {
            if(CollisionDetector.checkBulletBoundaryDown(bullet)) {
                if (!CollisionDetector.checkBulletCollisionWithPlayerTank(gameData, bullet)) {
                    bullet.setY(bullet.getY() + 20.00);
                }
            }
        });
    }
}
