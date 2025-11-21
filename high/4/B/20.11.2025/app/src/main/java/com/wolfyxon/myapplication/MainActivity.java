package com.wolfyxon.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static final String[] SPECIES = {
            "Pies",
            "Kot",
            "Świnka morska"
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

        HashMap<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Pies", 18);
        ageMap.put("Kot", 20);
        ageMap.put("Świnka morska", 9);

        String[] speciesArray = (String[]) ageMap.keySet().toArray();

        EditText nameInput = findViewById(R.id.nameInput);
        ListView speciesList = findViewById(R.id.speciesList);
        TextView ageLabel = findViewById(R.id.ageLabel);
        SeekBar ageSlider = findViewById(R.id.ageSlider);
        EditText reasonInput = findViewById(R.id.reasonInput);
        EditText timeInput = findViewById(R.id.timeInput);

        ArrayAdapter<String> speciesListAdapter = new ArrayAdapter<>(this, R.layout.list_item, speciesArray);
        speciesList.setAdapter(speciesListAdapter);

        speciesList.setOnItemClickListener((adapter, item, position, id) -> {
            TextView label = (TextView) item;
            int maxAge = ageMap.get(label.getText());

            ageSlider.setMax(maxAge);
        });

        ageSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageLabel.setText("Ile ma lat? " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.submitBtn).setOnClickListener(l -> {
            Toast t = new Toast(this);

            t.setText(String.format(
                    "%s, %s, %s, %s, %s",

                    nameInput.getText(),
                    ((TextView) speciesList.getSelectedItem()).getText(),
                    ageSlider.getProgress(),
                    reasonInput.getText(),
                    timeInput.getText()
            ));
        });
    }
}