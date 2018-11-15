package com.example.haley.dota2dayproj;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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