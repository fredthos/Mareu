package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.repository.MeetingRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddMeetingActivity extends AppCompatActivity {

    private TextInputEditText mMeetingSubject;
    private TextInputEditText mMeetingDate;
    private TextInputEditText mMeetingTime;
    private TextInputEditText mMeetingDuration;
    private TextInputEditText mMeetingRoom;
    private TextInputEditText mMeetingParticipants;

    private MeetingRepository mRepository;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mRepository = DI.getMeetingRepository();
        mCalendar = Calendar.getInstance();

        mMeetingDate = findViewById(R.id.meeting_date);
        mMeetingTime = findViewById(R.id.meeting_time);
        mMeetingDuration = findViewById(R.id.meeting_duration);
        mMeetingRoom = findViewById(R.id.meeting_room_name);
        mMeetingParticipants =findViewById(R.id.meeting_participants);

        mMeetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(mMeetingDate);
            }
        });

        mMeetingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(mMeetingTime);
            }
        });

        //duration

        mMeetingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void showDateDialog(EditText date) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                date.setText(simpleDateFormat.format(mCalendar.getTime()));
            }
        };

        new DatePickerDialog
                (AddMeetingActivity.this, dateSetListener, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimeDialog(EditText time) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time.setText(simpleDateFormat.format(mCalendar.getTime()));
            }
        };

        new TimePickerDialog(AddMeetingActivity.this, timeSetListener, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true).show();

    }

    //Duration



}