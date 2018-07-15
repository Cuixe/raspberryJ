package org.raspberryJ.connection;

import org.junit.*;
import org.raspberryJ.client.connection.ClientConnection;
import org.raspberryJ.client.connection.SocketClientConnection;
import org.raspberryJ.server.connection.ClientController;
import org.raspberryJ.server.connection.ServerConnection;
import org.raspberryJ.server.connection.SocketServerConnection;
import org.raspberryJ.server.data.ControllerRepository;

import java.io.IOException;

public class ConnectionTest {

    static ServerConnection socketServer;

    @BeforeClass
    public static void setup() throws IOException {
        socketServer = new SocketServerConnection(11500);
        socketServer.startServer();
    }

    @AfterClass
    public static void after() {
        socketServer.stopServer();
    }

    @Test
    public void validDevice() throws IOException {
        String ID = "RASP1";
        ControllerRepository.addKnownId(ID);
        ClientConnection clientConnection = new SocketClientConnection("localhost", 11500);
        clientConnection.connect(ID);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ClientController controller = ControllerRepository.getController(ID);
        controller.operatePin(10, true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(clientConnection.isRegistered());
        Assert.assertTrue(ControllerRepository.isRegistered(ID));
        Assert.assertEquals(1, clientConnection.status());
    }

    @Test
    public void severalDevices() throws IOException {
        String ID_1 = "RASP2";
        String ID_2 = "RASP3";
        ControllerRepository.addKnownId(ID_1);
        ControllerRepository.addKnownId(ID_2);
        ClientConnection clientConnection1 = new SocketClientConnection("localhost", 11500);
        clientConnection1.connect(ID_1);

        ClientConnection clientConnection2 = new SocketClientConnection("localhost", 11500);
        clientConnection2.connect(ID_2);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(ControllerRepository.isRegistered(ID_1));
        Assert.assertTrue(ControllerRepository.isRegistered(ID_2));
        Assert.assertEquals(1, clientConnection1.status());
        Assert.assertEquals(1, clientConnection2.status());


    }

    @Test
    public void invalidDevice() throws IOException {
        ClientConnection clientConnection = new SocketClientConnection("localhost", 11500);
        clientConnection.connect("UNKNOWN");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse(clientConnection.isRegistered());
        Assert.assertFalse(ControllerRepository.isRegistered("UNKNOWN"));
        Assert.assertEquals(2, clientConnection.status());
    }
}
