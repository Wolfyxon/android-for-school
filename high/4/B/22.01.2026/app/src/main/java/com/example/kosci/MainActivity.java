package com.example.kosci;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout diceList;

    static int[] DICE_IMGS = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
    };

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
    }

    void setDies(int[] values) {
        diceList.removeAllViews();

        for(int n : values) {
            addDiceImg(DICE_IMGS[n - 1]);
        }
    }

    void addDiceImg(int image) {
        ImageView img = new ImageView(this);
        img.setImageResource(image);
        img.setLayoutParams(new LinearLayout.LayoutParams(80, ActionBar.LayoutParams.WRAP_CONTENT));
        diceList.addView(img);
    }
}