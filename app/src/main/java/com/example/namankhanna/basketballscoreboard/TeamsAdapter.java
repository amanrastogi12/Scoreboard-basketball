package com.example.namankhanna.basketballscoreboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    ArrayList<Team> teamArrayList;
    Context context;

    OnTeamNameListener otnl;


    public TeamsAdapter(ArrayList<Team> teamArrayList, Context context) {
        this.teamArrayList = teamArrayList;
        this.context = context;
    }

    public void setOnTeamNameListener(OnTeamNameListener otnl) {
        this.otnl = otnl;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_teams, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        final Team team = teamArrayList.get(position);
        holder.tvTeam1.setText(team.getTeam1());
        holder.tvTeam2.setText(team.getTeam2());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otnl != null) {
                    otnl.getTeamNames(team.getTeam1(), team.getTeam2());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamArrayList.size();
    }

    public static class TeamsViewHolder extends RecyclerView.ViewHolder{

        TextView tvTeam1, tvTeam2;
        View rootView;

        public TeamsViewHolder(View itemView) {
            super(itemView);
            tvTeam1 = itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvTeam2);
            rootView = itemView;
        }
    }
}
