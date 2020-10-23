package com.safia.lamzone;


import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.safia.lamzone.di.DI;
import com.safia.lamzone.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.safia.lamzone.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {

    private static int ITEMS_COUNT = 5;
    private MeetingListActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @After
    public void cleanUp() {
        DI.getMeetingApiService().getMeeting().clear();
    }

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityRule =
            new ActivityTestRule(MeetingListActivity.class);

    @Test
    public void NeighboursList_shouldNotBeEmpty() {
        onView(withId(R.id.meeting_list))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void meetingList_DeleteAction() {
        onView(withId(R.id.meeting_list)).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.meeting_list)).perform(actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.meeting_list)).check(withItemCount(ITEMS_COUNT - 1));

    }

    @Test
    public void showMeetingDetail() {
        onView(withId(R.id.meeting_list)).perform(click());
        onView(withId(R.id.btnClose));
    }

    @Test
    public void createMeeting() {
        onView(withId(R.id.addReunion)).perform(click());
        onView(withId(R.id.edit_meeting_name)).perform(typeText("New Reunion Test"));
        onView(withId(R.id.btn_datePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 8, 25));
        onView(withText("OK")).perform(click());
        setTime();
        addEmail();
        selectRoom();
        onView(ViewMatchers.withId(R.id.btn_create)).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(ITEMS_COUNT + 1));
    }


    public void setTime() {
        onView(withId(R.id.btn_start_timePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(17, 10));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.btn_end_timePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(17, 20));
        onView(withText("OK")).perform(click());
    }

    public void selectRoom() {
        onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        onView(withText("Salle violette")).perform(click());
    }

    public void addEmail() {
        onView(withId(R.id.btn_add_email)).perform(click());
        onView(withId(R.id.edit_text_user_email)).perform(typeText("Mail@gmail.com"));
        onView(withId(R.id.btn_new_user)).perform(click());
        onView(withId(R.id.btn_save_user)).perform(click());

    }

    @Test
    public void filterByRoom() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Trier")).perform(click());
        onView(withText("Par salle")).perform(click());
        onView(withText("salle rouge")).perform(click());
        onView(withId(R.id.meeting_list)).check(withItemCount(2));
    }

    @Test
    public void filterByDate() {
        createMeeting();
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Trier")).perform(click());
        onView(withText("Par Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 8, 25));
    }

    @Test
    public void reinitialize() {
        filterByRoom();
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Trier")).perform(click());
        onView(withText("Réinitialiser")).perform(click());
    }

}
