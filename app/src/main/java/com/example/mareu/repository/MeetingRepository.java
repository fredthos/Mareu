package com.example.mareu.repository;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.List;

public interface MeetingRepository {

    /**
     * Get Rooms
     * @return the list of Rooms
     */
    List<Room> getRooms();

    Room getRoomById(long roomId);

    /**
     * Get Meeting
     * @return the list of meetings
     */
    List<Meeting> getMeetings();


}
