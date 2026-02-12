package com.example.photos;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

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


    int currentPostIdx = 0;
    List<Post> posts = List.of(
            new Post(R.drawable.cat_glow, 3),
            new Post(R.drawable.catfish, 10),
            new Post(R.drawable.cat_boom, 12),
            new Post(R.drawable.cat_device, 2)
    );

    ImageView postImg;
    MaterialButton btnLike;

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

        postImg = findViewById(R.id.postImg);
        btnLike = findViewById(R.id.btnLike);

        findViewById(R.id.btnPrev).setOnClickListener(l -> {
            offsetPostIdx(-1);
        });

        findViewById(R.id.btnNext).setOnClickListener(l -> {
           offsetPostIdx(1);
        });

        btnLike.setOnClickListener(l -> {
            Post post = getCurrentPost();

            if(post == null) {
                return;
            }

            post.toggleLike();
            setPost(post);
        });

        setPostIdx(currentPostIdx);
    }

    void setPost(Post post) {
        postImg.setImageResource(post.getImageResId());
        btnLike.setText(String.valueOf(post.getLikes()));
    }

    Post getCurrentPost() {
        return posts.get(currentPostIdx);
    }

    void setPostIdx(int i) {
        if(i >= 0 && i < posts.size() - 1) {
            currentPostIdx = i;
            setPost(posts.get(i));
        }
    }

    void offsetPostIdx(int offset) {
        int i = currentPostIdx + offset;
        int len = posts.size();

        setPostIdx(((i % len) + len) % len);
    }
}