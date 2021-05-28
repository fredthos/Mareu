package com.example.mareu.repository;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingRepository implements MeetingRepository {

    private List<Room> Rooms = DummyMeetingGenerator.generateRooms();
    private List<Meeting> mMeetings = new ArrayList<>();


    @Override
    public List<Room> getRooms() {
        return Rooms;
    }

    @Override
    public Room getRoomsById(long roomId) {
        return null;
    }

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;

    }
}
