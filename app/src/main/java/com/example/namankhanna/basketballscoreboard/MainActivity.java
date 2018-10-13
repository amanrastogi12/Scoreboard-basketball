package com.example.namankhanna.basketballscoreboard;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TeamsDialog.OnPositiveButtonListener {

    Toolbar toolbar;
    RecyclerView rvListTeams;
    FloatingActionButton fabAddTeams;
    FirebaseDatabase firebaseDatabase;
    ArrayList<Teams> teamsArrayList = new ArrayList<>();
    TeamsAdapter teamsAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar1);
        rvListTeams = findViewById(R.id.rvListTeams);
        fabAddTeams = findViewById(R.id.fabAddTeams);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(this);

        setSupportActionBar(toolbar);

        rvListTeams.setLayoutManager(new LinearLayoutManager(this));
        teamsAdapter = new TeamsAdapter(teamsArrayList, this);
        rvListTeams.setAdapter(teamsAdapter);

        readFromDatabase();

        fabAddTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTeamDialog();
            }
        });
    }

    private void openTeamDialog() {
        TeamsDialog dialog = new TeamsDialog();
        dialog.show(getSupportFragmentManager(), "TEAM_DIALOG");
    }

    @Override
    public void getTeamNames(String team1, String team2) {
        DatabaseReference reference = firebaseDatabase.getReference().child("Games").push();
        reference.setValue(
                new Teams(team1, team2, reference.getKey())
        );
    }

    private void readFromDatabase() {
        dialog.setMessage("Updating...");
        dialog.show();
        DatabaseReference reference = firebaseDatabase.getReference().child("Games");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Teams teams = dataSnapshot.getValue(Teams.class);
                teamsArrayList.add(teams);
                teamsAdapter.updateTeams(teamsArrayList);
                dialog.dismiss();
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
