package com.example.namankhanna.basketballscoreboard;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    TextView tvTimer1, tvTimer2, tvTeam1, tvTeam2;
    RecyclerView rvPlayers1, rvPlayers2;
    TextView tvScore1, tvScore2;
    FloatingActionButton fabPlayTimer1, fabPlayTimer2, fabPauseTimer1, fabPauseTimer2;
    FloatingActionButton fabAddFouls1, fabAddFouls2, fabAddTimeout1, fabAddTimeout2;
    FloatingActionButton fabRemoveFouls1, fabRemoveFouls2, fabRemoveTimeout1, fabRemoveTimeout2;
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    PlayersAdapter adapter1, adapter2;
    FirebaseDatabase firebaseDatabase;
    String team1, team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvTeam1 = findViewById(R.id.tvGameTeam1);
        tvTeam2 = findViewById(R.id.tvGameTeam2);
        rvPlayers1 = findViewById(R.id.rvPlayers1);
        rvPlayers2 = findViewById(R.id.rvPlayers2);
        firebaseDatabase = FirebaseDatabase.getInstance();

        rvPlayers1.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new PlayersAdapter(players1, this);
        rvPlayers1.setAdapter(adapter1);

        rvPlayers2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new PlayersAdapter(players2, this);
        rvPlayers2.setAdapter(adapter2);

        team1 = getIntent().getStringExtra("TEAM1");
        team2 = getIntent().getStringExtra("TEAM2");
        tvTeam1.setText(team1);
        tvTeam2.setText(team2);

        readDataFromDatabase();
    }

    private void readDataFromDatabase() {
        DatabaseReference reference1 = firebaseDatabase.getReference().child("Players").child(team1);
        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Player player = dataSnapshot.getValue(Player.class);
                players1.add(player);
                adapter1.updatePlayer(players1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference reference2 = firebaseDatabase.getReference().child("Players").child(team2);
        reference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Player player = dataSnapshot.getValue(Player.class);
                players2.add(player);
                adapter2.updatePlayer(players2);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
