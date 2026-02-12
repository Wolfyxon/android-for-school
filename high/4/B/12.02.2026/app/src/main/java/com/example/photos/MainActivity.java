package com.example.photos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static class Post {
        int imageResId;
        int likes = 0;
        boolean userLiked = false;

        Post(int imageResId, int initialLikes) {
            this.imageResId = imageResId;
            this.likes = initialLikes;
        }

        int getLikes() {
            return likes;
        }

        boolean isLiked() {
            return userLiked;
        }

        int getImageResId() {
            return imageResId;
        }

        void toggleLike() {
            if(userLiked) {
                userLiked = false;
                likes -= 1;
            } else {
                userLiked = true;
                likes += 1;
            }
        }
    }

    List<Post> posts = List.of(
            new Post(R.drawable.cat_glow, 3),
            new Post(R.drawable.catfish, 10),
            new Post(R.drawable.cat_boom, 12),
            new Post(R.drawable.cat_device, 2)
    );

    int currentPostIdx = 0;

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
    }


}