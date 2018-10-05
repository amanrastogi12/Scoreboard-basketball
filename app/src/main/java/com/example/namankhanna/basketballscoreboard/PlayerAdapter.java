package com.example.namankhanna.basketballscoreboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    ArrayList<Players> playersArrayList;
    Context context;

    public PlayerAdapter(ArrayList<Players> playersArrayList, Context context) {
        this.playersArrayList = playersArrayList;
        this.context = context;
    }

    public void updatePlayers(ArrayList<Players> playersArrayList) {
        this.playersArrayList = playersArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_players, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Players players = playersArrayList.get(position);
        holder.tvName.setText(players.getName());
        holder.tvTNum.setText(String.valueOf(players.gettNum()));
    }

    @Override
    public int getItemCount() {
        return playersArrayList.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvTNum;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPlayerName);
            tvTNum = itemView.findViewById(R.id.tvPlayerTNum);
        }
    }
}
