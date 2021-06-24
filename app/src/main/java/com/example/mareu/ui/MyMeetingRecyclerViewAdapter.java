package com.example.mareu.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.repository.MeetingRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MeetingViewHolder> {

    private final List<Meeting> mMeetings;
    private MeetingRepository mRepository = DI.getMeetingRepository();

    public MyMeetingRecyclerViewAdapter(List<Meeting> mettings ) {
        mMeetings = mettings;
    }

    @Override
    public MeetingViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent,false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MeetingViewHolder holder, int position) {
        Meeting currentMeeting = mMeetings.get(position);
        Room currentRoom = mRepository.getRoomById(currentMeeting.getRoomId());
        holder.setDataMeeting(currentRoom,currentMeeting);
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mMeetingRoomDesign;
        private final TextView mMeetingRoomName;
        private final TextView mMeetingSubject;
        private final TextView mMeetingDate;
        private final TextView mMeetingDuration;
        private final TextView mMeetingTime;
        private final TextView mMeetingParticipants;
        private final ImageButton mMeetingDelete;

        public void setDataMeeting(Room currentRoom, Meeting currentMeeting) {
            mMeetingRoomDesign.setImageDrawable(getDrawable(mMeetingRoomName.getContext(), currentRoom.getRoomDesign()));
            mMeetingRoomName.setText(currentRoom.getRoomName());
            mMeetingSubject.setText(currentMeeting.getMeetingSubject());
            mMeetingDate.setText(currentMeeting.getMeetingDate());
            mMeetingTime.setText(currentMeeting.getMeetingStartTime());
            mMeetingDuration.setText(currentMeeting.getMeetingDuration());
            mMeetingParticipants.setText(currentMeeting.getMeetingParticipants().toString());

            mMeetingDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new DeleteMeetingEvent(currentMeeting));
                }
            });
        }

        public MeetingViewHolder(View itemview) {
            super(itemview);
            mMeetingRoomDesign = itemView.findViewById(R.id.meeting_room_design);
            mMeetingRoomName = itemView.findViewById(R.id.meeting_room_name);
            mMeetingSubject = itemview.findViewById(R.id.meeting_subject);
            mMeetingDate = itemView.findViewById(R.id.meeting_date);
            mMeetingDuration = itemview.findViewById(R.id.meeting_duration);
            mMeetingTime = itemview.findViewById(R.id.meeting_time);
            mMeetingParticipants = itemView.findViewById(R.id.meeting_participants);
            mMeetingDelete = itemView.findViewById(R.id.meeting_delete);

            mMeetingParticipants.setSelected(true);
        }
    }
}
