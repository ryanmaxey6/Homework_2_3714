package com.example.homework2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TimerFragment extends Fragment implements View.OnClickListener {

    Button startB;
    Button restartB;
    Button lapB;
    TextView timeDisplay;
    // declare interaction listener
    private OnFragmentInteractionListener theListener;

    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        // initialize buttons
        startB = (Button) view.findViewById(R.id.startButton2);
        restartB = (Button) view.findViewById(R.id.restartButton2);
        lapB = (Button) view.findViewById(R.id.lapButton2);
        timeDisplay = (TextView) view.findViewById(R.id.tvTime2);

        // add listeners
        startB.setOnClickListener(this);
        restartB.setOnClickListener(this);
        lapB.setOnClickListener(this);

        return view;
    }

    public void setTimeDisplay(String time)
    {
        timeDisplay.setText(time);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener)
        {
            this.theListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement " +
                    "OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == startB.getId())
        {
            theListener.onButtonClicked(0);
        }
        else if (view.getId() == restartB.getId())
        {
            theListener.onButtonClicked(1);
        }
        else if (view.getId() == lapB.getId())
        {
            theListener.onButtonClicked(2);
        }
    }

    public interface OnFragmentInteractionListener {
        void onButtonClicked(int infoID);
    }
}