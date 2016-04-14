package com.example.giorgi.retrofitsimpleproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.giorgi.retrofitsimpleproject.models.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giorgi on 4/6/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView name;
        TextView full_name;

        PersonViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvTitle);
            full_name = (TextView) itemView.findViewById(R.id.tvLanguage);

            itemView.setOnClickListener(this);

        }

        // onItemClick
        @Override
        public void onClick(View view) {
            Log.d("nextActivity", "onClick " + getPosition());

            Context context = view.getContext();
            if(getPosition()==1){
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }

        }
    }

    public RVAdapter() {
        repos = new ArrayList<>();
    }

    public void addAll(List<Repo> repos) {
//        this.repos.addAll(repos);
        this.repos = repos;
    }

    List<Repo> repos;

    //constructor
//    RVAdapter(List<Repo> repos) {
//        this.repos = repos;
//    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singlelayout, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(repos.get(i).getName());
        personViewHolder.full_name.setText(repos.get(i).getFull_name());
    }

    @Override
    public int getItemCount() {
        return repos != null ? repos.size() : 0;
    }
}