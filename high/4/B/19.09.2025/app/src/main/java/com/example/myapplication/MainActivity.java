package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

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

        initWashing();
        initCleaner();
    }

    void initWashing() {
        EditText washingInput = findViewById(R.id.washingInp);
        TextView washingStatus = findViewById(R.id.washingStatus);

        findViewById(R.id.btnConfirmWash).setOnClickListener((l) -> {
            try {
                int program = Integer.parseInt(washingInput.getText().toString());

                if(program >= 1 && program <= 12) {
                    washingStatus.setText("Numer prania: " + String.valueOf(program));
                }

            } catch (NumberFormatException e) {

            }
        });
    }

    void initCleaner() {
        Button btn = findViewById(R.id.btnTurnCleaner);
        TextView powerStatus = findViewById(R.id.cleanerPower);

        AtomicBoolean powered = new AtomicBoolean(false);

        btn.setOnClickListener((l) -> {
            boolean bl = powered.get();
            powered.set(!bl);

            if(bl) {
                powerStatus.setText("Odkurzacz: włączony");
                btn.setText("Wyłącz");
            } else {
                powerStatus.setText("Odkurzacz: wyłączony");
                btn.setText("Włącz");
            }
        });
    }
}