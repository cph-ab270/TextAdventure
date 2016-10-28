package m1.map;

import libs.FileIO;
import m1.Room;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ejdems on 10/10/2016.
 */
public class DungeonMap {

    private Room[] rooms;

    public Room[] createDungeonMap() {
        // TODO: implement reading from a file
        ArrayList<String> mapData = getMapResource();
        createRooms(mapData);
        for (int i = 0; i < mapData.size(); i++) {
            String[] roomData = mapData.get(i).split(",");
            setRoom(roomData, rooms[i]);
        }
        return rooms;
    }

    private ArrayList<String> getMapResource() {
        FileIO fileIO = new FileIO();
        ArrayList<String> map = null;
        try {
            map = fileIO.readFile("resources/map.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private void createRooms(ArrayList<String> mapData) {
        rooms = new Room[mapData.size()];
        for (int i = 0; i < mapData.size(); i++) {
            rooms[i] = new Room();
        }
    }

    private void setRoom(String[] roomData, Room room) {
        setResources(roomData[0], room);
        for (int ii = 1; ii < roomData.length; ii++) {
            int connection = Integer.parseInt(roomData[ii].substring(1));
            switch (roomData[ii].charAt(0)) {
                case 'e':
                    room.setEast(rooms[connection]);
                    break;
                case 'w':
                    room.setWest(rooms[connection]);
                    break;
                case 's':
                    room.setSouth(rooms[connection]);
                    break;
                case 'n':
                    room.setNorth(rooms[connection]);
                    break;
            }
        }
    }

    private void setResources(String roomResources, Room room) {
        String[] resources = roomResources.split("-");
        if(resources.length > 1 && !resources[1].isEmpty()) {
            int gold = Integer.parseInt(resources[1]);
            room.setGold(gold);
        }
        if(resources.length > 2 && !resources[2].isEmpty()) {
            String description = resources[2];
            room.setDescription(description);
        }
    }
}