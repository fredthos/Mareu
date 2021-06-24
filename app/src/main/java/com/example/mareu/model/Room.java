package com.example.mareu.model;

import java.util.Objects;

public class Room {

    private long id;
    private String mRoomName;
    private int mRoomDesign;

    public Room(long id, String roomName, int roomDesign) {
        this.id = id;
        this.mRoomName = roomName;
        this.mRoomDesign = roomDesign;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomName() {
        return mRoomName;
    }

    public void setRoomName(String roomName) {
        this.mRoomName = roomName;
    }

    public int getRoomDesign() {
        return mRoomDesign;
    }

    public void setRoomDesign(int roomDesign) {
        this.mRoomDesign = roomDesign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
