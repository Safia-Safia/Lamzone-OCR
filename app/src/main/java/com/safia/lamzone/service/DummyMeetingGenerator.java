package com.safia.lamzone.service;

import com.safia.lamzone.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_NEIGHBOURS = Arrays.asList(
            new Meeting( " Arbre ", "yaourt@gmail.com","https://i.pravatar.cc/150?u="),
            new Meeting( "Exemple", "jus@gmail.com","https://i.pravatar.cc/150?u="));

    static List<Meeting> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}


