package org.raspberryJ.server.connection;

import org.raspberryJ.api.Connection;
import org.raspberryJ.api.Message;
import org.raspberryJ.api.MessageCallback;
import org.raspberryJ.server.data.ControllerRepository;

public class ClientController implements MessageCallback, GpioController {

    private Connection connection;
    private long id;
    private Message.Transport.Builder transportBuilder = Message.Transport.newBuilder();

    public void operatePin(int pin, boolean activate) {
        Message.PinRequest.Builder request = Message.PinRequest.newBuilder();
        request.setClientId(this.id);
        request.setPindNumber(pin);
        request.setTurnOn(activate);
        transportBuilder.clear();
        transportBuilder.setPinRequest(request);
        sendResponse();
    }

    @Override
    public void process(Message.Transport transport) {
        switch (transport.getMessageCase()) {
            case REGISTERREQUEST:
                processRegister(transport.getRegisterRequest());
                break;
            case PINGREQUEST:
                processPingRequest(transport.getPingRequest());
                break;
        }
    }

    private void processPingRequest(Message.PingRequest ping) {
        Message.PingResponse.Builder builder = Message.PingResponse.newBuilder();
        builder.setClientId(this.id);
        builder.setResponseTime(System.currentTimeMillis());
        builder.setPingRequest(ping);
        transportBuilder.clear();
        transportBuilder.setPingResponse(builder);
        sendResponse();
    }

    private void processRegister(Message.RegisterRequest registerRequest) {
        Message.RegisterResponse.Builder builder = Message.RegisterResponse.newBuilder();
        builder.setRegisterRequest(registerRequest);
        String raspberryId = registerRequest.getRaspberryId();
        boolean valid = false;
        if (!ControllerRepository.isKnown(raspberryId)) {
            builder.setStatus(Message.RegisterResponse.Status.UN_KNOWN_DEVICE);
        } else if(ControllerRepository.isRegistered(raspberryId)){
            builder.setStatus(Message.RegisterResponse.Status.ALREADY_REGISTERED);
        } else {
            builder.setStatus(Message.RegisterResponse.Status.ACCEPTED);
            builder.setClientId(this.id);
            ControllerRepository.register(raspberryId, this);
            valid = true;
        }
        transportBuilder.clear();
        transportBuilder.setRegisterResponse(builder);
        sendResponse();
        if (!valid) {
            try {
                connection.stopConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResponse() {
        try {
            connection.sendMessage(transportBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
        this.id = connection.getConnectionId();
    }

    public Connection getConnection() {
        return connection;
    }
}
