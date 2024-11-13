package edu.school21.clientTanks.controller;

import edu.school21.clientTanks.view.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    private final int PORT = 8081;
    private Scene scene;
    private View view;
    private Socket client;
    private Scanner in;
    private BufferedWriter out;
    private Stage stage;
    public Controller(View view, Scene scene, Stage stage) throws IOException {
        this.view = view;
        this.scene = scene;
        this.stage = stage;
        this.client = new Socket("localhost", PORT);
        this.in = new Scanner(new InputStreamReader(client.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    }
    public void startClient(){
        ReadThread readThread = new ReadThread(in, view);
        WriteThread writeThread = new WriteThread(out, scene);
        new Thread(readThread).start();
        new Thread(writeThread).start();
        stage.setOnCloseRequest(event -> {
            try {
                client.close();
                in.close();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
