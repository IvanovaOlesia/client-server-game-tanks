package edu.school21.serverTanks.gameLogic;

import edu.school21.serverTanks.model.GameData;
import edu.school21.serverTanks.server.ClientData;

public class PlayerActionHandler {
    public static  void handlePlayerAction(ClientData action, GameData playerData)  {
        if (action != null) {
            if (action.getAction().equals("LEFT")) {
                PlayerMovementHandler.moveLeft(playerData);
            } else if (action.getAction().equals("RIGHT")) {
                PlayerMovementHandler.moveRight(playerData);
            }
        }
    }



}
