package com.example.namankhanna.basketballscoreboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvTeam1, tvTeam2;
    EditText etPlayer1, etPlayer2, etTNum1, etTNum2;
    FloatingActionButton fabPlayer1, fabPlayer2;
    RecyclerView rvPlayer1, rvPlayer2;
    Button btnPlay;
    FirebaseDatabase firebaseDatabase;
    String team1, team2;
    ArrayList<Player> players1 = new ArrayList<>();
    ArrayList<Player> players2 = new ArrayList<>();
    PlayersAdapter adapter1, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        toolbar = findViewById(R.id.toolbar2);
        tvTeam1 = findViewById(R.id.tvTeamName1);
        tvTeam2 = findViewById(R.id.tvTeamName2);
        etPlayer1 = findViewById(R.id.etPlayer1);
        etPlayer2 = findViewById(R.id.etPlayer2);
        etTNum1 = findViewById(R.id.etTNum1);
        etTNum2 = findViewById(R.id.etTNum2);
        fabPlayer1 = findViewById(R.id.fabAddPlayer1);
        fabPlayer2 = findViewById(R.id.fabAddPlayer2);
        rvPlayer1 = findViewById(R.id.rvListPlayers1);
        rvPlayer2 = findViewById(R.id.rvListPlayers2);
        btnPlay = findViewById(R.id.btnPlay);
        firebaseDatabase = FirebaseDatabase.getInstance();

        setSupportActionBar(toolbar);

        team1 = getIntent().getStringExtra("TEAM1");
        team2 = getIntent().getStringExtra("TEAM2");
        tvTeam1.setText(team1);
        tvTeam2.setText(team2);

        rvPlayer1.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new PlayersAdapter(players1, this);
        rvPlayer1.setAdapter(adapter1);

        rvPlayer2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new PlayersAdapter(players2, this);
        rvPlayer2.setAdapter(adapter2);

        readPlayersFromDatabase(team1, team2);

        fabPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etPlayer1.getText().toString()) && !TextUtils.isEmpty(etTNum1.getText().toString())) {
                    writePlayerInDatabase(etPlayer1.getText().toString(), Integer.valueOf(etTNum1.getText().toString()), team1);
                    etPlayer1.setText("");
                    etTNum1.setText("");
                }
            }
        });

        fabPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etPlayer2.getText().toString()) && !TextUtils.isEmpty(etTNum2.getText().toString())) {
                    writePlayerInDatabase(etPlayer2.getText().toString(), Integer.valueOf(etTNum2.getText().toString()), team2);
                    etPlayer2.setText("");
                    etTNum2.setText("");
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(players1.size() == 10 && players2.size() == 10) {
                    Intent i = new Intent(PlayerActivity.this, GameActivity.class);
                    i.putExtra("TEAM1", team1);
                    i.putExtra("TEAM2", team2);
                    startActivity(i);
                }
            }
        });

    }

    private void readPlayersFromDatabase(String team1, String team2) {
        DatabaseReference reference1 = firebaseDatabase.getReference().child("Players").child(team1);
        DatabaseReference reference2 = firebaseDatabase.getReference().child("Players").child(team2);
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

    private void writePlayerInDatabase(final String name, final Integer num, String team) {
        DatabaseReference reference = firebaseDatabase.getReference().child("Players").child(team).push();
        if(team.equals(team1) && players1.size() < 10) {
            reference.setValue(new Player(name, reference.getKey(), 0, num));
        }
        else if(team.equals(team2) && players2.size() < 10){
            reference.setValue(new Player(name, reference.getKey(), 0, num));
        }
        else {
            Toast.makeText(this, "Team Limit Reached", Toast.LENGTH_SHORT).show();
        }
    }
}
