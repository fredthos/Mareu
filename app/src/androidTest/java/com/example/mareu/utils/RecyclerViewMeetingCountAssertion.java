package com.example.mareu.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class RecyclerViewMeetingCountAssertion implements ViewAssertion {
    private final Matcher<Integer> matcher;

    public RecyclerViewMeetingCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    public static RecyclerViewMeetingCountAssertion withMeetingCount(int expectedCount) {
        return withMeetingCount(Matchers.is(expectedCount));
    }

    public static RecyclerViewMeetingCountAssertion withMeetingCount(Matcher<Integer> matcher) {
        return new RecyclerViewMeetingCountAssertion(matcher);
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Assert.assertThat(adapter.getItemCount(), matcher);
    }
}
