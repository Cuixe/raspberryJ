package org.raspberryJ.server.connection;

import org.raspberryJ.api.Connection;
import org.raspberryJ.api.SocketConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketServerConnection implements ServerConnection {

    private ServerSocket serverSocket;
    private Thread runner;
    private Map<Long, Connection> connections = new HashMap<>();

    public SocketServerConnection(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void startServer() {
        runner = new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    Connection connection = new SocketConnection(socket, new ClientController());
                    connection.startConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        runner.start();
    }

    public void stopServer() {
        connections.forEach((key,connection) -> {
            try {
                connection.stopConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
