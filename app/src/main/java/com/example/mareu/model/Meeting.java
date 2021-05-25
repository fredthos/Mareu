package com.example.mareu.model;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private long RoomId;
    private String mMeetingSubject;
    private String mMeetingDay;
    private long mMeetingStart;
    private long mMeetingEnd;
    private List<String> mMeetingParticipants;

    public Meeting(long Roomid, String meetingSubject, String meetingDay, long meetingStart,
                   long meetingEnd, List<String> MeetingParticipantsString) {
        this.RoomId = Roomid;
        this.mMeetingSubject = meetingSubject;
        this.mMeetingDay = meetingDay;
        this.mMeetingStart = meetingStart;
        this.mMeetingEnd = meetingEnd;
        this.mMeetingParticipants = MeetingParticipantsString;
    }

    public long getRoomId() {
        return RoomId;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public String getMeetingDay() {
        return mMeetingDay;
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
                Objects.equals(mMeetingSubject, meeting.mMeetingSubject) &&
                Objects.equals(mMeetingDay, meeting.mMeetingDay) &&
                Objects.equals(mMeetingParticipants, meeting.mMeetingParticipants);
    }


}
