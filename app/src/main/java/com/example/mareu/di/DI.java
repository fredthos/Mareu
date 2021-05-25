package com.example.mareu.di;

import com.example.mareu.repository.DummyMeetingRepository;
import com.example.mareu.repository.MeetingRepository;

/**
 * Dependency injector to get instance of repository
 */
public class DI {
    private static MeetingRepository repository = new DummyMeetingRepository();

    /**
     * Get an instance on @{@link MeetingRepository}
     * @return
     */

    public static MeetingRepository getMeetingRepository() {
        return repository;
    }

}
