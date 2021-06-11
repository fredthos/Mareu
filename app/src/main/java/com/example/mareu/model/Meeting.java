package com.example.mareu.model;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private long RoomId;
    private final String mMeetingSubject;
    private double mMeetingDate;
    private long mMeetingStartTime;
    private long mMeetingEndTime;
    private String mMeetingParticipants;

    public Meeting(long RoomId, String meetingSubject, double meetingDate, long meetingStartTime,
                   long meetingEndTime, String MeetingParticipants) {
        this.RoomId = RoomId;
        this.mMeetingSubject = meetingSubject;
        this.mMeetingDate = meetingDate;
        this.mMeetingStartTime = meetingStartTime;
        this.mMeetingEndTime = meetingEndTime;
        this.mMeetingParticipants = MeetingParticipants;
    }

    public long getRoomId() {
        return this.RoomId;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public double getMeetingDate() {
        return mMeetingDate;
    }

    public long getMeetingStartTime() {
        return mMeetingStartTime;
    }

    public long getMeetingEndTime() {
        return mMeetingEndTime;
    }

    public String getMeetingParticipants() {
        return mMeetingParticipants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return RoomId == meeting.RoomId &&
                mMeetingStartTime == meeting.mMeetingStartTime &&
                mMeetingEndTime == meeting.mMeetingEndTime &&
                Objects.equals(mMeetingDate, meeting.mMeetingDate) &&
                Objects.equals(mMeetingParticipants, meeting.mMeetingParticipants);
    }


}
