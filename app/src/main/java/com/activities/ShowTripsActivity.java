package com.activities;

import android.app.Activity;
import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tickshare.R;


public class ShowTripsActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triplistview);
        recyclerView = (RecyclerView) findViewById(R.id.tripListRecyclerView);
        String[] possibleTrips = new String[MainActivity.tripManager.getTripList().size()];

        for (int i = 0; i < MainActivity.tripManager.getTripList().size(); i++) {
            possibleTrips[i] = MainActivity.tripManager.getTripList().get(i).toString();
        }


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(possibleTrips);
        recyclerView.setAdapter(mAdapter);
    }
}
