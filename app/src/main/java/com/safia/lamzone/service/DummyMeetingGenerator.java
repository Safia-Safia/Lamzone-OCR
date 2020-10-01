package com.safia.lamzone.service;

import android.graphics.Color;

import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Salle 1", 0x36000000 + Color.CYAN),
            new Room("Salle 2", 0x36000000 + Color.BLUE),
            new Room("Salle 3", 0x36000000 + Color.RED),
            new Room("Salle 4", 0x36000000 + Color.GREEN),
            new Room("Salle 5", 0x36000000 + Color.BLACK));


    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    private static ArrayList <String> emails = new ArrayList<>(Arrays.asList
            ("Exemple@yahoo.fr","Exemple@outlook.fr","Exemple@gmail.com","Exemple@hotmail.com"));


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            //new Meeting("Mario", emails, generateRooms().get(0), "26/3", , "14h40")
         //   , new Meeting("Example", emails, generateRooms().get(2), "26/3", "13h30", "14h40")
    );

}


