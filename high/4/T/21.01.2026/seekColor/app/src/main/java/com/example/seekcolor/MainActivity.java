package com.example.seekcolor;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView colorLabel;
    SeekBar seekR;
    SeekBar seekG;
    SeekBar seekB;

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

        colorLabel = findViewById(R.id.colorLabel);

        seekR = setupBar(findViewById(R.id.seekR));
        seekG = setupBar(findViewById(R.id.seekG));
        seekB = setupBar(findViewById(R.id.seekB));

        update();
    }

    void update() {
        colorLabel.setBackgroundColor(Color.rgb(seekR.getProgress(), seekG.getProgress(), seekB.getProgress()));
    }

    SeekBar setupBar(SeekBar bar) {
        bar.setMax(255);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        return bar;
    }
}