package com.example.namankhanna.basketballscoreboard;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements TeamsDialog.OnPositiveButtonListener {

    Toolbar toolbar;
    RecyclerView rvListTeams;
    FloatingActionButton fabAddTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar1);
        rvListTeams = findViewById(R.id.rvListTeams);
        fabAddTeams = findViewById(R.id.fabAddTeams);

        setSupportActionBar(toolbar);

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
        Toast.makeText(this, team1 + " " + team2, Toast.LENGTH_SHORT).show();
    }
}
