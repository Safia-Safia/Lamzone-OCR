package com.safia.lamzone.service;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.safia.lamzone.model.*;

public class DummyRoomGenerator {

    public static List<Room> LIST_ROOM = Arrays.asList(

            new Room("Salle 1" ,0x36000000 + Color.YELLOW),
            new Room("Salle 2" ,0x36000000 + Color.GREEN),
            new Room("Salle 2" ,0x36000000 + Color.RED)
    );

    public static List<Room> generateRooms() {
        return new ArrayList<>(LIST_ROOM);
    }

}
