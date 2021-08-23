package com.example.mareu.model;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private long meetingRoomId;
    private String mMeetingSubject;
    private String mMeetingDate;
    private String mMeetingStartTime;
    private String mMeetingDuration;
    private List<String> mMeetingParticipants;

    public Meeting(
            long meetingRoomId,
            String meetingSubject,
            String meetingDate,
            String meetingStartTime,
            String meetingDuration,
            List<String> meetingParticipants
    ) {
        this.meetingRoomId = meetingRoomId;
        mMeetingSubject = meetingSubject;
        mMeetingDate = meetingDate;
        mMeetingStartTime = meetingStartTime;
        mMeetingDuration = meetingDuration;
        mMeetingParticipants = meetingParticipants;
    }

    public long getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(long meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public String getMeetingSubject() {
        return mMeetingSubject;
    }

    public void setMeetingSubject(String meetingSubject) {
        this.mMeetingSubject = meetingSubject;
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
        return Objects.equals(meetingRoomId, meeting.meetingRoomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingRoomId);
    }
}
