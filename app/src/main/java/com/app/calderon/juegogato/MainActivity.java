package com.app.calderon.juegogato;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private FloatingActionButton fabStart;

    private TextView txtTrun;

    private boolean box1Player1;
    private boolean box2Player1;
    private boolean box3Player1;
    private boolean box4Player1;
    private boolean box5Player1;
    private boolean box6Player1;
    private boolean box7Player1;
    private boolean box8Player1;
    private boolean box9Player1;

    private boolean box1Player2;
    private boolean box2Player2;
    private boolean box3Player2;
    private boolean box4Player2;
    private boolean box5Player2;
    private boolean box6Player2;
    private boolean box7Player2;
    private boolean box8Player2;
    private boolean box9Player2;

    private final int PLAYER_ONE = 1;
    private final int PLAYER_TWO = 2;
    private int TURN = PLAYER_ONE;

    private int counter = 0;

    private CoordinatorLayout clayout;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-2807565067627797~1779610712");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        sendToolbar();
        sendBind();
        sendOnClick();
        disabledButtons();
        cleanButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                write(button1);
                counter++;
                if (TURN == PLAYER_ONE)
                    box1Player1 = true;
                if (TURN == PLAYER_TWO)
                    box1Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button1.setEnabled(false);
                break;
            case R.id.btn2:
                write(button2);
                counter++;
                if (TURN == PLAYER_ONE)
                    box2Player1 = true;
                if (TURN == PLAYER_TWO)
                    box2Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button2.setEnabled(false);
                break;
            case R.id.btn3:
                write(button3);
                counter++;
                if (TURN == PLAYER_ONE)
                    box3Player1 = true;
                if (TURN == PLAYER_TWO)
                    box3Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button3.setEnabled(false);
                break;
            case R.id.btn4:
                write(button4);
                counter++;
                if (TURN == PLAYER_ONE)
                    box4Player1 = true;
                if (TURN == PLAYER_TWO)
                    box4Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button4.setEnabled(false);
                break;
            case R.id.btn5:
                write(button5);
                counter++;
                if (TURN == PLAYER_ONE)
                    box5Player1 = true;
                if (TURN == PLAYER_TWO)
                    box5Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button5.setEnabled(false);
                break;
            case R.id.btn6:
                write(button6);
                counter++;
                if (TURN == PLAYER_ONE)
                    box6Player1 = true;
                if (TURN == PLAYER_TWO)
                    box6Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button6.setEnabled(false);
                break;
            case R.id.btn7:
                write(button7);
                counter++;
                if (TURN == PLAYER_ONE)
                    box7Player1 = true;
                if (TURN == PLAYER_TWO)
                    box7Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button7.setEnabled(false);
                break;
            case R.id.btn8:
                write(button8);
                counter++;
                if (TURN == PLAYER_ONE)
                    box8Player1 = true;
                if (TURN == PLAYER_TWO)
                    box8Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button8.setEnabled(false);
                break;
            case R.id.btn9:
                write(button9);
                counter++;
                if (TURN == PLAYER_ONE)
                    box9Player1 = true;
                if (TURN == PLAYER_TWO)
                    box9Player2 = true;
                changeTurn();
                checkGame();
                tiedGame(counter);
                button9.setEnabled(false);
                break;
            case R.id.startAgain:
                startGameAgain();
                break;
        }
    }

    private void sendBind() {
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);
        button9 = (Button) findViewById(R.id.btn9);
        fabStart = (FloatingActionButton) findViewById(R.id.startAgain);
        clayout = (CoordinatorLayout) findViewById(R.id.clayout);
        txtTrun = findViewById(R.id.txtTurn);
    }

    private void sendOnClick() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        fabStart.setOnClickListener(this);
    }

    private void sendToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void write(Button button) {
        if (TURN == PLAYER_ONE) {
            writeCircle(button);
        } else if (TURN == PLAYER_TWO) {
            writeX(button);
        }
    }

    private void tiedGame(int number) {
        if (number == 9) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setSnackBbr(getString(R.string.tied_game), getColor(R.color.colorAccent));
            }
            disabledButtons();
        }

    }

    private void writeCircle(Button button) {
        button.setBackgroundResource(R.drawable.circle);
    }

    private void writeX(Button button) {
        button.setBackgroundResource(R.drawable.x);
    }

    private void changeTurn() {
        if (TURN == PLAYER_ONE){
            TURN = PLAYER_TWO;
            txtTrun.setText(getString(R.string.turn2));}
        else if (TURN == PLAYER_TWO){
            TURN = PLAYER_ONE;
            txtTrun.setText(getString(R.string.turn1));}
    }

    private void checkGame() {

        genericCheckGame(box1Player1, box2Player1, box3Player1, button1, button2, button3, PLAYER_ONE);
        genericCheckGame(box4Player1, box5Player1, box6Player1, button4, button5, button6, PLAYER_ONE);
        genericCheckGame(box7Player1, box8Player1, box9Player1, button7, button8, button9, PLAYER_ONE);
        genericCheckGame(box1Player1, box4Player1, box7Player1, button1, button4, button7, PLAYER_ONE);
        genericCheckGame(box2Player1, box5Player1, box8Player1, button2, button5, button8, PLAYER_ONE);
        genericCheckGame(box3Player1, box6Player1, box9Player1, button3, button6, button9, PLAYER_ONE);
        genericCheckGame(box1Player1, box5Player1, box9Player1, button1, button5, button9, PLAYER_ONE);
        genericCheckGame(box3Player1, box5Player1, box7Player1, button3, button5, button7, PLAYER_ONE);

        genericCheckGame(box1Player2, box2Player2, box3Player2, button1, button2, button3, PLAYER_TWO);
        genericCheckGame(box4Player2, box5Player2, box6Player2, button4, button5, button6, PLAYER_TWO);
        genericCheckGame(box7Player2, box8Player2, box9Player2, button7, button8, button9, PLAYER_TWO);
        genericCheckGame(box1Player2, box4Player2, box7Player2, button1, button4, button7, PLAYER_TWO);
        genericCheckGame(box2Player2, box5Player2, box8Player2, button2, button5, button8, PLAYER_TWO);
        genericCheckGame(box3Player2, box6Player2, box9Player2, button3, button6, button9, PLAYER_TWO);
        genericCheckGame(box1Player2, box5Player2, box9Player2, button1, button5, button9, PLAYER_TWO);
        genericCheckGame(box3Player2, box5Player2, box7Player2, button3, button5, button7, PLAYER_TWO);

    }

    private void startGameAgain() {
        enabledButtons();
        cleanButtons();
        cleanBox();
        TURN = PLAYER_ONE;
        counter = 0;
        fabStart.setImageResource(R.drawable.refresh);
        txtTrun.setText(getString(R.string.turn1));

    }

    private void cleanButtons() {
        button1.setBackgroundColor(Color.WHITE);
        button2.setBackgroundColor(Color.WHITE);
        button3.setBackgroundColor(Color.WHITE);
        button4.setBackgroundColor(Color.WHITE);
        button5.setBackgroundColor(Color.WHITE);
        button6.setBackgroundColor(Color.WHITE);
        button7.setBackgroundColor(Color.WHITE);
        button8.setBackgroundColor(Color.WHITE);
        button9.setBackgroundColor(Color.WHITE);
    }

    private void cleanBox() {
        box1Player1 = false;
        box2Player1 = false;
        box3Player1 = false;
        box4Player1 = false;
        box5Player1 = false;
        box6Player1 = false;
        box7Player1 = false;
        box8Player1 = false;
        box9Player1 = false;
        box1Player2 = false;
        box2Player2 = false;
        box3Player2 = false;
        box4Player2 = false;
        box5Player2 = false;
        box6Player2 = false;
        box7Player2 = false;
        box8Player2 = false;
        box9Player2 = false;
    }

    private void enabledButtons() {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
    }

    private void disabledButtons() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
    }

    private void genericCheckGame(boolean boolean1, boolean boolean2, boolean boolean3,
                                  Button btn1, Button btn2, Button btn3,
                                  int player) {
        if (boolean1 && boolean2 && boolean3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (player == PLAYER_ONE) {
                    setSnackBbr(getString(R.string.p_one_won), getColor(R.color.circleWon));
                    drawLinesO(btn1, btn2, btn3);
                }
                if (player == PLAYER_TWO) {
                    setSnackBbr(getString(R.string.p_two_won), getColor(R.color.exWon));
                    drawLinesX(btn1, btn2, btn3);
                }
            }
            counter = 0;
            disabledButtons();
            txtTrun.setText(getString(R.string.finish));
        }
    }

    private void drawLinesX(Button button_1, Button button_2, Button button_3) {
        button_1.setBackgroundResource(R.drawable.x_won);
        button_2.setBackgroundResource(R.drawable.x_won);
        button_3.setBackgroundResource(R.drawable.x_won);
    }

    private void drawLinesO(Button button_1, Button button_2, Button button_3) {
        button_1.setBackgroundResource(R.drawable.circle_won);
        button_2.setBackgroundResource(R.drawable.circle_won);
        button_3.setBackgroundResource(R.drawable.circle_won);
    }

    private void setSnackBbr(String message, int color) {
        Snackbar snack = Snackbar.make(clayout, message, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            tv.setTextColor(color);
        }
        snack.show();
    }
}
