package com.app.calderon.juegogato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private Toolbar toolbar;

    private SharedPreferences pref;
    private int winPlayer1 = 0;
    private int winPlayer2 = 0;
    private int tiedGames  = 0;

    private TextView txtP1 ;
    private TextView txtP2;
    private TextView tied ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        setSupportActionBar(toolbar);

        pref = getSharedPreferences("counters", Context.MODE_PRIVATE);

        sendBind();
        setToolbar();
        try{
            winPlayer1 =  getCounterP1Saved();
            winPlayer2 =  getCounterP2Saved();
            tiedGames = getCounterTiedSaved();
        }catch(NullPointerException e){
            winPlayer1 =  0;
            winPlayer2 =  0;
            tiedGames = 0;
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        sendTxt();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_activity_statistics));
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void sendBind(){
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fabRefresh);
        txtP1 = findViewById(R.id.txtP1);
        txtP2 = findViewById(R.id.txtP2);
        tied = findViewById(R.id.txtTied);
        fab.setOnClickListener(this);

        txtP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(fab,"Este es un ejemplo",Snackbar.LENGTH_SHORT).show();
                    }
                },3000);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    private  void sendTxt(){
        txtP1.setText(String.format(Locale.getDefault(), "%d", winPlayer1));
        txtP2.setText(String.format(Locale.getDefault(), "%d", winPlayer2));
        tied.setText(String.format(Locale.getDefault(), "%d", tiedGames));
    }

    private void saveCounterP1() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("countP1", winPlayer1);
        editor.apply();
    }

    private void saveCounterP2() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("countP2", winPlayer2);
        editor.apply();
    }

    private void saveCounterTied() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("tied", tiedGames);
        editor.apply();
    }

    private int getCounterP1Saved() {
        return pref.getInt("countP1", winPlayer1);
    }

    private int getCounterP2Saved() {
        return pref.getInt("countP2", winPlayer2);
    }

    private int getCounterTiedSaved() {
        return pref.getInt("tied", tiedGames);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fabRefresh:
                refreshCounters();
                break;
        }
    }

    private void refreshCounters() {
        winPlayer1 = 0;
        winPlayer2 = 0;
        tiedGames  = 0;
        saveCounterP1();
        saveCounterP2();
        saveCounterTied();
        sendTxt();
    }
}
