package com.example.mareu.events;

import com.example.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting mMeeting;

    public DeleteMeetingEvent(Meeting meeting) {
        this.mMeeting = meeting;
    }
}
