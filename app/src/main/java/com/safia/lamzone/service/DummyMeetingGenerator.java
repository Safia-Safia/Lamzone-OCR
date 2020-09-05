package com.safia.lamzone.service;

import android.graphics.Color;

import com.safia.lamzone.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
           // new Meeting( "Example", "yaourt@gmail.com",  0x36000000 +Color.YELLOW),
            //new Meeting( "Exemple", "jus@gmail.com", 0x36000000 +   Color.GREEN)
            );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}


