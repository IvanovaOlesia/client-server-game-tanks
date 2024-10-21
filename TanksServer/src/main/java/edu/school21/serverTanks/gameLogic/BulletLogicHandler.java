package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.Bullet;
import edu.school21.serverTanks.model.GameData;

public class BulletLogicHandler {
    public static void spawnBullet(GameData playerData){
        Bullet bullet = new Bullet(playerData.getPositionPlayersX().get(0) + 38, 864);
        playerData.addBullet(bullet);
    }
}
