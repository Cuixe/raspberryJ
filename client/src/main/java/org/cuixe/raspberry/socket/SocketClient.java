package org.cuixe.raspberry.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClient implements ClientConnection {

    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private Thread worker;

    public SocketClient (String ip, int port, String name, MessageCallback callback) throws Exception{
        socket = new Socket(ip, port);
        worker = new Thread(() -> {
            while(socket.isConnected()) {
                try {
                    int data = inputStream.read();
                    callback.processMessage(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void startConnection() throws Exception {
        outputStream = new DataOutputStream(socket.getOutputStream());
        inputStream = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void stopConnection() throws Exception {

    }

    public void sendMessage(Object message)  throws Exception{
        outputStream.write(null);
    }

    @Override
    public int status() {
        return 0;
    }

}
