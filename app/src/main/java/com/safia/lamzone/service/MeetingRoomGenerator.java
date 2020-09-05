package com.safia.lamzone.service;

import android.graphics.Color;

import com.safia.lamzone.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomGenerator {
    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room( "Example",   0x36000000 + Color.GREEN),
            new Room( "Example",  0x36000000 +Color.BLUE),
            new Room( "Example", 0x36000000 +Color.RED),
            new Room( "Example",  0x36000000 +Color.YELLOW),
            new Room( "Example", 0x36000000 +Color.BLACK),
            new Room( "Example",  0x36000000 +Color.YELLOW),
            new Room( "Example",  0x36000000 +Color.YELLOW)    );

    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }
}
