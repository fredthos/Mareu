package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mareu.R;
import com.example.mareu.repository.MeetingRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddMeetingActivity extends AppCompatActivity {

    private TextInputEditText mMeetingDate;
    private TextInputEditText mMeetingTime;
    private TextInputEditText mMeetingDuree;
    private TextInputEditText mMeeetingRoom;
    private TextInputEditText mMeetingParticipants;

    private MeetingRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
    }
}