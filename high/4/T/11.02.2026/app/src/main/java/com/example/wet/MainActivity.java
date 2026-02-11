package com.example.wet;

import android.app.AlertDialog;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    static String[] SPECIES = {
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

        ListView speciesList = findViewById(R.id.speciesList);
        SeekBar seekAge = findViewById(R.id.seekAge);
        TextView lblAge = findViewById(R.id.lblAge);
        EditText inpName = findViewById(R.id.inpName);
        EditText inpTime = findViewById(R.id.inpTime);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.specie_entry, SPECIES);
        speciesList.setAdapter(adapter);

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