package com.example.haley.dota2dayproj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import static android.net.wifi.WifiConfiguration.PairwiseCipher.strings;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    //    private BottomNavigationView bottomNavView;
        ArrayList<String> appEntry;
        RecyclerView myRecyclerView;
        RecyclerView.LayoutManager myLayoutManager;
        RecyclerView.Adapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = findViewById(R.id.my_recycler_view);

        appEntry = new ArrayList<>();
        appEntry.add("Earthshaker");
        appEntry.add("Sven");
        appEntry.add("Tiny");
        appEntry.add("Kunkka");
        appEntry.add("Beastmaster");
        appEntry.add("Dragon Knight");
        appEntry.add("Clockwerk");
        appEntry.add("Omninight");
        appEntry.add("Io");
        appEntry.add("Tusk");

        for (int i = 0; i < 100; i++) {
            appEntry.add("Earthshaker # " + i);
        }

        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new MainAdapter(appEntry);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);



   /*     bottomNavView = findViewById(R.id.bottom_navigation);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.AtoZSort:
                        Toast.makeText(MainActivity.this, "Sort A-Z Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.HighSort:
                        Toast.makeText(MainActivity.this, "Sort High to Low Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.LowSort:
                        Toast.makeText(MainActivity.this, "Sort Low to High Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.charSort:
                        Toast.makeText(MainActivity.this, "Sort By Character Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        }); */


    }

    /*

    private class DownloadData extends AsyncTask<String, Void, String> {

        private static final String TAG = "DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter is " + s);

        @Override
        protected String doInBackground(String... strings) {
                Log.d(TAG, "onPostExecute: starts with " + strings[0]);
                return "doInBackground completed.";
        }


        }
    } */

}
