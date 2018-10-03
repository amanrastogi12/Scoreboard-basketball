package com.example.namankhanna.basketballscoreboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PlayerActivity extends AppCompatActivity {

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        myToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
    }
}
