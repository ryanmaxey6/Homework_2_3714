package com.example.homework2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    TextView lapDisplay;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        lapDisplay = view.findViewById(R.id.tvLapTimes);

        return view;
    }

    public void setDisplay(ArrayList<String> timeStamps)
    {
        lapDisplay.setText("");
        for (String stamp : timeStamps)
        {
            lapDisplay.append(stamp);
        }
    }

    public void clearDisplay()
    {
        lapDisplay.setText("");

    }
    public void appendTimeStamp(String timeStamp)
    {
        lapDisplay.append(timeStamp);
    }
}