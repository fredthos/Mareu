package com.example.mareu.model;

public class Room {

    private long id;
    private String mRoomName;
    private int mRoomDesign;

    public Room(long i, String RoomName, int RoomDesign) {
        this.id = id;
        this.mRoomName = RoomName;
        this.mRoomDesign = RoomDesign;
    }

    public long getId() {
        return id;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public int getRoomDesign() {
        return mRoomDesign;
    }

}
