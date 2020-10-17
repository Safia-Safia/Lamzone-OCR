package com.safia.lamzone.di;

import com.safia.lamzone.service.DummyMeetingApiService;
import com.safia.lamzone.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        service = new DummyMeetingApiService();
        return service;
    }
}
