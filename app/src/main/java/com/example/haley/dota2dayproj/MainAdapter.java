package com.example.haley.dota2dayproj;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<String> appEntry;

    public MainAdapter(ArrayList<String> Entry){
        appEntry = Entry;
    }


    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int i) {
        viewHolder.fullName.setText(appEntry.get(i));
    }

    @Override
    public int getItemCount() {
        return appEntry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.full_name);
        }
    }
}