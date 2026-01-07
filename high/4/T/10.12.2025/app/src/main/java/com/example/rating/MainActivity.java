package com.example.rating;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView ratingText;
    TextView clicksText;

    int clicks = 0;

    void updateRating() {
        ratingText.setText("Twoja ocena: " + ratingBar.getRating());
    }
    void updateClicks() {
        clicksText.setText("Kliknięcia: " + clicks);
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

        ratingBar = findViewById(R.id.rating);
        ratingText = findViewById(R.id.ratingText);
        clicksText = findViewById(R.id.clicks);

        ratingBar.setOnRatingBarChangeListener((bar, value, fromUser) -> {
            updateRating();
        });

        findViewById(R.id.btnLike).setOnClickListener(l -> {
            float rating = ratingBar.getRating();

            clicks++;
            updateClicks();

            if(rating < 5) {
                rating += 0.5F;

                ratingBar.setRating(rating);
            } else {
                Toast.makeText(this, "Maksymalna ocena", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(l -> {
            clicks = 0;
            ratingBar.setRating(0);
            updateClicks();
            updateRating();
        });
    }
}