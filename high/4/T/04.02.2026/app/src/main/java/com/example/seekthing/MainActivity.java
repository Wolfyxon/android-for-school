package com.example.seekthing;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView label;
    SeekBar sizeSlider;
    RadioGroup radioGroup;

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

        radioGroup = findViewById(R.id.radio_group);
        input = findViewById(R.id.input);
        label = findViewById(R.id.label);
        sizeSlider = findViewById(R.id.size_slider);

        addColor("Czarny", Color.BLACK);
        addColor("Niebieski", Color.BLUE);
        addColor("Czerwony", Color.RED);
        addColor("Zielony", Color.GREEN);

        findViewById(R.id.btn_apply).setOnClickListener(l -> {
            label.setText(input.getText());
        });

        sizeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                label.setTextSize(sizeSlider.getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    void addColor(String name, int value) {
        RadioButton radio = new RadioButton(this);

        radio.setText(name);
        radio.setTextColor(value);

        radio.setOnClickListener(l -> {
            label.setTextColor(value);
        });

        radioGroup.addView(radio);
    }
}