package com.safia.lamzone.model;

public class Room {

    private String roomName;
    private int roomColor;


    public Room(String roomName, int roomColor) {
        this.roomColor = roomColor;
        this.roomName = roomName;
    }

    public int getRoomColor() {
        return roomColor;
    }

    public void setRoomColor(int roomColor) {
        this.roomColor = roomColor;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


}
