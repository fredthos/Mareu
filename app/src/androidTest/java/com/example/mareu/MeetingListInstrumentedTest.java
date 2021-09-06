package com.example.mareu;

import android.support.v4.media.session.PlaybackStateCompat;

import androidx.annotation.ContentView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.mareu.di.DI;
import com.example.mareu.repository.MeetingRepository;
import com.example.mareu.ui.MeetingListActivity;
import com.example.mareu.utils.DeleteViewAction;
import com.example.mareu.utils.RecyclerViewMeetingCountAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;
import java.util.concurrent.RecursiveAction;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static com.example.mareu.utils.RecyclerViewMeetingCountAssertion.withMeetingCount;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListInstrumentedTest {

    private static int ITEMS_COUNT = 5;

    private ActivityScenario<MeetingListActivity> mActivity;
    private MeetingRepository mMeetingRepository;

    @Rule
    public ActivityScenarioRule<MeetingListActivity> mActivityScenarioRule =
            new ActivityScenarioRule(MeetingListActivity.class);

    @Before
    public void setup() {
        mActivity = mActivityScenarioRule.getScenario();
        assertThat(mActivity, notnullValue());
        mMeetingRepository = DI.getNewInstanceMeetingRepository();

    }

    private void assertThat(ActivityScenario<MeetingListActivity> activity, Object notnullValue) {
    }

    private Object nullValue() {
        return new IsNull();
    }
    
    private Object notnullValue() {
        return IsNot.not(nullValue());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(1)));

    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withMeetingCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 4
        onView(ViewMatchers.withId(R.id.list_meetings)).check(withMeetingCount(ITEMS_COUNT - 1));
    }

    //@Test
    // Todo ajout reunion

    //@Test
    // Todo test par filtre


}