package com.safia.lamzone.service;

import android.graphics.Color;

import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Salle rouge", Color.parseColor("#6D071A")),
            new Room("Salle violette", Color.parseColor("#997A8D")),
            new Room("Salle verte", Color.parseColor("#A5D152")),
            new Room("Salle grise", Color.parseColor("#BBD2E1")),
            new Room("Salle bleue", Color.parseColor("#74D0F1")));


    static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    public static ArrayList<String> emails = new ArrayList<>(Arrays.asList
            ("Exemple@yahoo.fr", "Luigi@outlook.fr", "Waluigi@gmail.com", "Exemple@hotmail.com"));


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion 1", emails, generateRooms().get(0), new Date(202062900), new Date(), new Date())
            , new Meeting("Reunion 2", emails, generateRooms().get(2), new Date(), new Date(1430), new Date(1530))
            , new Meeting("Reunion 3", emails, generateRooms().get(1), new Date(541651), new Date(), new Date())
            , new Meeting("Reunion 4", emails, generateRooms().get(4), new Date(541651), new Date(1730), new Date())
            , new Meeting("Reunion 5", emails, generateRooms().get(0), new Date(), new Date(), new Date())
    );

}


