package com.example.haley.dota2dayproj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private BottomNavigationView bottomNavView;
    private ArrayList<String> appEntry;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    private RecyclerView.Adapter myAdapter;
    private LayoutInflater layoutInflater;

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


        bottomNavView = findViewById(R.id.bottom_navigation);
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

        });

    }

    @Override
    public void onClick(View v) {
        View popUpView = layoutInflater.inflate(R.layout.popup_layout, null);
    }
}
