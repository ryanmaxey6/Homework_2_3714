package com.example.homework2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TimerFragment.OnFragmentInteractionListener {

    TimerFragment timerFragment;
    ListFragment listFragment;
    Timer timer;
    boolean running;
    private TimerAsyncTask asyncTask;
    private static final String LOG_NAME = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
        {
            Log.d(LOG_NAME, "no saved instance state");
        }

        // create fragment references
        timerFragment = (TimerFragment) getSupportFragmentManager().findFragmentById(R.id.timerFrag);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);

        // initialize timer
        timer = new Timer();
        running = false;

        // initialize TimerAsyncTask
        asyncTask = new TimerAsyncTask();

        Log.i(LOG_NAME, "onCreate finished");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("running", running);
        outState.putInt("seconds", timer.getSeconds());
        outState.putInt("minutes", timer.getMinutes());
        outState.putInt("hours", timer.getHours());
        outState.putStringArrayList("time_list", timer.getTimeList());
        Log.i(LOG_NAME, "onSaveInstanceState finished");
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState)
    {
        super.onRestoreInstanceState(inState);
        ArrayList<String> times = inState.getStringArrayList("time_list");
        int seconds = inState.getInt("seconds");
        int minutes = inState.getInt("minutes");
        int hours = inState.getInt("hours");
        timer = new Timer(times, hours, minutes, seconds);
        running = inState.getBoolean("running");
        Log.i(LOG_NAME, "onRestoreInstanceState finished");
        if (asyncTask.getStatus() != AsyncTask.Status.RUNNING)
        {
            asyncTask = new TimerAsyncTask();
            asyncTask.execute(0, 0, 0);
            timerFragment.startB.setText("Stop");
        }
        if (!running)
        {
            timerFragment.startB.setText("Start");
        }
        String currTime = String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes(),
                timer.getSeconds());
        timerFragment.setTimeDisplay(currTime);
        if (listFragment != null && listFragment.isInLayout())
        {
            listFragment.setDisplay(timer.getTimeList());
        }
    }

    @Override
    protected void onDestroy()
    {
        // checking if async task is still running
        if (asyncTask != null && asyncTask.getStatus() == AsyncTask.Status.RUNNING)
        {
            // cancel the task before destroying the activity
            asyncTask.cancel(true);
            asyncTask = null;
        }
        super.onDestroy();
        Log.i(LOG_NAME, "onDestroy finished");
    }

    @Override
    public void onButtonClicked(int infoID) {
        if (infoID == 0) // start/stop button pressed
        {
            if (!running)
            {
                // start timer
                running = true;
                timerFragment.startB.setText("Stop");
                if (asyncTask.getStatus() != AsyncTask.Status.RUNNING)
                {
                    asyncTask = new TimerAsyncTask();
                    asyncTask.execute(0, 0, 0);
                }
            }
            else
            {
                // stop timer
                running = false;
                timerFragment.startB.setText("Start");
//                asyncTask.cancel(true);
            }
        }
        else if (infoID == 1) // reset button pressed
        {
            // clicking reset should reset everything in both screens
            running = false;
            timerFragment.startB.setText("Start");
//            asyncTask.cancel(true);
            timer.reset();
            String currTime = String.format("%02d:%02d:%02d", timer.getHours(), timer.getMinutes(),
                    timer.getSeconds());
            timerFragment.setTimeDisplay(currTime);
            if (listFragment != null && listFragment.isInLayout())
            {
                listFragment.clearDisplay();
            }
        }
        else if (infoID == 2) // lap button pressed
        {
            int length = timer.getTimeList().size() + 1;
            String lapTime = String.format("%d. %02d:%02d:%02d\n", length, timer.getHours(),
                    timer.getMinutes(), timer.getSeconds());
            timer.addTime(lapTime);
            if (listFragment != null && listFragment.isInLayout())
            {
                listFragment.appendTimeStamp(lapTime);
            }
        }
    }

    private class TimerAsyncTask extends AsyncTask<Integer, Integer, Void>
    {
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            String currTime = String.format("%02d:%02d:%02d", values[0], values[1], values[2]);
            Log.i(LOG_NAME, currTime);
            timerFragment.setTimeDisplay(currTime);
        }

        @Override
        protected Void doInBackground(Integer... times) {
            while (running)
            {
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                    System.out.println(e);
                    Log.i(LOG_NAME, "and some other stuff");
                }
                if (isCancelled())
                {
                    break;
                }
                if (running)
                {
                    timer.calc();
                }
                publishProgress(timer.getHours(), timer.getMinutes(), timer.getSeconds());
            }
            return null;
        }
    }

    public void clickedList(View view)
    {
        Intent i1 = new Intent(this, ListActivity.class);
        i1.putStringArrayListExtra("lap_times", timer.getTimeList());
        startActivity(i1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_NAME, "onStart finished");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_NAME, "onResume finished");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_NAME, "onPause finished");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_NAME, "onStop finished");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_NAME, "onRestart finished");
    }

}