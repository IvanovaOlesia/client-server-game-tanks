package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.GameData;
import edu.school21.serverTanks.server.ClientData;

public class PlayerActionHandler {
    private PlayerActionHandler() {}

    private static long lastUpdateTime = System.currentTimeMillis();
    public static  void handlePlayerAction(ClientData action, GameData playerData)  {
        if (action != null) {
            if (action.getAction().equals("LEFT")) {
                PlayerMovementHandler.moveLeft(playerData);
            } else if (action.getAction().equals("RIGHT")) {
                PlayerMovementHandler.moveRight(playerData);
            }
            else if (action.getAction().equals("SPACE")) {
                BulletLogicHandler.spawnBullet(playerData);
            }
        }
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastUpdateTime;
        if (elapsedTime >= 100) {
            BulletLogicHandler.moveBulletPlayer(playerData);
            BulletLogicHandler.moveBulletEnemy(playerData);
            lastUpdateTime = currentTime;
            playerData.setDeleteBullet(true);
        }else{
            playerData.setDeleteBullet(false);
        }

    }



}
