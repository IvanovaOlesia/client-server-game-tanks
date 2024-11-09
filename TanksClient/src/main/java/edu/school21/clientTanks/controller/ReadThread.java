package edu.school21.clientTanks.controller;

import edu.school21.clientTanks.view.RenderBullet;
import edu.school21.clientTanks.view.View;
import edu.school21.clientTanks.JSONModel.GameData;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;
import javafx.application.Platform;

public class ReadThread implements Runnable{
    private final Scanner in;
    private final View view;
    private BlockingQueue<GameData> queue;

    public ReadThread(Scanner in, View view) {
        this.in = in;
        this.view = view;
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        new Thread(new RenderBullet(view,queue)).start();
        while (in.hasNext()){
            GameData playerData = new Gson().fromJson(in.nextLine(), GameData.class);
            try {
                queue.put(playerData);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() -> {
                    try {
                        view.renderNewData(playerData);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }
}
