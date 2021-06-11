package com.example.mareu.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.repository.MeetingRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextInputEditText mMeetingSubject;
    private TextInputEditText mMeetingDate;
    private TextInputEditText mMeetingTime;
    private TextInputEditText mMeetingDuration;
    private TextInputEditText mMeetingRoom;
    private TextInputEditText mMeetingParticipants;
    private Button mCreate;
    private Spinner mDurationSpinner;
    private Spinner mRoomSpinner;

    private MeetingRepository mRepository;
    private Calendar mStartCalendar;
    private int mMeetingDurationMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mRepository = DI.getMeetingRepository();
        mStartCalendar = Calendar.getInstance();

        mMeetingDate = findViewById(R.id.meeting_date);
        mMeetingTime = findViewById(R.id.meeting_time);
        mMeetingDuration = findViewById(R.id.meeting_duration);
        mMeetingRoom = findViewById(R.id.meeting_room_name);
        mMeetingParticipants = findViewById(R.id.meeting_participants);
        mCreate = findViewById(R.id.create);
        mDurationSpinner = findViewById(R.id.meeting_duration_spinner);
        mRoomSpinner = findViewById(R.id.meeting_room_spinner);

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.meeting_durations_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mDurationSpinner.setAdapter(adapter);
        mDurationSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (this, R.array.meeting_room_array, R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(adapter1);
        mRoomSpinner.setOnItemSelectedListener(this);
    }

    private void showDateDialog(EditText date) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mStartCalendar.set(Calendar.YEAR, year);
                mStartCalendar.set(Calendar.MONTH, month);
                mStartCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                date.setText(simpleDateFormat.format(mStartCalendar.getTime()));
                mMeetingRoom.setText("");
            }
        };

        new DatePickerDialog
                (AddMeetingActivity.this, dateSetListener, mStartCalendar.get(Calendar.YEAR), mStartCalendar.get(Calendar.MONTH), mStartCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimeDialog(EditText time) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mStartCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mStartCalendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                time.setText(simpleDateFormat.format(mStartCalendar.getTime()));
                mMeetingRoom.setText("");
            }
        };

        new TimePickerDialog(AddMeetingActivity.this, timeSetListener, mStartCalendar.get(Calendar.HOUR_OF_DAY), mStartCalendar.get(Calendar.MINUTE), true).show();

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMeetingDurationMillis = (position + 1) * 15 * 60000;
        Calendar mEndCalendar = Calendar.getInstance();
        long meetingEndTimeMillis = mStartCalendar.getTimeInMillis() + mMeetingDurationMillis;
        mEndCalendar.setTimeInMillis(meetingEndTimeMillis);
        int booleanCompar = mStartCalendar.compareTo(mEndCalendar);
        mMeetingRoom.setText("");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}