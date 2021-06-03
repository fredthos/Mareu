package com.example.mareu.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.repository.MeetingRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeetingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private MeetingRepository mRepository;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;

    public static MeetingFragment newInstance() {
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    //public MeetingFragment() {
        // Required empty public constructor
    //}

    ///**
     //* Use this factory method to create a new instance of
     //* this fragment using the provided parameters.
     //*
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     //* @return A new instance of fragment MeetingFragment.
     //*/
    // TODO: Rename and change types and number of parameters
    //public static MeetingFragment newInstance(String param1, String param2) {
       //MeetingFragment fragment = new MeetingFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        //return fragment;
    //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = DI.getMeetingRepository();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_list, container,false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return view;
    }

    public void initList() {
        mMeetings = mRepository.getMeetings();
        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(mMeetings));
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}