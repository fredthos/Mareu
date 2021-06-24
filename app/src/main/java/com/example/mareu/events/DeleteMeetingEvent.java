package com.example.mareu.events;

import com.example.mareu.model.Meeting;

public class DeleteMeetingEvent {

    /**
     * The Meeting to delete
     */
    public Meeting mMeeting;

    /**
     * Instantiates a new Delete meeting event.
     *
     * @param meeting the meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}
