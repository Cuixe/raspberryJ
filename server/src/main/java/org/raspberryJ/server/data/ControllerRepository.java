package org.raspberryJ.server.data;

import org.raspberryJ.server.connection.ClientController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerRepository {

    private static Map<String, ClientController> connections = new HashMap<>();
    private static List<String> knownIds = new ArrayList<>();

    public static synchronized boolean isKnown(String raspberryId) {
        return knownIds.contains(raspberryId);
    }

    public static synchronized boolean isRegistered(String raspberryId){
        return connections.containsKey(raspberryId);
    }

    public static synchronized ClientController getController(String raspberryId){
        return connections.get(raspberryId);
    }

    public static synchronized void register(String raspberryId, ClientController connection) {
        if(!isRegistered(raspberryId)) {
            connections.put(raspberryId, connection);
        }
    }

    public static synchronized void unRegister(String raspberryId) {
        try {
            ClientController controller = connections.get(raspberryId);
            controller.getConnection().stopConnection();
            connections.remove(raspberryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void addKnownId(String id) {
        knownIds.add(id);
    }

}
