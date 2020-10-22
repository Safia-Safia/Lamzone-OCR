package com.safia.lamzone;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static int ITEMS_COUNT =6;
    private MeetingListActivity mActivity;

    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_meeting))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingList_shouldNotBeEmpty(){
        onView(ViewMatchers.withId(R.id.list_meeting)).check(withItemCount(ITEMS_COUNT));

    }
}
