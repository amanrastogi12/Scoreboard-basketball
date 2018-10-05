package com.example.namankhanna.basketballscoreboard;

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

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Toolbar myToolbar;
    TextView tvTeamName1, tvTeamName2;
    EditText etTeam1, etTeam2, etTNum1, etTNum2;
    FloatingActionButton fabAdd1, fabAdd2;
    RecyclerView rvTeam1, rvTeam2;
    Button btnLetsPlay;
    ArrayList<Players> players1 = new ArrayList<>();
    ArrayList<Players> players2 = new ArrayList<>();
    PlayerAdapter adapter1;
    PlayerAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        myToolbar = findViewById(R.id.myToolbar2);
        setSupportActionBar(myToolbar);

        tvTeamName1 = findViewById(R.id.tvTeamName1);
        tvTeamName2 = findViewById(R.id.tvTeamName2);
        etTeam1 = findViewById(R.id.etTeam1Player);
        etTeam2 = findViewById(R.id.etTeam2Player);
        etTNum1 = findViewById(R.id.etTeam1TNum);
        etTNum2 = findViewById(R.id.etTeam2TNum);
        fabAdd1 = findViewById(R.id.fabAddPlayer1);
        fabAdd2 = findViewById(R.id.fabAddPlayer2);
        rvTeam1 = findViewById(R.id.rvTeam1);
        rvTeam2 = findViewById(R.id.rvTeam2);
        btnLetsPlay = findViewById(R.id.btnLetsPlay);

        String name1 = getIntent().getStringExtra("TEAM1");
        String name2 = getIntent().getStringExtra("TEAM2");
        if(name1 != null && name2 != null) {
            tvTeamName1.setText(name1);
            tvTeamName2.setText(name2);
        }

        rvTeam1.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new PlayerAdapter(players1, this);
        rvTeam1.setAdapter(adapter1);

        rvTeam2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new PlayerAdapter(players2, this);
        rvTeam2.setAdapter(adapter2);

        fabAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etTeam1.getText().toString()) && !TextUtils.isEmpty(etTNum1.getText().toString())) {
                    addPlayersInTeam1();
                }
            }
        });

        fabAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etTeam1.getText().toString()) && !TextUtils.isEmpty(etTNum1.getText().toString())) {
                    addPlayersInTeam2();
                }
            }
        });

        btnLetsPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayerActivity.this, "Its Play Time !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addPlayersInTeam1() {
        if(players1.size() == 10 && players2.size() == 10) {
            btnLetsPlay.setVisibility(View.VISIBLE);
        }
        String name = etTeam1.getText().toString();
        int tNum = Integer.valueOf(etTNum1.getText().toString());
        if(players1.size() < 10) {
            players1.add(new Players(name, tNum));
            adapter1.updatePlayers(players1);
//            etTeam1.setText("");
//            etTNum1.setText("");
        }
        else {
            Toast.makeText(this, "Cannot add more players", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPlayersInTeam2() {
        if(players1.size() == 10 && players2.size() == 10) {
            btnLetsPlay.setVisibility(View.VISIBLE);
        }
        String name = etTeam2.getText().toString();
        int tNum = Integer.valueOf(etTNum2.getText().toString());
        if(players1.size() == 10 && players2.size() == 10) {
            btnLetsPlay.setVisibility(View.VISIBLE);
        }
        if(players2.size() < 10) {
            players2.add(new Players(name, tNum));
            adapter2.updatePlayers(players2);
//            etTeam2.setText("");
//            etTNum2.setText("");
        }
        else {
            Toast.makeText(this, "Cannot add more players", Toast.LENGTH_SHORT).show();
        }
    }
}
