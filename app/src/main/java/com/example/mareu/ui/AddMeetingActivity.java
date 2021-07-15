package com.example.mareu.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.meeting_subject)
    TextInputEditText mMeetingSubject;
    @BindView(R.id.meeting_date)
    TextInputEditText mMeetingDate;
    @BindView(R.id.meeting_time)
    TextInputEditText mMeetingTime;
    @BindView(R.id.meeting_duration)
    TextInputEditText mMeetingDuration;
    @BindView(R.id.meeting_room_name)
    TextInputEditText mMeetingRoom;
    @BindView(R.id.meeting_participants)
    TextInputEditText mMeetingParticipants;
    @BindView(R.id.meeting_duration_spinner)
    Spinner mDurationSpinner;
    @BindView(R.id.meeting_room_spinner)
    Spinner mRoomSpinner;
    @BindView(R.id.chip_group)
    ChipGroup mChipGroup;
    @BindView(R.id.create)
    Button mCreate;

    private MeetingRepository mRepository;
    private Calendar mStartCalendar;
    private List<String> mMeetingParticipantsList;
    private int mMeetingDurationMillis;
    private int chipId;


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
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mRepository = DI.getMeetingRepository();
        mStartCalendar = Calendar.getInstance();
        mMeetingParticipantsList = new ArrayList<>();

        // Subject
        mMeetingSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                meetingSubject = s.toString();
                checkIfValidateAndCreate();

            }
        });

        // Date
        mMeetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(mMeetingDate);
            }
        });
        mMeetingDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                meetingDate = s.toString();

            }
        });

        // Time
        mMeetingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(mMeetingTime);
            }
        });
        mMeetingTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                meetingTime = s.toString();
            }
        });

        // Spinner Duration
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.meeting_durations_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mDurationSpinner.setAdapter(adapter);
        mDurationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMeetingDurationMillis = (position + 1) * 15;
                Calendar mEndCalendar = Calendar.getInstance();
                long meetingEndTimeMillis = mStartCalendar.getTimeInMillis() + mMeetingDurationMillis;
                mEndCalendar.setTimeInMillis(meetingEndTimeMillis);
                int booleanCompar = mStartCalendar.compareTo(mEndCalendar);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner Room
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource
                (this, R.array.meeting_room_array, R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mRoomSpinner.setAdapter(adapter1);
        mRoomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Participants
        mMeetingParticipants.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ") || s.toString().contains(";")) {
                    mMeetingParticipants.onEditorAction(EditorInfo.IME_ACTION_DONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                meetingParticipants = s.toString();

            }
        });

        mMeetingParticipants.setOnEditorActionListener((v, actionId, event) -> {
            String insertEmailText = v.getText().toString();
            insertEmailText = insertEmailText.replace(" ", "").replace(";", "");
            if (insertEmailText.contains("@") && !insertEmailText.endsWith("@")
                    && insertEmailText.contains(".") && !insertEmailText.endsWith(".")) {
                mMeetingParticipantsList.add(insertEmailText);
                Chip chip = new Chip(AddMeetingActivity.this);
                chip.setText(insertEmailText);
                chip.setCloseIconVisible(true);
                chip.setBackgroundResource(R.color.teal_700);
                chip.setCloseIconTintResource(R.color.purple_500);
                chip.setTextColor(getResources().getColor(R.color.design_default_color_primary_dark));
                chip.setId(chipId++);
                chip.setOnCloseIconClickListener(v1 -> {
                    mChipGroup.removeView(v1);
                    mMeetingParticipantsList.remove(chip.getText().toString());
                    checkIfValidateAndCreate();
                });
                mChipGroup.addView(chip);
            } else {
                Snackbar.make(v, "Email incorrect, Participant non ajouté", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.purple_200))
                        .setTextColor(getResources().getColor(R.color.design_default_color_primary_dark)).show();
            }
            checkIfValidateAndCreate();
            mMeetingParticipants.setText("");
            return false;
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void checkIfValidateAndCreate() {
        mCreate.setEnabled(meetingSubject != null
                && meetingDate != null
                && meetingTime != null
                && meetingParticipants != null);
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
                (AddMeetingActivity.this, dateSetListener, mStartCalendar.get(Calendar.YEAR),
                        mStartCalendar.get(Calendar.MONTH), mStartCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

        new TimePickerDialog(AddMeetingActivity.this, timeSetListener,
                mStartCalendar.get(Calendar.HOUR_OF_DAY), mStartCalendar.get(Calendar.MINUTE), true).show();

    }

}