package org.raspberryJ.client.connection;

import org.raspberryJ.api.Connection;
import org.raspberryJ.api.Message;

public class PingWorker {

    private Thread thread;
    private Connection connection;
    private int timeOut = 5000;
    private Message.Transport.Builder transport = Message.Transport.newBuilder();
    private int lastAttempt = 1;
    private int lastResponse = 0;

    public PingWorker(Connection connection) {
        this(connection, 5000);
    }

    public PingWorker(Connection connection, int timeOut) {
        this.connection = connection;
        this.timeOut = timeOut;
        this.thread = new Thread(() -> {
            while (true) {
                Message.PingRequest.Builder pinRequest = Message.PingRequest.newBuilder();
                pinRequest.setAttempt(lastAttempt++);
                pinRequest.setRequestTime(System.currentTimeMillis());
                transport.setPingRequest(pinRequest);
                try {
                    connection.sendMessage(transport.build());
                    Thread.sleep(timeOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void registerPingResponse(Message.PingResponse pingResponse) {
        lastResponse = pingResponse.getPingRequest().getAttempt();
    }

    public int getLastAttempt() {
        return lastAttempt;
    }

    public int getLastResponse() {
        return lastResponse;
    }
}
