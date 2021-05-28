package com.example.mareu.model;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private long RoomId;
    private String mMeetingDate;
    private long mMeetingStart;
    private long mMeetingEnd;
    private List<String> mMeetingParticipants;

    public Meeting(long RoomId, String meetingObject, String meetingDate, long meetingStart,
                   long meetingEnd, List<String> MeetingParticipantsString) {
        this.RoomId = RoomId;
        this.mMeetingDate = meetingDate;
        this.mMeetingStart = meetingStart;
        this.mMeetingEnd = meetingEnd;
        this.mMeetingParticipants = MeetingParticipantsString;
    }

    public long getRoomId() {
        return this.RoomId;
    }

    public String getMeetingDate() {
        return mMeetingDate;
    }

    public long getMeetingStart() {
        return mMeetingStart;
    }

    public long getMeetingEnd() {
        return mMeetingEnd;
    }

    public List<String> getMeetingParticipants() {
        return mMeetingParticipants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return RoomId == meeting.RoomId &&
                mMeetingStart == meeting.mMeetingStart &&
                mMeetingEnd == meeting.mMeetingEnd &&
                Objects.equals(mMeetingDate, meeting.mMeetingDate) &&
                Objects.equals(mMeetingParticipants, meeting.mMeetingParticipants);
    }


}
