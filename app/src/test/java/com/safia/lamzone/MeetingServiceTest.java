package com.safia.lamzone;

import com.safia.lamzone.di.DI;
import com.safia.lamzone.model.Meeting;
import com.safia.lamzone.model.Room;
import com.safia.lamzone.service.DummyMeetingGenerator;
import com.safia.lamzone.service.MeetingApiService;


import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private MeetingApiService mApiService;

    @Before
    public void setUp() {
        mApiService = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingList() {
        List<Meeting> meeting = mApiService.getMeeting();
        List<Meeting> meetingListExpected = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meeting, is(equalTo(meetingListExpected)));
    }

    @Test
    public void getRoomList() {
        List<Room> roomList = mApiService.getMeetingRooms();
        List<Room> roomListExpected = DummyMeetingGenerator.DUMMY_ROOMS;
        assertThat(roomList, is(equalTo(roomListExpected)));
    }

    @Test
    public void deleteMeeting() {
        Meeting meetingToDelete = mApiService.getMeeting().get(0);
        mApiService.deleteMeeting(meetingToDelete);
        assertFalse(mApiService.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void addMeeting() {
        List<String> email = DummyMeetingGenerator.emails;
        List<Room> roomList = mApiService.getMeetingRooms();
        Meeting meetingCreated = new Meeting("Name", email, roomList.get(0),
                                 new Date(200820), new Date(1430), new Date(1530));
        mApiService.addMeeting(meetingCreated);
        assertTrue(mApiService.getMeeting().contains(meetingCreated));
    }

    @Test
    public void getMeetingByRoom() {
        List<Meeting> meeting = mApiService.getMeetingByRoom(mApiService.getMeetingRooms().get(0));
        assertEquals(2, meeting.size());
    }

    @Test
    public void getMeetingByDate() {
        Date date = new Date(541651);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        List<Meeting> filteredMeeting = mApiService.getMeetingByDate(day, month, year);
        assertEquals(2, filteredMeeting.size());
    }


   @Test
    public void isRoomAvailable() {
        List<String> email = DummyMeetingGenerator.emails;
        List<Room> roomList = mApiService.getMeetingRooms();
        Meeting meetingCreated = new Meeting(
                "Name", email, roomList.get(0), new Date(200820), new Date(1430), new Date(1530));
        assertTrue(mApiService.canMeetingBeCreated(meetingCreated));
    }

    @Test
    public void isDateAvailable() {
        List<String> email = DummyMeetingGenerator.emails;
        List<Room> roomList = mApiService.getMeetingRooms();
        Meeting meetingCreated1 = new Meeting(
                "Name", email, roomList.get(0), new Date(200820), new Date(1430), new Date(1530));
        assertTrue(mApiService.canMeetingBeCreated(meetingCreated1));
    }

    @Test
    public void isTimeAvailable() {
        List<String> email = DummyMeetingGenerator.emails;
        List<Room> roomList = mApiService.getMeetingRooms();
        Meeting meeting = new Meeting(
                "Name", email, roomList.get(0), new Date(), new Date(), new Date());
        assertFalse(mApiService.canMeetingBeCreated(meeting));
    }

}