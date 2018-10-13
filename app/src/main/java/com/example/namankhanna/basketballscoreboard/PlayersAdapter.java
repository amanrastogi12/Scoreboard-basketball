package com.example.namankhanna.basketballscoreboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {

    ArrayList<Player> playerArrayList;
    Context context;

    public PlayersAdapter(ArrayList<Player> playerArrayList, Context context) {
        this.playerArrayList = playerArrayList;
        this.context = context;
    }

    public void updatePlayer(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_player, parent, false);
        return new PlayersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        Player player = playerArrayList.get(position);
        holder.tvPlayer.setText(player.getName());
        holder.tvTNum.setText(String.valueOf(player.gettNum()));
    }

    @Override
    public int getItemCount() {
        return playerArrayList.size();
    }

    public static class PlayersViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlayer, tvTNum;

        public PlayersViewHolder(View itemView) {
            super(itemView);
            tvPlayer = itemView.findViewById(R.id.tvPlayer);
            tvTNum = itemView.findViewById(R.id.tvTNum);
        }
    }
}
