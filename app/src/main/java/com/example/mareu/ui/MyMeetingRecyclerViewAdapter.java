package com.example.mareu.ui;

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

import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetings;
    private MeetingRepository mRepository = DI.getMeetingRepository();

    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent,false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting currentMeeting = mMeetings.get(position);
        Room currentRoom = mRepository.getRoomById(currentMeeting.getRoomId());
        holder.mMeetingRoomDesign.setImageDrawable(getDrawable(holder.mMeetingRoomName.getContext(), currentRoom.getRoomDesign()));
        holder.mMeetingRoomName.setText(currentRoom.getRoomName());
        holder.mMeetingParticipants.setText(currentMeeting.getMeetingParticipants().toString());

        holder.mMeetingDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EventBus.getDefault().post(new DeleteMeetingEvent(currentMeeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder{

        private final ImageView mMeetingRoomDesign;
        private final TextView mMeetingRoomName;
        private final TextView mMeetingDate;
        private final TextView mMeetingParticipants;
        private final ImageButton mMeetingDelete;

        public MeetingViewHolder(View itemview) {
            super(itemview);
            mMeetingRoomDesign = itemView.findViewById(R.id.meeting_room_design);
            mMeetingRoomName = itemView.findViewById(R.id.meeting_room_name) ;
            mMeetingDate = itemView.findViewById(R.id.meeting_date);
            mMeetingParticipants = itemView.findViewById(R.id.meeting_participants);
            mMeetingDelete = itemView.findViewById(R.id.meeting_delete);

            mMeetingParticipants.setSelected(true);
        }
    }
}
