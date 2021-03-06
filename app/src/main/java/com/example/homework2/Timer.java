package com.example.homework2;

import java.util.ArrayList;

public class Timer {

    private ArrayList<String> timeList;
    private int sec;
    private int min;
    private int hr;

    public Timer()
    {
        timeList = new ArrayList<String>();
        sec = 0;
        min = 0;
        hr = 0;
    }

    public Timer(ArrayList<String> times, int hours, int minutes, int seconds)
    {
        timeList = times;
        sec = seconds;
        min = minutes;
        hr = hours;
    }

    public ArrayList<String> getTimeList()
    {
        return timeList;
    }

    public int getSeconds()
    {
        return sec;
    }

    public int getMinutes()
    {
        return min;
    }

    public int getHours()
    {
        return hr;
    }

    public void calc()
    {
        sec++;
        if (sec == 60)
        {
            sec = 0;
            min++;
        }
        if (min == 60)
        {
            min = 0;
            hr++;
        }
    }

    public void addTime(String time)
    {
        timeList.add(time);
    }

    public void reset()
    {
        sec = 0;
        min = 0;
        hr = 0;
        timeList.clear();
    }

}
