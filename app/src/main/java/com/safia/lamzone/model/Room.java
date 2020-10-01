package com.safia.lamzone.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {

    private String roomName;
    private int roomColor;


    public Room(String roomName, int roomColor) {
        this.roomColor = roomColor;
        this.roomName = roomName;
    }

    protected Room(Parcel in) {
        roomName = in.readString();
        roomColor = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(roomName);
        dest.writeInt(roomColor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

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
