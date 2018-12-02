package com.example.haley.dota2dayproj;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;

class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {


    /*TEST FOR POP UP CODE*/
    public static int cardID;

    private Context context;
    /*____________________*/

    private static final String TAG = "HeroAdapter";
    private List<Hero> heroList;
    //private final OnItemClickListener onItemClickListener ;

    /*created an interface which will contain the function declaration "onItemClick"
        Interface for the cardView popup
    */
   /* public interface OnItemClickListener{
        void onItemClick(Hero hero);
    }*/


    public HeroAdapter(ArrayList<Hero> heroList, Context context) {
     //   this.onItemClickListener = onItemClickListener;
        this.heroList = heroList;
        this.context = context;
    }


    /*This function is going to inflate (instantiate) a view from the row xml layout
    * and then return the view
    *
    * make sure to always pass the parent view or the default view will be displayed
    *
    * the last parameter "false" tells the inflater to attach the view to its root or not
    *   We do not need to do this as the recycler view already handles this behavior.
    *       Tells the inflator not to attach the inflated view to its parent
    *       attaching a view means adding it to the parent layout (like dragging a widget onto the layout)
    * */
    @NonNull
    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        /*TEST FOR POP UP CODE*/
        cardID++;
        /*____________________*/

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }


    /*
    * This method is called by the Recycler view when it wants new data to be stored in a
    *   ViewHolder so that it can display it
    *
    * As items scroll off the screen the Recycler view will provide a recycled
    *   ViewHolder object and tell us the position of the data object that it
    *   needs to display
    *
    * What we have to do in this method is get that item from the list and put its
    *       values into the ViewHolder widgets. This is normally easy we just store the
    *       photos attributes where they need to go respectively; This can be seen in the ViewHolder
    *       class.
    *
    * The issue here is that we're not store the actual hero, just the URL shown where to
    *   get the Hero Data from OpenDota.
    *
    * We are going to use a library called Picasso to cache the data to improve performance
    *   and energy as it will not require Wi-Fi or data to retrieve the data we need to grab
    *   1) add Picasso as a dependency
    *   2) This will help load images quicker (if I can get it to work)
    *
    * Recap
    *   1) we retrieved the current Hero from the list (first statement in the function
    *       Recycler view helps us here because it tells us the position of the data we need
    *       in the "i" parameter we can then consequently retrieve the exact photo from
    *       our arraylist that we saved int his object
    *   2) we are then logging what has been retrieved after so we can see what photo was retrieved
    *      anc it's position relative to the arraylist
    *   3) picasso is then used to load the image to avoid exstablishing another asynctask
    *   4) the last part called the bindData function which will store all the necessary
    *       data to the widgets in the Recycler.
    *
    *
    * */
    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.ViewHolder viewHolder, int i) {
        Hero heroItem = heroList.get(i);
        Log.d(TAG, "onBindViewHolder: " + heroItem.getName() + ", " + heroItem.getID() + ", " + heroItem.getPick_1() + " at position ----> " + i);

        viewHolder.bindData(heroItem);
    }


    /* returns the number of entries in the list
    *
    * utilizes a ternary operator
    *
    * */
    @Override
    public int getItemCount() {
        return ((heroList != null) &&  (heroList.size() != 0) ? heroList.size() : 0);
    }

    //This function will grab a Hero from the list at a specific position
    public Hero getHero(int position) {
        return ((heroList != null) &&  (heroList.size() != 0) ? heroList.get(position) : null);
    }

    public List<Hero> getHeroList(){
        return heroList;
    }



    /*loadNewData Function
    * when the query changes and new data is downloaded  we need to be able to
    *   provide the adapter with the new list
    *
    * sets the heroList (Class attribute) to the new list of Heroes
    *
    * "notifyDataSetChanged()" tells the recycler view that the data has changed
    *   so that it can go ahead and refresh the display
    * */
    void loadNewData(List<Hero> newHeroes){
        heroList = newHeroes;
        notifyDataSetChanged();

        /*_________HARRISON TEST CODE  (and some important stuff)_______*/
        Hero.GenerateDifference(heroList);  //THIS LINE IS IMPORTANT, IT POPULATES OUR ARRAY DATA
    }

    /*_______________INNER STATIC VIEW HOLDER CLASS______________*/
    class ViewHolder extends RecyclerView.ViewHolder{
      protected TextView full_name;
      protected final TextView character_ID;
      protected final TextView pick_1;
      protected CardView cardView;
      private LayoutInflater popUpInflater;
      private PopupWindow popupWindow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.full_name = itemView.findViewById(R.id.full_name);
            this.character_ID = itemView.findViewById(R.id.char_ID);
            this.pick_1 = itemView.findViewById(R.id.pick_1);
            this.cardView = itemView.findViewById(R.id.row);
            this.popUpInflater = LayoutInflater.from(itemView.getContext());

            cardView.setId(cardID);

            Log.d("cardId", String.valueOf(cardID));
        }

        public void bindData(final Hero hero) {

            //verifying that the data is being retrieved successfully
            Log.d(TAG, "bindData: hero ID is: " + hero.getID());
            Log.d(TAG, "bindData: hero 1_pick is: " + hero.getPick_1());

            //pushing data to display on the specified widgets
            full_name.setText(hero.getName());
            character_ID.setText(Integer.toString(hero.getID()));
            pick_1.setText(Integer.toString(hero.getPick_1()));

            //setting onclick listener on the cardview to create popup with the data
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    displayPopup(hero);

                    Toast.makeText(itemView.getContext(), "hero: " + hero.getName(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static class PopupDialogFragment extends DialogFragment {

        //wrapper will create a new fragment for you rather then call
        private static final String ARG_HERO = "hero";

        //WRapper method
        public static PopupDialogFragment newInstance(Hero hero) {

            Bundle args = new Bundle();

            PopupDialogFragment fragment = new PopupDialogFragment();
            args.putParcelable(ARG_HERO, hero);
            fragment.setArguments(args);
            return fragment;
        }

        //inflates the layout file and returns a view
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.popup_layout, container,
                    false);

            return rootView;
        }

        //assigns the argument values
        @Override
        public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(rootView, savedInstanceState);
            Hero hero = getArguments().getParcelable(ARG_HERO);
            BarChart barChart = rootView.findViewById(R.id.barGraph);


            //setting the x Axis labels
            ArrayList<String> labels = new ArrayList<String>();
                labels.add("Herald");   //pick 1
                labels.add("Guardian"); //pick 2
                labels.add("Crusader"); //pick 3
                labels.add("Archon");   //pick 4
                labels.add("Legend");   //pick 5
                labels.add("Ancient");  //pick 6
                labels.add("Divine");   //pick 7


            //creating a BarDataSet instance in order to display the data in a Bar Chart
            ArrayList<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry((int)hero.getp1p(), 0));
                entries.add(new BarEntry((int)hero.getp2p(), 1));
                entries.add(new BarEntry((int)hero.getp3p(), 2));
                entries.add(new BarEntry((int)hero.getp4p(), 3));
                entries.add(new BarEntry((int)hero.getp5p(), 4));
                entries.add(new BarEntry((int)hero.getp6p(), 5));
                entries.add(new BarEntry((int)hero.getp7p(), 6));



            //populating the data into the chart
            barChart.setDescription("");
            barChart.setDescriptionColor(Color.WHITE);
            BarDataSet bardataset = new BarDataSet(entries, "Pick Rate Percentage");
            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

            BarData data = new BarData(labels, bardataset);
            barChart.setData(data);
            barChart.getXAxis().setTextColor(Color.WHITE);
            barChart.getXAxis().setLabelsToSkip(0);
            bardataset.setBarSpacePercent(((float) (7) - data.getXValCount()) / (float) (7) * 100f);





            TextView fullName = rootView.findViewById(R.id.full_name);
            fullName.setText(hero.getName());

        }
    }


   public void displayPopup(Hero hero){
       PopupDialogFragment dialogFragment = PopupDialogFragment.newInstance(hero);
       dialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "OpenPopup");

    }

}