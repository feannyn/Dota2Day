package com.example.haley.dota2dayproj;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GetDotaJSONData.OnDataAvailable {


    private BottomNavigationView bottomNavView;
    private ArrayList<Hero> hero_app;
    private RecyclerView RecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    private HeroAdapter heroAdapter;
    private LayoutInflater layoutInflater;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView = findViewById(R.id.my_recycler_view);
        myLayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(myLayoutManager);
        hero_app = new ArrayList<Hero>();

        /*__________________New HERO ADAPTER IMPLEMENTATION FOR POPUP________________*/
    /*    heroAdapter = new HeroAdapter(hero_app, new HeroAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Hero hero) {
                Toast.makeText(MainActivity.this, "hero: " + hero.getName(), Toast.LENGTH_LONG).show();




            }
        });*/
        /*___________________________________________________________*/






        heroAdapter = new HeroAdapter(hero_app, this);

        RecyclerView.setAdapter(heroAdapter); //associates our adapter to the Hero adapter (it's the link between the classes)

        RecyclerView.setHasFixedSize(true);
        bottomNavView = findViewById(R.id.bottom_navigation);

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.AtoZSort:
                        Collections.sort(hero_app, new AlphabeticComparator());
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


    /*________MIGHT NEED TO DELETE THIS IT IS DUPLICATIVE*/
    @Override
    public void onClick(View v) {
        View popUpView = layoutInflater.inflate(R.layout.popup_layout, null);
    }


    /*
     * onResume is overriding the previous functionality above (GETRAWDATA /.Execute)
     * This is done because because when we pause the current activity and return we want
     * to resume what we were initially doing.
     * */
    @Override
    protected void onResume() {
        // Log.d(TAG, "onResume: starts...");
        super.onResume();
        GetDotaJSONData getDotaJSONData = new GetDotaJSONData(this, "https://api.opendota.com/api/heroStats", "en-us", true);
        getDotaJSONData.execute("");
    }


    //This method is created to verify that everything is working as expected
    //it will not be used once everything is deployed but is a mechanism created
    //to assist in the development process.
    public void onDataAvailable(List<Hero> data, DownloadStatus status) {
        Log.d(TAG, "onDataAvailable: starts...");

        if (status == DownloadStatus.OK) {
            heroAdapter.loadNewData(data);
        } else {
            //download or processing failed
            Log.e(TAG, "onDownloadComplete failed with status " + status);
        }

        Log.d(TAG, "onDataAvailable: ends...");

    }

}

//this class is used to sort our list of heroes alphabetically.
//it compares the names of two heroes and returns a value.
//this will be passed into the sort() function in order to
//sort a list of heroes alphabetically.
class AlphabeticComparator implements Comparator<Hero> {

    @Override
    public int compare(Hero A, Hero B) {
        String nameA = A.getName();
        String nameB = B.getName();
        //built in string comparison function
        return nameA.compareToIgnoreCase(nameB);
    }
}

class DifferenceComparator implements Comparator<Hero> {

    @Override
    public int compare(Hero A, Hero B) {
        if (A.getPick_difference() > B.getPick_difference()) return 1;
        else if (A.getPick_difference() < B.getPick_difference()) return -1;
        return 0;
    }
}