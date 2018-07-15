package org.raspberryJ.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketConnection implements Connection {

    private Socket socket;
    private Thread worker;
    private Runnable runnable;
    private MessageCallback callback;
    private OutputStream outputStream;
    private InputStream inputStream;
    private long id;

    public SocketConnection(String host, int port, MessageCallback callback) throws IOException {
        this(new Socket(host, port), callback);
    }

    public SocketConnection(Socket socket, MessageCallback callback) {
        this.socket = socket;
        this.callback = callback;
        init();
    }

    private void init() {
        this.id = System.currentTimeMillis();
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        runnable = () -> {
            try {
                inputStream = socket.getInputStream();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            while (true) {
                try {
                    if(socket.isClosed()) {
                        break;
                    }
                    Message.Transport transport = Message.Transport.parseDelimitedFrom(inputStream);
                    this.callback.process(transport);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        callback.setConnection(this);
    }

    @Override
    public void startConnection() {
        worker = new Thread(runnable);
        worker.start();
    }

    @Override
    public void stopConnection() throws Exception {
        worker.interrupt();
        socket.close();
    }

    @Override
    public void sendMessage(Message.Transport transport) throws Exception{
        System.out.println("Sending " + id + " :" + transport.toString());
        transport.writeDelimitedTo(outputStream);
    }

    @Override
    public void setCallback(MessageCallback callback) {
        this.callback = callback;
    }

    @Override
    public long getConnectionId() {
        return this.id;
    }

    @Override
    public int status() {
        if (socket.isClosed())
            return 2;
        if(socket.isConnected())
            return 1;
        return 3;
    }
}
