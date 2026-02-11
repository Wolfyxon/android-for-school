package com.example.wet;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    static class Specie {
        String name;
        int maxAge;

        Specie(String name, int maxAge) {
            this.name = name;
            this.maxAge = maxAge;
        }
    }


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

        Specie[] species = {
                new Specie("Pies", 18),
                new Specie("Kot", 20),
                new Specie("Świnka morska", 9)
        };
        List<String> specieNames = Arrays.stream(species)
                .map(v -> v.name)
                .collect(Collectors.toList());

        ListView speciesList = findViewById(R.id.speciesList);
        SeekBar seekAge = findViewById(R.id.seekAge);
        TextView lblAge = findViewById(R.id.lblAge);
        EditText inpName = findViewById(R.id.inpName);
        EditText inpTime = findViewById(R.id.inpTime);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.specie_entry, specieNames);
        speciesList.setAdapter(adapter);

        speciesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Specie s = species[i];
                seekAge.setMax(s.maxAge);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        seekAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                lblAge.setText("Ile ma lat? " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.btnSubmit).setOnClickListener(l -> {
            TextView selected = (TextView) speciesList.getSelectedItem();

            String[] components = {
                    inpName.getText().toString(),
                    selected != null ? selected.getText().toString() : "Nieznany",
                    String.valueOf(seekAge.getProgress()),
                    inpTime.getText().toString()
            };

            new AlertDialog.Builder(this)
                    .setTitle("Zapisana wizyta")
                    .setMessage(String.join(", ", components))
                    .setPositiveButton("Ok", null)
                    .show();
        });
    }
}