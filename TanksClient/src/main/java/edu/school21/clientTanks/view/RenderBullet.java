package edu.school21.clientTanks.view;

import edu.school21.clientTanks.JSONModel.GameData;
import javafx.scene.Scene;

import java.util.concurrent.BlockingQueue;

public class RenderBullet implements Runnable {
    private View view;
    private BlockingQueue<GameData> dataQueue;
    private GameData gameData;

    public RenderBullet(View view) {
        this.view = view;
    }

    @Override
    public void run() {
//        view.moveBullet();

    }
}
