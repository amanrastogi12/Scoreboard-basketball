package com.example.namankhanna.basketballscoreboard;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TeamDialog.OnPositiveButtonListener {

    Toolbar myToolbar;
    FloatingActionButton fabAddTeams;
    TeamDatabaseHelper teamDatabaseHelper;
    public static final String TAG = "MAIN_ACTIVITY";
    ArrayList<Team> teamArrayList = new ArrayList<>();
    RecyclerView rvListTeams;
    TeamsAdapter teamsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        fabAddTeams = findViewById(R.id.fabAddTeams);
        teamDatabaseHelper = new TeamDatabaseHelper(this);
        rvListTeams = findViewById(R.id.rvListTeams);
        rvListTeams.setLayoutManager(new LinearLayoutManager(this));
        teamsAdapter = new TeamsAdapter(teamArrayList, this);
        rvListTeams.setAdapter(teamsAdapter);
        teamsAdapter.setOnTeamNameListener(new OnTeamNameListener() {
            @Override
            public void getTeamNames(String team1, String team2) {
                Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                i.putExtra("TEAM1", team1);
                i.putExtra("TEAM2", team2);
                startActivity(i);
            }
        });
        fabAddTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        prepareArrayList();

    }

    private void prepareArrayList() {
        Cursor cursor = teamDatabaseHelper.readData();
        while (cursor.moveToNext()) {
            String team1 = cursor.getString(cursor.getColumnIndex("TeamA"));
            String team2 = cursor.getString(cursor.getColumnIndex("TeamB"));
            teamArrayList.add(new Team(team1, team2));
        }
    }

    private void openDialog() {
        TeamDialog teamDialog = new TeamDialog();
        teamDialog.show(getSupportFragmentManager(), "TEAM_DIALOG");
    }

    @Override
    public void getTeamNames(String team1, String team2) {
        if(!TextUtils.isEmpty(team1) && !TextUtils.isEmpty(team2)) {
            boolean res = teamDatabaseHelper.writeData(team1, team2);
            if(res)
                Log.d(TAG, "Database write successful");
            else
                Log.d(TAG, "Database write unsuccessful");
            teamArrayList.add(new Team(team1, team2));
            teamsAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(this, "Kindly enter both the team names", Toast.LENGTH_SHORT).show();
        }
    }
}
