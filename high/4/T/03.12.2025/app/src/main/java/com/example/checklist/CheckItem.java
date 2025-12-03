package com.example.checklist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CheckItem extends LinearLayout {
    CheckBox checkBox;
    Button deleteBtn;

    public CheckItem(Context context) {
        super(context);
        setup(context);
    }

    public CheckItem(Context context, String text) {
        super(context);
        setup(context);

        checkBox.setText(text);
    }

    public CheckItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    void setup(Context context) {
        inflate(context, R.layout.layout_checkitem, this);

        checkBox = findViewById(R.id.checkBox);
        deleteBtn = findViewById(R.id.checkDelete);

        checkBox.setOnClickListener(l -> update());
        deleteBtn.setOnClickListener(l -> remove());

        update();
    }

    void remove() {
        ((ViewManager) getParent()).removeView(this);
    }

    void update() {
        if(checkBox.isChecked()) {
            deleteBtn.setVisibility(VISIBLE);
        } else {
            deleteBtn.setVisibility(GONE);
        }
    }
}
