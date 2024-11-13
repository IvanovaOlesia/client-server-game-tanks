package edu.school21.serverTanks.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ActionHandler implements Runnable {
    private BufferedReader playerBufferedReader;
    private BlockingQueue<String> queue;

    public ActionHandler(BufferedReader playerBufferedReader, BlockingQueue<String> queue) {
        this.playerBufferedReader = playerBufferedReader;
        this.queue = queue;
    }

    @Override
    public void run() {
                try {
                    while (true) {
                       String action = playerBufferedReader.readLine();
                        if (action == null) {
                            queue.put("exit");
                            break;
                        }else {
                            queue.put(action);
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

    }
}
