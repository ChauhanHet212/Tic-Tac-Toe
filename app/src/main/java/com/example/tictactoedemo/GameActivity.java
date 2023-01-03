package com.example.tictactoedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int i, id, cnt = 0;
    TextView playerstate, p1, p2;
    ImageView backbtn;
    TextView[] txtv = new TextView[9];
    AppCompatButton restartbtn;
    int actplay = 1;
    ArrayList list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        i = intent.getIntExtra("btn", 0);

        backbtn = findViewById(R.id.backbtn);
        restartbtn = findViewById(R.id.restartbtn);
        playerstate = findViewById(R.id.playerstate);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);

        for (int h = 0; h < 9; h++) {
            id = getResources().getIdentifier("txtv" + h, "id", getPackageName());
            txtv[h] = findViewById(id);
            txtv[h].setOnClickListener(this);
        }

        playerstate.setTextColor(this.getResources().getColor(R.color.red));
        restartbtn.setVisibility(View.INVISIBLE);

        if (i == 2) {
            playerstate.setText("X's Turn");
            p1.setText("Player 1 = ");
            p2.setText("Player 2 = ");
        } else if (i == 1) {
            p1.setText("Player = ");
            p2.setText("CPU = ");
        }

        restartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartbtn.setVisibility(View.INVISIBLE);
                for (int h = 0; h < 9; h++) {
                    txtv[h].setText("");
                    txtv[h].setTextColor(getResources().getColor(R.color.black));
                    txtv[h].setClickable(true);
                }
                if (i == 2) {
                    playerstate.setText("X's Turn");
                    playerstate.setTextColor(getResources().getColor(R.color.red));
                } else if (i == 1){
                    list.clear();
                    cnt = 0;
                    playerstate.setText("");
                }
                actplay = 1;
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (i == 1) {
            for (int h = 0; h < 9; h++) {
                win();
                if (txtv[h].getId() == view.getId()) {
                    txtv[h].setText("X");
                    txtv[h].setClickable(false);
                    list.add(h);
                    cnt++;
                    if (win() == 0 && cnt <= 4) {
                        while (true) {
                            int max = 9, min = 0;
                            int r = new Random().nextInt(max - min) + min;
                            if (!list.contains(r)) {
                                txtv[r].setText("O");
                                txtv[r].setClickable(false);
                                list.add(r);
                                break;
                            }
                        }
                    }
                }
                win();
            }
        } else if (i == 2) {
            for (int h = 0; h < 9; h++) {
                if (txtv[h].getId() == view.getId()) {
                    if (actplay == 1) {
                        txtv[h].setText("X");
                        txtv[h].setClickable(false);
                        playerstate.setText("O's Turn");
                        playerstate.setTextColor(getResources().getColor(R.color.blue));
                        actplay = 2;
                    } else {
                        txtv[h].setText("O");
                        txtv[h].setClickable(false);
                        playerstate.setText("X's Turn");
                        playerstate.setTextColor(getResources().getColor(R.color.red));
                        actplay = 1;
                    }
                    win();
                }
            }
        }
    }

    public int win() {
        if (!txtv[0].getText().equals("") && txtv[0].getText().equals(txtv[1].getText()) && txtv[1].getText().equals(txtv[2].getText())) {
            winner(txtv[0].getText().toString(), 0, 1, 2);
            disable();
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[3].getText().equals("") && txtv[3].getText().equals(txtv[4].getText()) && txtv[4].getText().equals(txtv[5].getText())) {
            disable();
            winner(txtv[3].getText().toString(), 3, 4, 5);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[6].getText().equals("") && txtv[6].getText().equals(txtv[7].getText()) && txtv[7].getText().equals(txtv[8].getText())) {
            disable();
            winner(txtv[6].getText().toString(), 6, 7, 8);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[0].getText().equals("") && txtv[0].getText().equals(txtv[3].getText()) && txtv[3].getText().equals(txtv[6].getText())) {
            disable();
            winner(txtv[0].getText().toString(), 0, 3, 6);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[1].getText().equals("") && txtv[1].getText().equals(txtv[4].getText()) && txtv[4].getText().equals(txtv[7].getText())) {
            disable();
            winner(txtv[1].getText().toString(), 1, 4, 7);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[2].getText().equals("") && txtv[2].getText().equals(txtv[5].getText()) && txtv[5].getText().equals(txtv[8].getText())) {
            disable();
            winner(txtv[2].getText().toString(), 2, 5, 8);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[0].getText().equals("") && txtv[0].getText().equals(txtv[4].getText()) && txtv[4].getText().equals(txtv[8].getText())) {
            disable();
            winner(txtv[0].getText().toString(), 0, 4, 8);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[2].getText().equals("") && txtv[2].getText().equals(txtv[4].getText()) && txtv[4].getText().equals(txtv[6].getText())) {
            disable();
            winner(txtv[2].getText().toString(), 2, 4, 6);
            restartbtn.setVisibility(View.VISIBLE);
            return 1;
        } else if (!txtv[0].getText().equals("") && !txtv[1].getText().equals("") && !txtv[2].getText().equals("") && !txtv[3].getText().equals("")
                && !txtv[4].getText().equals("") && !txtv[5].getText().equals("") && !txtv[6].getText().equals("") && !txtv[7].getText().equals("")
                && !txtv[8].getText().equals("")) {
            restartbtn.setVisibility(View.VISIBLE);
            playerstate.setText("Game Draw");
            playerstate.setTextColor(getResources().getColor(R.color.green));
            return 2;
        } else {
            return 0;
        }
    }

    public void winner(String n, int v1, int v2, int v3) {
        if (i == 2) {
            if (n == "X") {
                playerstate.setText("X is Winner");
                playerstate.setTextColor(getResources().getColor(R.color.red));
                txtv[v1].setTextColor(getResources().getColor(R.color.red));
                txtv[v2].setTextColor(getResources().getColor(R.color.red));
                txtv[v3].setTextColor(getResources().getColor(R.color.red));
            } else {
                playerstate.setText("O is Winner");
                playerstate.setTextColor(getResources().getColor(R.color.blue));
                txtv[v1].setTextColor(getResources().getColor(R.color.blue));
                txtv[v2].setTextColor(getResources().getColor(R.color.blue));
                txtv[v3].setTextColor(getResources().getColor(R.color.blue));
            }
        } else if (i == 1) {
            if (n == "X") {
                playerstate.setText("Player is Winner");
                playerstate.setTextColor(getResources().getColor(R.color.red));
                txtv[v1].setTextColor(getResources().getColor(R.color.red));
                txtv[v2].setTextColor(getResources().getColor(R.color.red));
                txtv[v3].setTextColor(getResources().getColor(R.color.red));
            } else {
                playerstate.setText("CPU is Winner");
                playerstate.setTextColor(getResources().getColor(R.color.blue));
                txtv[v1].setTextColor(getResources().getColor(R.color.blue));
                txtv[v2].setTextColor(getResources().getColor(R.color.blue));
                txtv[v3].setTextColor(getResources().getColor(R.color.blue));
            }
        }
    }

    public void disable() {
        for (int h = 0; h < 9; h++) {
            txtv[h].setClickable(false);
        }
    }
}