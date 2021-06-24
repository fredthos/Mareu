package com.example.mareu.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.DummyMeetingGenerator;
import com.example.mareu.repository.MeetingRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

    private FloatingActionButton mCreateMeetingBtn;

    private List<Meeting> mFakeMeeting;
    private MeetingRepository mMeetingRepository;
    private RecyclerView mRecyclerView;
    private MyMeetingRecyclerViewAdapter mMyMeetingRecyclerViewAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        if (menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_date:
                Toast.makeText(this, R.string.filterByDate, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_room:
                Toast.makeText(this, R.string.filterByRoom, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reset:
                Toast.makeText(this,R.string.resetFilter, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

        mMeetingRepository = DI.getMeetingRepository();
        mRecyclerView=findViewById(R.id.recycler_view_meeting);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFakeMeeting = DummyMeetingGenerator.DUMMY_FAKE_MEETING;
        mMyMeetingRecyclerViewAdapter = new MyMeetingRecyclerViewAdapter(mFakeMeeting);
        mRecyclerView.setAdapter(mMyMeetingRecyclerViewAdapter);

        mCreateMeetingBtn = findViewById(R.id.create_meeting);
        mCreateMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMeetingActivityIntent = new Intent(MeetingListActivity.this, AddMeetingActivity.class);
                startActivity(addMeetingActivityIntent);
            }
        });

    }
}