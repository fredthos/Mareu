package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.DummyMeetingGenerator;
import com.example.mareu.repository.MeetingRepository;
import com.example.mareu.ui.MeetingListActivity;

import junit.extensions.ActiveTestSuite;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingListUnitTest {

    private MeetingRepository mRepository;


    @Before
    public void setup() {
        mRepository = DI.getMeetingRepository();
        mRepository.getMeetings().clear();

    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = mRepository.getMeetings();
        List<Meeting> exeptedMeetings = DummyMeetingGenerator.DUMMY_FAKE_MEETING;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(exeptedMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = mRepository.getMeetings().get(0);
        mRepository.deleteMeeting(meetingToDelete);
        assertFalse(mRepository.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void addMeetingWithSuccess() {
        int numberMeeting = mRepository.getMeetings().size();
        Meeting meetingToAdd = mRepository.getMeetings().get(0);
        mRepository.createMeeting(meetingToAdd);
        assertEquals(mRepository.getMeetings().size(),numberMeeting + 1);
    }

    @Test
    public void filterMeetingByDateWithSuccess() {
        List<Meeting> meetings = new ArrayList<>();
        String filterDate = "03/05/2021";
        meetings.addAll(mRepository.filterByDate(filterDate));
        for (Meeting m : meetings){
            assertTrue(m.getMeetingDate(), equals(filterDate));
        }

    }

    @Test
    public void filterMeetingByRoomWithSuccess() {

    }
}