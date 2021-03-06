package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListFragment listFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // check if we are in landscape mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
            return;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        ArrayList<String> lapTimes = i.getStringArrayListExtra("lap_times");
        listFragment2 = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);
        listFragment2.setDisplay(lapTimes);
    }

    public void clickedBack(View view)
    {
        Intent i1 = new Intent(this, MainActivity.class);
        startActivity(i1);
    }
}