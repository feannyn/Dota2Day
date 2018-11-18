package com.example.haley.dota2dayproj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    private static final String TAG = "HeroAdapter";
    private List<Hero> heroList;
    private Context context;

    public HeroAdapter(Context context, ArrayList<Hero> heroList) {
        this.context = context;
        this.heroList = heroList;
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
        //Picasso.with(context).load(heroItem.getPhoto).error(R.drawable.placeholder).placeholder(R.drawable.placeholder).into(holder.thumbnail);

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

        //System.out.println("SIZE OF hero_app: " + heroList.size());
        /*______________________________________________________________*/
    }

    /*_______________INNER STATIC VIEW HOLDER CLASS______________*/
    static class ViewHolder extends RecyclerView.ViewHolder{
      public TextView full_name;
      public final TextView character_ID;
      public final TextView pick_1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.full_name = itemView.findViewById(R.id.full_name);
            this.character_ID = itemView.findViewById(R.id.char_ID);
            this.pick_1 = itemView.findViewById(R.id.pick_1);
        }

        public void bindData(final Hero hero) {
            Log.d(TAG, "bindData: hero ID is: " + hero.getID());
            Log.d(TAG, "bindData: hero 1_pick is: " + hero.getPick_1());


            full_name.setText(hero.getName());
            character_ID.setText(Integer.toString(hero.getID()));
            pick_1.setText(Integer.toString(hero.getPick_1()));
        }

    }
}