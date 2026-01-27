package com.example.kosci;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinearLayout diceList;
    TextView roundScoreLbl;
    TextView gameScoreLbl;
    int roundScore = 0;
    int gameScore = 0;

    static int[] DICE_IMGS = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
    };

    static int DICE_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        diceList = findViewById(R.id.dies);
        roundScoreLbl = findViewById(R.id.roundScore);
        gameScoreLbl = findViewById(R.id.gameScore);

        findViewById(R.id.btnReset).setOnClickListener(l -> {
            reset();
        });

        findViewById(R.id.btnThrow).setOnClickListener(l -> {

        });
    }

    void throwDies() {
        roundScore = 0;

        int[] scores = new int[DICE_COUNT];
        Random rng = new Random();

        for(int i = 0; i < scores.length; i++) {
            int n = rng.nextInt(1, DICE_IMGS.length + 1);

            roundScore += n;
            gameScore += n;

            scores[i] = n;
        }

        setDies(scores);
        updateScoreText();
    }

    void updateScoreText() {
        roundScoreLbl.setText("Wynik tego losowania: " + roundScore);
        gameScoreLbl.setText("Wynik gry: " + gameScore);
    }

    void reset() {
        setDiesUnknown();
        updateScoreText();
    }

    void setDies(int[] values) {
        diceList.removeAllViews();

        for(int n : values) {
            addDiceImg(DICE_IMGS[n - 1]);
        }
    }

    void setDiesUnknown() {
        diceList.removeAllViews();

        for(int i = 0; i < DICE_COUNT; i++) {
            addDiceImg(R.drawable.dice_unknown);
        }
    }

    void addDiceImg(int image) {
        int margin = 5;

        ImageView img = new ImageView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, ActionBar.LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin, margin, margin);

        img.setImageResource(image);
        img.setLayoutParams(lp);

        diceList.addView(img);
    }
}