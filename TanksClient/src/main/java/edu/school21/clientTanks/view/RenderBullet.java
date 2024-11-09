package edu.school21.clientTanks.view;

import edu.school21.clientTanks.JSONModel.GameData;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RenderBullet implements Runnable {
    private View view;
    private BlockingQueue<GameData> dataQueue;
    private GameData gameData;


    public RenderBullet(View view, BlockingQueue<GameData> queue) {
        this.dataQueue = queue;
        this.view = view;
    }


    @Override
    public void run() {
        while (true) {
            try {
                gameData = dataQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

                Platform.runLater(() -> {
                    view.moveBullet(gameData);
                });
        }
    }
}
