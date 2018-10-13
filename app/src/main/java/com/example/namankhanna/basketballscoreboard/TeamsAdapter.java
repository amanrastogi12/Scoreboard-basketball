package com.example.namankhanna.basketballscoreboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    ArrayList<Teams> teamsArrayList;
    Context context;

    public TeamsAdapter(ArrayList<Teams> teamsArrayList, Context context) {
        this.teamsArrayList = teamsArrayList;
        this.context = context;
    }

    public void updateTeams(ArrayList<Teams> teamsArrayList) {
        this.teamsArrayList = teamsArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_team, parent, false);
        return new TeamsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Teams teams = teamsArrayList.get(position);
        holder.tvTeam1.setText(teams.getTeam1());
        holder.tvTeam2.setText(teams.getTeam2());
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }

    public static class TeamsViewHolder extends RecyclerView.ViewHolder {

        TextView tvTeam1, tvTeam2;

        public TeamsViewHolder(View itemView) {
            super(itemView);
            tvTeam1 = itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvTeam2);
        }
    }
}
