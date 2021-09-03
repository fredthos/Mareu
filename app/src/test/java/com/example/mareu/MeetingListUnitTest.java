package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.DummyMeetingGenerator;
import com.example.mareu.repository.MeetingRepository;
import com.example.mareu.ui.MeetingListActivity;

import junit.extensions.ActiveTestSuite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}