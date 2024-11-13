package edu.school21.serverTanks.server;

import com.google.gson.Gson;
import edu.school21.serverTanks.model.GameData;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ClientHandler implements Runnable{
    private GameData playerData;
    private BufferedReader playerBufferedReader;
    private final BufferedWriter playerBufferedWriter;
    private final BlockingQueue<String> queue;
    private final BlockingQueue<ClientData> eventQueue;
    private final ActionHandler actionHandler;
    private Integer id;

    public ClientHandler(Socket player, GameData playerData, Integer id, BlockingQueue<ClientData> eventQueue) throws IOException {
        this.playerBufferedReader = new BufferedReader(new InputStreamReader(player.getInputStream()));
        this.playerBufferedWriter = new BufferedWriter(new OutputStreamWriter(player.getOutputStream()));
        this.playerData = playerData;
        this.queue = new LinkedBlockingQueue<>();
        this.eventQueue = eventQueue;
        actionHandler = new ActionHandler(playerBufferedReader ,queue);
        this.id = id;
    }


    @Override
    public void run() {
        try {
            new Thread(actionHandler).start();
            sendMessageToPlayer(playerData);
            String action = "run";
            while (!action.equals("exit")){
                action = queue.take();
                eventQueue.put(new ClientData(action,id));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToPlayer(GameData playerData) throws IOException {
        String json = new Gson().toJson(playerData);
        playerBufferedWriter.write(json);
        playerBufferedWriter.newLine();
        playerBufferedWriter.flush();
    }
}
