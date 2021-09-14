package com.example.mareu.repository;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Delete Meeting
     *
     * @param meeting delete meeting
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    /**
     * Create Meeting
     *
     * @param meeting create meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    /**
     * @param date
     * @return meeting list filter by date
     */
    @Override
    public List<Meeting> filterByDate(String date) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting m = mMeetings.get(i);
            if (m.getMeetingDate().contentEquals(date)) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }

    /**
     * @param roomId
     * @return meeting list filter by room
     */
    @Override
    public List<Meeting> filterByRoom(long roomId) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (int i = 0; i < mMeetings.size(); i++) {
            Meeting m = mMeetings.get(i);
           if (m.getMeetingRoomId() == roomId) {
               filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
   }
}
