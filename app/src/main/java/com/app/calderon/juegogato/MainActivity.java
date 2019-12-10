package com.app.calderon.juegogato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import static com.app.calderon.juegogato.Util.PLAYER_ONE_VS_COMPUTER;
import static com.app.calderon.juegogato.Util.PLAYER_ONE_VS_PLAYER_TWO;
import static com.app.calderon.juegogato.Util.getCounterP1Saved;
import static com.app.calderon.juegogato.Util.getCounterP2Saved;
import static com.app.calderon.juegogato.Util.getCounterTiedSaved;
import static com.app.calderon.juegogato.Util.getSettingsPlayer;
import static com.app.calderon.juegogato.Util.saveCounterP1;
import static com.app.calderon.juegogato.Util.saveCounterP2;
import static com.app.calderon.juegogato.Util.saveCounterTied;

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

    private TextView txtTurn;

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

    private boolean computerPlays = false;
    private boolean autoclic = false;
    private boolean finish = false;

    private final int PLAYER_ONE = 1;
    private final int PLAYER_TWO = 2;
    private final int COMPUTER = 3;
    private int TURN = PLAYER_ONE;

    String s = Build.MODEL;

    private final int button_one = R.id.btn1;
    private final int button_two = R.id.btn2;
    private final int button_tree = R.id.btn3;
    private final int button_for = R.id.btn4;
    private final int button_five = R.id.btn5;
    private final int button_six = R.id.btn6;
    private final int button_seven = R.id.btn7;
    private final int button_eight = R.id.btn8;
    private final int button_nine = R.id.btn9;

    private int counter = 0;

    private SharedPreferences prefCounters;
    private SharedPreferences prefsSettings;
    private int winPlayer1 = 0;
    private int winPlayer2 = 0;
    private int tiedGames = 0;

    private ArrayList<Integer> numbers = new ArrayList<>();

    private CoordinatorLayout clayout;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-2807565067627797~1779610712");

        getPreferences();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (getSettingsPlayer(prefsSettings) == PLAYER_ONE_VS_COMPUTER) {
            computerPlays = true;
            TURN = PLAYER_ONE;
        }

        getCounters();
        putPlayerSettings();

        sendToolbar();
        sendBind();
        sendOnClick();

        disabledButtons();
        cleanButtons();
    }

    private void getPreferences() {
        prefCounters = getSharedPreferences("counters", Context.MODE_PRIVATE);
        prefsSettings = getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    private void getCounters() {
        try {
            winPlayer1 = getCounterP1Saved(prefCounters, winPlayer1);
            winPlayer2 = getCounterP2Saved(prefCounters, winPlayer2);
            tiedGames = getCounterTiedSaved(prefCounters, tiedGames);
        } catch (NullPointerException e) {
            winPlayer1 = 0;
            winPlayer2 = 0;
            tiedGames = 0;
        }
    }

    private void putPlayerSettings() {
        int i = getSettingsPlayer(prefsSettings);
        if (i == PLAYER_ONE_VS_PLAYER_TWO)
            Toast.makeText(this, "1 vs 2", Toast.LENGTH_SHORT).show();
        if (i == PLAYER_ONE_VS_COMPUTER)
            Toast.makeText(this, "1 vs " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_statistics) {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
            return true;
        }


        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean available(int j) {
        boolean flag = true;
        for (int i = 0; i < numbers.size(); i++) {
            if (j == numbers.get(i)) {
                flag = false;
            }
        }
        return flag;
    }

    private void turnOfComputer() {
        final int i = getNumber();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickButton(getButton(i),i);
            }
        }, 1000);
    }

    private void lockButtons() {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 1) button1.setEnabled(false);
            if (numbers.get(i) == 2) button2.setEnabled(false);
            if (numbers.get(i) == 3) button3.setEnabled(false);
            if (numbers.get(i) == 4) button4.setEnabled(false);
            if (numbers.get(i) == 5) button5.setEnabled(false);
            if (numbers.get(i) == 6) button6.setEnabled(false);
            if (numbers.get(i) == 7) button7.setEnabled(false);
            if (numbers.get(i) == 8) button8.setEnabled(false);
            if (numbers.get(i) == 9) button9.setEnabled(false);
        }
    }

    private void clickButton(Button button, int number) {
        write(button);
        setStatusBoxes(number);
        counter++;
        changeTurn();
        checkGame();
        numbers.add(number);
        button.setEnabled(false);
        tiedGame(counter);
    }

    private void setStatusBoxes(int number) {
        switch (number) {
            case 1:
                if (TURN == PLAYER_ONE) {
                    box1Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box1Player2 = true;
                }
                break;
            case 2:
                if (TURN == PLAYER_ONE) {
                    box2Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box2Player2 = true;
                }
                break;
            case 3:
                if (TURN == PLAYER_ONE) {
                    box3Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box3Player2 = true;
                }
                break;
            case 4:
                if (TURN == PLAYER_ONE) {
                    box4Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box4Player2 = true;
                }
                break;
            case 5:
                if (TURN == PLAYER_ONE) {
                    box5Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box5Player2 = true;
                }
                break;
            case 6:
                if (TURN == PLAYER_ONE) {
                    box6Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box6Player2 = true;
                }
                break;
            case 7:
                if (TURN == PLAYER_ONE) {
                    box7Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box7Player2 = true;
                }
                break;
            case 8:
                if (TURN == PLAYER_ONE) {
                    box8Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box8Player2 = true;
                }
                break;
            case 9:
                if (TURN == PLAYER_ONE) {
                    box9Player1 = true;
                } else if (TURN == PLAYER_TWO) {
                    box9Player2 = true;
                }
                break;
        }
    }

    private int getNumber() {
        int i = 0;
        boolean flag = false;


        while (!flag) {
            i = ((int) (Math.random() * 9) + 1);
            if (available(i)) {
                numbers.add(i);
                flag = true;
            }
        }
        return i;
    }

    private Button getButton(int i) {
        if (i == 1) return button1;
        if (i == 2) return button2;
        if (i == 3) return button3;
        if (i == 4) return button4;
        if (i == 5) return button5;
        if (i == 6) return button6;
        if (i == 7) return button7;
        if (i == 8) return button8;
        if (i == 9) return button9;
        else return null;
    }

    @Override
    public void onClick(View v) {
        if (computerPlays) {
            switch (v.getId()) {
                case button_one:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button1, 1);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_two:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button2, 2);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_tree:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button3, 3);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_for:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button4, 4);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_five:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button5,5);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_six:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button6, 6);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_seven:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button7, 7);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_eight:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button8, 8);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case button_nine:
                    if (TURN == PLAYER_ONE) {
                        clickButton(button9, 9);
                        lockButtons();
                        if (!finish) {
                            turnOfComputer();
                        }
                    }
                    break;
                case R.id.startAgain:
                    startGameAgain();
                    break;
            }
        } else {
            switch (v.getId()) {
                case button_one:
                    clickButton(button1, 1);
                    break;
                case button_two:
                    clickButton(button2, 2);
                    break;
                case button_tree:
                    clickButton(button3, 3);
                    break;
                case button_for:
                    clickButton(button4, 4);
                    break;
                case button_five:
                    clickButton(button5, 5);
                    break;
                case button_six:
                    clickButton(button6, 6);
                    break;
                case button_seven:
                    clickButton(button7, 7);
                    break;
                case button_eight:
                    clickButton(button8, 8);
                    break;
                case button_nine:
                    clickButton(button9, 9);
                    break;
                case R.id.startAgain:
                    startGameAgain();
                    break;
            }
        }
    }

    private void sendBind() {
        button1  = findViewById(R.id.btn1);
        button2  = findViewById(R.id.btn2);
        button3  = findViewById(R.id.btn3);
        button4  = findViewById(R.id.btn4);
        button5  = findViewById(R.id.btn5);
        button6  = findViewById(R.id.btn6);
        button7  = findViewById(R.id.btn7);
        button8  = findViewById(R.id.btn8);
        button9  = findViewById(R.id.btn9);
        fabStart = findViewById(R.id.startAgain);
        clayout  = findViewById(R.id.clayout);
        txtTurn  = findViewById(R.id.txtTurn);
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
            if (!finish) {
                tiedGames++;
            }
            saveCounterTied(prefCounters, tiedGames);
        }
    }

    private void writeCircle(Button button) {
        button.setBackgroundResource(R.drawable.circle);
    }

    private void writeX(Button button) {
        button.setBackgroundResource(R.drawable.x);
    }

    private void changeTurn() {
        if (TURN == PLAYER_ONE) {
            TURN = PLAYER_TWO;
            if(!computerPlays)
                txtTurn.setText(getString(R.string.turn2));
            else
                txtTurn.setText(getString(R.string.turns)+" "+s);
        } else if (TURN == PLAYER_TWO) {
            TURN = PLAYER_ONE;
            txtTurn.setText(getString(R.string.turn1));
        }
    }

    private void checkGame() {

        if (genericCheckGame(box1Player1, box2Player1, box3Player1, button1, button2, button3, PLAYER_ONE)) {
        }
        if (genericCheckGame(box4Player1, box5Player1, box6Player1, button4, button5, button6, PLAYER_ONE)) {
        }
        if (genericCheckGame(box7Player1, box8Player1, box9Player1, button7, button8, button9, PLAYER_ONE)) {
        }
        if (genericCheckGame(box1Player1, box4Player1, box7Player1, button1, button4, button7, PLAYER_ONE)) {
        }
        if (genericCheckGame(box2Player1, box5Player1, box8Player1, button2, button5, button8, PLAYER_ONE)) {
        }
        if (genericCheckGame(box3Player1, box6Player1, box9Player1, button3, button6, button9, PLAYER_ONE)) {
        }
        if (genericCheckGame(box1Player1, box5Player1, box9Player1, button1, button5, button9, PLAYER_ONE)) {
        }
        if (genericCheckGame(box3Player1, box5Player1, box7Player1, button3, button5, button7, PLAYER_ONE)) {
        }


        if (genericCheckGame(box1Player2, box2Player2, box3Player2, button1, button2, button3, PLAYER_TWO)) {
        }
        if (genericCheckGame(box4Player2, box5Player2, box6Player2, button4, button5, button6, PLAYER_TWO)) {
        }
        if (genericCheckGame(box7Player2, box8Player2, box9Player2, button7, button8, button9, PLAYER_TWO)) {
        }
        if (genericCheckGame(box1Player2, box4Player2, box7Player2, button1, button4, button7, PLAYER_TWO)) {
        }
        if (genericCheckGame(box2Player2, box5Player2, box8Player2, button2, button5, button8, PLAYER_TWO)) {
        }
        if (genericCheckGame(box3Player2, box6Player2, box9Player2, button3, button6, button9, PLAYER_TWO)) {
        }
        if (genericCheckGame(box1Player2, box5Player2, box9Player2, button1, button5, button9, PLAYER_TWO)) {
        }
        if (genericCheckGame(box3Player2, box5Player2, box7Player2, button3, button5, button7, PLAYER_TWO)) {
        }

    }

    private void startGameAgain() {
        finish = false;
        numbers.clear();
        enabledButtons();
        cleanButtons();
        cleanBox();
        TURN = PLAYER_ONE;
        counter = 0;
        fabStart.setImageResource(R.drawable.refresh);
        txtTurn.setText(getString(R.string.turn1));

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

    private boolean genericCheckGame(boolean boolean1, boolean boolean2, boolean boolean3,
                                     Button btn1, Button btn2, Button btn3,
                                     int player) {
        if (boolean1 && boolean2 && boolean3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (player == PLAYER_ONE) {
                    setSnackBbr(getString(R.string.p_one_won), getColor(R.color.circleWon));
                    drawLinesO(btn1, btn2, btn3);
                    if (!finish) {
                        winPlayer1++;
                    }
                    finish = true;
                    saveCounterP1(prefCounters, winPlayer1);
                }
                if (player == PLAYER_TWO) {
                    if(computerPlays)
                        setSnackBbr((getString(R.string.won))+" "+s, getColor(R.color.exWon));
                    else
                        setSnackBbr(getString(R.string.p_two_won), getColor(R.color.exWon));
                    drawLinesX(btn1, btn2, btn3);
                    if (!finish) {
                        winPlayer2++;
                    }
                    finish = true;
                    saveCounterP2(prefCounters, winPlayer2);
                }
            }
            counter = 0;
            disabledButtons();
            txtTurn.setText(getString(R.string.finish));
            return true;
        } else {
            return false;
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
