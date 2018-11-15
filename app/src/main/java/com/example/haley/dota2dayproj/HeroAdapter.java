package com.example.haley.dota2dayproj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    private List<Hero> hero_app;

    public HeroAdapter(ArrayList<Hero> heroApp) {
        this.hero_app = heroApp;
    }

    @NonNull
    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindData(hero_app.get(i));

    }

    @Override
    public int getItemCount() {
        return hero_app.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
      public TextView full_name;
      public final TextView character_ID;
      public final TextView pick_1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.full_name = itemView.findViewById(R.id.full_name);
            this.character_ID = itemView.findViewById(R.id.characterID);
            this.pick_1 = itemView.findViewById(R.id.pick_1);
        }

        public void bindData(final Hero hero) {
            full_name.setText(hero.getName());
            character_ID.setText(hero.getID());
            pick_1.setText(hero.getPick_1());
        }

    }
}












































    /*
    private static final String Tag = "HeroAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Hero> applications;

    public HeroAdapter(Context context, int resource, List<Hero> applications){
        super(); //idk
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount(){
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag(); //there like, has to be an error here
        }
        Hero currentApp = applications.get(position);
        viewHolder.full_name.setText(currentApp.getName());
        viewHolder.character_ID.setText(currentApp.getID());
        viewHolder.pick_1.setText(currentApp.getPick_1());

        return convertView;
    }
    private class ViewHolder{
        //Will add the other attributes later;
        final TextView full_name;
        final TextView character_ID;
        final TextView pick_1;

        ViewHolder(View v){
            this.full_name = v.findViewById(R.id.full_name)
            this.character_ID = v.findViewById(R.id.characterID);
            this.pick_1 = v.findViewById(R.id.pick_1);
        }
    }
    */
}