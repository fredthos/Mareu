package com.example.mareu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mareu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MeetingListActivity extends AppCompatActivity {

    private FloatingActionButton mCreateMeetingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting);

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