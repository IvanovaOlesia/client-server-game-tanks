package edu.school21.clientTanks.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.io.BufferedWriter;
import java.io.IOException;
public class WriteThread implements Runnable{
    private BufferedWriter out;
    private Scene scene;
    private boolean canShot;
    public WriteThread(BufferedWriter out, Scene scene) {
        this.out = out;
        this.scene = scene;
        canShot = true;
    }
    @Override
    public void run() {
        preventHoldKey();
        while (true){
            scene.setOnKeyPressed(event -> {
               if (event.getCode() == KeyCode.SPACE && canShot){
                   try {
                       sendActionToServer(event.getCode().toString());
                       canShot = false;
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               } else if (event.getCode() != KeyCode.SPACE) {
                   try {
                       sendActionToServer(event.getCode().toString());
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
            });
        }

    }

    private void preventHoldKey() {
        scene.setOnKeyReleased(event ->{
            if (event.getCode() == KeyCode.SPACE){
                canShot = true;
            }
        } );
    }
    private void sendActionToServer(String action) throws IOException {
        out.write(action);
        out.newLine();
        out.flush();

    }
}
