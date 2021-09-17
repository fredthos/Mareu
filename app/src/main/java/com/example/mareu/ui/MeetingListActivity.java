package com.example.mareu.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.repository.MeetingRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity {


    private FloatingActionButton mCreateMeetingBtn;

    private List<Meeting> mMeetings;
    private List<Room> mRooms;
    private MeetingRepository mMeetingRepository;
    private RecyclerView mRecyclerView;
    private MyMeetingRecyclerViewAdapter adapter;
    private Calendar mCalendar;
    private String selectedDate = null;
    private AlertDialog roomDialog = null;
    private Spinner mRoomSpinner = null;
    private SpinnerAdapter roomSpinnerAdapter;


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.Filter_date) {
            mCalendar = Calendar.getInstance();
            showDateDialog();
        } else if (itemId == R.id.Filter_room) {
            showRoomDialog();
        } else if (itemId == R.id.Reset_Filtre) {
            mMeetings = mMeetingRepository.getMeetings();
            adapter.setMeetings(mMeetings);
            Toast.makeText(this, R.string.reset_filter, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showRoomDialog() {
        final AlertDialog.Builder roomDialogBuilder =
                new AlertDialog.Builder(this, R.style.Dialog);
        roomDialogBuilder.setView(R.layout.dialogselectroom);
        roomDialogBuilder.setOnDismissListener(dialog -> {
            roomDialog = null;
            mRoomSpinner = null;
        });
        roomDialogBuilder.setPositiveButton("OK", (dialog, which) -> {
            Room selectedRoom = (Room) mRoomSpinner.getSelectedItem();
            long selectedRoomId = selectedRoom.getId();
            List<Meeting> filteredList = mMeetingRepository.filterByRoom(selectedRoomId);
            adapter.setMeetings(filteredList);
            Toast.makeText(getApplicationContext(), R.string.filter_by_meetingroom, Toast.LENGTH_SHORT).show();
            roomDialog = null;
            mRoomSpinner = null;
        });
        roomDialog = roomDialogBuilder.create();
        roomDialog.show();
        populateRoomSpinner();
    }

    private void populateRoomSpinner() {
        mRoomSpinner = roomDialog.findViewById(R.id.room_spinner);
        if (mRoomSpinner != null) mRoomSpinner.setAdapter(roomSpinnerAdapter);
        assert mRoomSpinner != null;
        mRoomSpinner.performClick();
    }

    private void showDateDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                selectedDate = simpleDateFormat.format(mCalendar.getTime());
                List<Meeting> filteredList = mMeetingRepository.filterByDate(selectedDate);
                adapter.setMeetings(filteredList);
                Toast.makeText(getApplicationContext(), R.string.filter_by_date, Toast.LENGTH_SHORT).show();
            }
        };

        new DatePickerDialog
                (MeetingListActivity.this, dateSetListener, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        mMeetingRepository = DI.getMeetingRepository();
        mRecyclerView = findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();

        mCreateMeetingBtn = findViewById(R.id.create_meeting);
        mCreateMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMeetingActivityIntent = new Intent(MeetingListActivity.this, AddMeetingActivity.class);
                startActivity(addMeetingActivityIntent);
                Log.d("open", "ouverture layout");
            }
        });
    }

    public void init() {
        mMeetings = mMeetingRepository.getMeetings();
        mRooms = new ArrayList<>();
        mRooms.add(new Room(0, "Sélection d'une salle", 0));
        mRooms.addAll(mMeetingRepository.getRooms());
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings, mRooms);
        mRecyclerView.setAdapter(adapter);
        roomSpinnerAdapter = new RoomSpinnerAdapter(mRooms);
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeetingEvent(DeleteMeetingEvent event) {
        Log.d("selection", "bouton selectionné");
        mMeetingRepository.deleteMeeting(event.mMeeting);
        adapter.notifyDataSetChanged();
    }

}