package com.example.mareu.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.repository.MeetingRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetings;
    private final List<Room> mRooms;
    private MeetingRepository mRepository = DI.getMeetingRepository();

    public void setMeetings (List<Meeting> meetingList) {
        mMeetings = meetingList;
        notifyDataSetChanged();
    }

    public MyMeetingRecyclerViewAdapter(List<Meeting> mettings, List<Room> rooms) {
        mMeetings = mettings;
        mRooms = rooms;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent,false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MeetingViewHolder holder, int position) {
        Meeting currentMeeting = mMeetings.get(position);
        Room currentRoom = mRepository.getRoomById(currentMeeting.getMeetingRoomId());
        holder.mMeetingRoomDesign.setImageDrawable(holder.itemView.getContext().getDrawable(currentRoom.getRoomDesign()));
        holder.mMeetingRoomName.setText(currentRoom.getRoomName());
        holder.mMeetingSubjet.setText(currentMeeting.getMeetingSubject());
        holder.mMeetingDate.setText(currentMeeting.getMeetingDate());
        holder.mMeetingTime.setText(currentMeeting.getMeetingStartTime());
        holder.mMeetingDuration.setText(currentMeeting.getMeetingDuration());
        holder.mMeetingParticipants.setText(currentMeeting.getMeetingParticipants().toString());

        holder.mMeetingDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(currentMeeting));
                Log.d("click", "bouton clické");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.meeting_room_design)
        public ImageView mMeetingRoomDesign;
        @BindView(R.id.meeting_room_name)
        public TextView mMeetingRoomName;
        @BindView(R.id.meeting_subject)
        public TextView mMeetingSubjet;
        @BindView(R.id.meeting_date)
        public TextView mMeetingDate;
        @BindView(R.id.meeting_time)
        public TextView mMeetingTime;
        @BindView(R.id.meeting_duration)
        public TextView mMeetingDuration;
        @BindView(R.id.meeting_participants)
        public TextView mMeetingParticipants;
        @BindView(R.id.meeting_delete)
        public ImageButton mMeetingDelete;

        public MeetingViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this,itemview);
        }
    }
}
