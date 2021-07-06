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

import butterknife.BindView;

public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.meeting_subject)
    TextInputEditText mMeetingSubject;
    @BindView(R.id.filter_date)
    TextInputEditText mMeetingDate;
    @BindView(R.id.meeting_time)
    TextInputEditText mMeetingTime;
    @BindView(R.id.meeting_duration)
    TextInputEditText mMeetingDuration;
    @BindView(R.id.meeting_room_name)
    TextInputEditText mMeetingRoom;
    @BindView(R.id.meeting_participants)
    TextInputEditText mMeetingParticipants;
    @BindView(R.id.create_meeting)
    Button mCreateMeeting;
    @BindView(R.id.meeting_duration_spinner)
    Spinner mDurationSpinner;
    @BindView(R.id.meeting_room_spinner)
    Spinner mRoomSpinner;
    @BindView(R.id.create)
    Button mCreate;

    private MeetingRepository mRepository;
    private Calendar mStartCalendar;
    private int mMeetingDurationMillis;

    //todo initialisation creation meeting (1)
    private String meetingSubject;
    private String meetingDate;
    private String meetingTime;
    private String meetingDuration = "15min";
    private String meetingRoom = "cactus";
    private String meetingParticipants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        modifVars();

        mRepository = DI.getMeetingRepository();
        mStartCalendar = Calendar.getInstance();

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

        //todo a modifier avec les differents champs OK pour create (3)

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMeetingSubject.getText().toString();
                if (mMeetingSubject.getText().toString() != null) ;
            }
        });
    }

    // DatePicker
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

    // TimePicker
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

    //todo modification des variables (2)
    private void modifVars() {


    }


}