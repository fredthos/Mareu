package com.example.mareu;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.di.DI;
import com.example.mareu.repository.MeetingRepository;
import com.example.mareu.ui.MeetingListActivity;
import com.example.mareu.utils.DeleteViewAction;

import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListInstrumentedTest {

    private static int ITEMS_COUNT = 5;
    private static final String TEST_CREATE_MEETING = "Reunion test";
    private static final String TEST_DATE_MEETING = "17/09/2021";
    private static final String TEST_TIME_MEETING = "7:20";
    private static final String TEST_DURATION_MEETING = "1h";
    private static final String TEST_ROOM_MEETING = "Mojito";

    private MeetingListActivity mActivity;
    private MeetingRepository mMeetingRepository;
    private Calendar mCalendar;

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityTestRule =
            new ActivityTestRule(MeetingListActivity.class);


    @Before
    public void setup() {
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notnullValue());
        mMeetingRepository = DI.getNewInstanceMeetingRepository();
        mCalendar = Calendar.getInstance();

    }

    private void assertThat(MeetingListActivity activity, Object notnullValue) {
    }

    private Object nullValue() {
        return new IsNull();
    }

    private Object notnullValue() {
        return IsNot.not(nullValue());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView(withId(R.id.recycler_view_meeting))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.recycler_view_meeting))
                .perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 4
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    // Todo ajout reunion
    public void myMeetingList_ClickAndOpenActivityAddMeeting() {
        // Given : We are on meeting list activity
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT));
        // When : Click on createmeeting boutton
        onView(withId(R.id.create_meeting)).perform(click());
        // Then : Activity_add_meeting to be open
        onView(withId(R.id.activity_add_meeting)).check(matches(isDisplayed()));
    }

    @Test
    public void myActivityAddMeeting_createmeeting() {
        // Given : We are on activity add meeting
        onView(withId(R.id.create_meeting)).perform(click());
        // When : Complete Meeting subject
        onView(withId(R.id.meeting_subject)).perform(click());
        onView(withId(R.id.meeting_subject))
                .perform(typeTextIntoFocusedView("Reunion test")
                        , closeSoftKeyboard(), pressImeActionButton());
        onView(withId(R.id.meeting_subject)).check(matches(withText(TEST_CREATE_MEETING)));
        // When : Complete Meeting date
        onView(withId(R.id.meeting_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.meeting_date)).check(matches(withText(TEST_DATE_MEETING)));
        // When : Complete Meeting time
        onView(withId(R.id.meeting_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.meeting_time)).check(matches(isDisplayed()));
        // When : Complete Meeting duration
        onView(withId(R.id.meeting_duration_spinner)).perform(click());
        onView(withText(TEST_DURATION_MEETING)).check(matches(isDisplayed()));
        onView(withText(TEST_DURATION_MEETING)).perform(click());
        onView(withId(R.id.meeting_duration_spinner)).check(matches(withSpinnerText(TEST_DURATION_MEETING)));
        // When : Complete Meeting Room
        onView(withId(R.id.meeting_room_spinner)).perform(click());
        onView(withText(TEST_ROOM_MEETING)).check(matches(isDisplayed()));
        onView(withText(TEST_ROOM_MEETING)).perform(click());
        onView(withId(R.id.meeting_room_spinner)).check(matches(withSpinnerText(TEST_ROOM_MEETING)));
        // When : Complete Meeting Participants
        onView(withId(R.id.meeting_participants)).perform(click());
        onView(withId(R.id.meeting_participants))
                .perform(typeTextIntoFocusedView("fred@lamzone.com")
                        , closeSoftKeyboard(), pressImeActionButton());
        onView(withId(R.id.meeting_participants))
                .perform(typeTextIntoFocusedView("jean@lamzone.com")
                        , closeSoftKeyboard(), pressImeActionButton());
        onView(withId(R.id.meeting_participants)).check(matches(isDisplayed()));
        // When : Click Button for Create
        onView(withId(R.id.create)).perform(click());
        // Then : on list Meeting activity add one meeting
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void myActivityListMeeting_selectedFilterByDate_isCorrect() {
        // Given : We are on meeting list activity
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT));
        // When : Selected filter by date
        onView(withContentDescription(R.string.filtres)).perform(click());
        onView(withText(R.string.filter_by_date)).perform(click());
        // When : Selected Date
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recycler_view_meeting)).check(matches(isDisplayed()));
        // Then : Check mmeeting with date selected
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(1));
    }


    @Test
    public void myActivityListMeeting_selectedFilterByRoom_isCorrect() {
        // Given : We are on meeting list activity
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT));
        // When : Selected filter by room
        onView(withContentDescription(R.string.filtres)).perform(click());
        onView(withText(R.string.filter_by_meetingroom)).perform(click());
        // When : Selected Room
        onView(withText("Maitai")).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recycler_view_meeting)).check(matches(isDisplayed()));
        // Then : Check mmeeting with date selected
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(2));

    }

    @Test
    public void myActivityListMeeting_selectedResetFilter_isCorrect() {
        // Given : We are on meeting list activity
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT));
        // When : Selected filter by room
        onView(withContentDescription(R.string.filtres)).perform(click());
        onView(withText(R.string.filter_by_meetingroom)).perform(click());
        // When : Selected Room
        onView(withText("Maitai")).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.recycler_view_meeting)).check(matches(isDisplayed()));
        // When : Check mmeeting with date selected
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(2));
        // When : Selected reset filter
        onView(withContentDescription(R.string.filtres)).perform(click());
        onView(withText(R.string.reset_filter)).perform(click());
        // When : Check mmeeting with date selected
        onView(withId(R.id.recycler_view_meeting)).check(withItemCount(ITEMS_COUNT));
    }
}