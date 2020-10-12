package com.safia.lamzone.service;

import android.graphics.Color;

import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyMeetingGenerator {

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Salle cyan",  Color.CYAN),
            new Room("Salle magenta", Color.MAGENTA),
            new Room("Salle rouge", Color.RED),
            new Room("Salle verte",  Color.GREEN),
            new Room("Salle grise", Color.BLACK));


    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    private static ArrayList<String> emails = new ArrayList<>(Arrays.asList
            ("Exemple@yahoo.fr", "Exemple@outlook.fr", "Exemple@gmail.com", "Exemple@hotmail.com"));


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
              new Meeting("Reunion 1", emails, generateRooms().get(0), new Date(200820), new Date(1430), new Date(1530))
            , new Meeting("Reunion 2", emails, generateRooms().get(2), new Date(100920), new Date(1730), new Date(1630))
            , new Meeting("Reunion 3", emails, generateRooms().get(1), new Date(121220), new Date(1730), new Date(1630))
            , new Meeting("Reunion 1", emails, generateRooms().get(0), new Date(200820), new Date(1430), new Date(1530))
            );

}


