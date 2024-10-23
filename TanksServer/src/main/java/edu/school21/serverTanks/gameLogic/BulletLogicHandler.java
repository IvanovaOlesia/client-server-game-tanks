package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameData;

public class BulletLogicHandler {
    public static void spawnBullet(GameData playerData){
        Bullet bullet = new Bullet(playerData.getPositionPlayersX().get(0) + 38, 864);
        playerData.addBulletPlayer(bullet);
    }
    public static void  moveBulletPlayer(GameData playerData){
        playerData.getBulletListPlayer().forEach(bullet -> {
            bullet.setY(bullet.getY() - 10.00);
        });
    }
    public static void  moveBulletEnemy(GameData playerData){
        playerData.getBulletListEnemy().forEach(bullet -> {
            bullet.setY(bullet.getY() + 10.00);
        });
    }
}
