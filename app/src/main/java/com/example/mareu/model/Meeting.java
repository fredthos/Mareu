package com.example.mareu.model;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private long RoomId;
    private String mMeetingSubject;
    private String mMeetingDate;
    private String mMeetingStartTime;
    private String mMeetingDuration;
    private List<String> mMeetingParticipants;

    public Meeting(int RoomId, String meetingSubject, String meetingDate, String meetingStartTime,
                   String meetingDuration, List<String> MeetingParticipants) {
        this.RoomId = RoomId;
        this.mMeetingSubject = meetingSubject;
        this.mMeetingDate = meetingDate;
        this.mMeetingStartTime = meetingStartTime;
        this.mMeetingDuration = meetingDuration;
        this.mMeetingParticipants = MeetingParticipants;
    }

    public long getRoomId() {
        return RoomId;
    }

    public void setRoomId(long roomId) {
        this.RoomId = roomId;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public void setMeetingSubject(String meetingTopic) {
        this.mMeetingSubject = mMeetingSubject;
    }

    public String getMeetingDate() {
        return mMeetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.mMeetingDate = meetingDate;
    }

    public String getMeetingStartTime() {
        return mMeetingStartTime;
    }

    public void setMeetingStartTime(String meetingStartTime) {
        this.mMeetingStartTime = meetingStartTime;
    }

    public String getMeetingDuration() {
        return mMeetingDuration;
    }

    public void setMeetingDuration(String  meetingDuration) {
        this.mMeetingDuration = meetingDuration;
    }

    public List<String> getMeetingParticipants() {
        return mMeetingParticipants;
    }

    public void setMeetingParticipants(List<String> meetingParticipants) {
        this.mMeetingParticipants = meetingParticipants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(RoomId, meeting.RoomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RoomId);
    }
}
