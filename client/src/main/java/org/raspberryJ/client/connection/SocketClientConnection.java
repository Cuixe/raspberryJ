package org.raspberryJ.client.connection;

import org.raspberryJ.api.Connection;
import org.raspberryJ.api.Message;
import org.raspberryJ.api.MessageCallback;
import org.raspberryJ.api.SocketConnection;

import java.io.IOException;

public class SocketClientConnection implements MessageCallback, ClientConnection {

    private Message.Transport.Builder transport = Message.Transport.newBuilder();
    private Connection connection;
    private PingWorker pingWorker;
    private String host;
    private int port;
    private boolean registered = false;

    public SocketClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void process(Message.Transport transport) {
        switch (transport.getMessageCase()) {
            case REGISTERRESPONSE:
                processRegisterResponse(transport.getRegisterResponse());
                break;
            case PINRESPONSE:
                pingWorker.registerPingResponse(transport.getPingResponse());
                break;
            case PINREQUEST:
                processPinRequest(transport.getPinRequest());
                break;
        }
    }

    private void processRegisterResponse(Message.RegisterResponse registerResponse) {
        if (registerResponse.getStatus() == Message.RegisterResponse.Status.ACCEPTED) {
            pingWorker = new PingWorker(connection);
            pingWorker.start();
            registered = true;
        } else {
            try {
                connection.stopConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void connect(String raspberryId) throws IOException {
        connection = new SocketConnection(host, port, this);
        connection.startConnection();
        Message.RegisterRequest.Builder register = Message.RegisterRequest.newBuilder();
        register.setRaspberryId(raspberryId);
        transport.clear();
        transport.setRegisterRequest(register);
        sendMessage();
    }

    public void processPinRequest(Message.PinRequest request) {
        //TODO activate pin
        Message.PinResponse.Builder pinResponse = Message.PinResponse.newBuilder();
        pinResponse.setClientId(this.connection.getConnectionId());
        pinResponse.setPinRequest(request);
        pinResponse.setStatus(1);
        transport.clear();
        transport.setPinResponse(pinResponse);
        sendMessage();

    }

    private  void sendMessage() {
        try {
            this.connection.sendMessage(transport.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int status() {
        return connection.status();
    }

    public boolean isRegistered() {
        return registered;
    }

    @Override
    public void setConnection(Connection connection) {

    }
}
