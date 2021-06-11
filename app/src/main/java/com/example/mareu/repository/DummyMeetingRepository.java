package com.example.mareu.repository;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingRepository implements MeetingRepository {

    private List<Room> mRooms = DummyMeetingGenerator.generateRooms();
    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeeting();


    @Override
    public List<Room> getRooms() {
        return mRooms;
    }

    @Override
    public Room getRoomById(long roomId) {
        for (int i = 0; i < mRooms.size(); i++) {
            if (roomId == mRooms.get(i).getId()) {
                return mRooms.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;

    }
}
