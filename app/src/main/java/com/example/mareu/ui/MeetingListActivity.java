package com.example.mareu.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.repository.DummyMeetingGenerator;
import com.example.mareu.repository.MeetingRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Comparator;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

    private FloatingActionButton mCreateMeetingBtn;

    private List<Meeting> mMeetings;
    private List<Room> mRooms;
    private MeetingRepository mMeetingRepository;
    private RecyclerView mRecyclerView;
    private MyMeetingRecyclerViewAdapter adapter;

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
        if (itemId == R.id.Trier_date) {
            mMeetings = mMeetingRepository.getMeetings();
            mMeetings.sort(new Comparator<Meeting>() {
                @Override
                public int compare(Meeting m1, Meeting m2) {
                    return m1.getMeetingDate().compareTo(m2.getMeetingDate());
                }
            });
            adapter.setMeetings(mMeetings);
            Toast.makeText(this, R.string.trierByDate, Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.Trier_room) {
            mMeetings = mMeetingRepository.getMeetings();
            mMeetings.sort(new Comparator<Meeting>() {
                @Override
                public int compare(Meeting m1, Meeting m2) {
                    return Long.compare(m1.getMeetingRoomId(), m2.getMeetingRoomId());
                }
            });
            adapter.setMeetings(mMeetings);
            Toast.makeText(this, R.string.trierByRoom, Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.Reset) {
            mMeetings = mMeetingRepository.getMeetings();
            //mMeetings.clear();
            //mMeetings.addAll(mMeetings);
            adapter.setMeetings(mMeetings);
            Toast.makeText(this, R.string.reset, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        mMeetingRepository = DI.getMeetingRepository();
        mRecyclerView = findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initList();

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

    public void initList() {
        mMeetings = mMeetingRepository.getMeetings();
        mRooms = mMeetingRepository.getRooms();
        //mMeetings.addAll(DUMMY_FAKE_MEETING);
        adapter = new MyMeetingRecyclerViewAdapter(mMeetings, mRooms);
        mRecyclerView.setAdapter(adapter);
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