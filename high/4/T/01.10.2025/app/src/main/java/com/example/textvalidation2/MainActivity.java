package com.example.textvalidation2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @FunctionalInterface
    interface InputValidatorHandler {
        String validate(String text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        regInput(findViewById(R.id.nameInp), findViewById(R.id.nameErr), (text) -> {
            int len = text.length();

            for(int i = 0; i < len; i++) {
                char ch = text.charAt(i);
                boolean isUpper = Character.isUpperCase(ch);

                if(!Character.isAlphabetic(ch)) {
                    return "Imię może zawierać tylko litery";
                }

                if(i == 0 && !isUpper) {
                    return "Pierwsza litera musi być duża";
                }

                if(i != 0 && isUpper) {
                    return "Tylko pierwsza litera może być duża";
                }
            }

            if(!Character.isUpperCase(text.charAt(0))) {
                return "Pierwsza litera musi być duża";
            }

            return "";
        });

        regInput(findViewById(R.id.emailInp), findViewById(R.id.emailErr), (text) -> {
            boolean hasAt = false; // @
            int len = text.length();

            if(len == 0) {
                return "Adres e-mail nie może być pusty";
            }

            for(int i = 0; i < len; i++) {
                char ch = text.charAt(i);

                if(ch == '@') {
                    if(hasAt) {
                        return "Adres może zawierać tylko 1 '@'";
                    }

                    hasAt = true;
                }
            }

            if(!hasAt) {
                return "Adres musi zawierać @";
            }

            return "";
        });

        regInput(findViewById(R.id.passInp), findViewById(R.id.passErr), (text) -> {
            int len = text.length();

            if(len < 8) {
                return "Hasło musi zawierać conajmniej 8 znaków";
            }

            boolean hasLower = false;
            boolean hasUpper = false;

            for(int i = 0; i < len; i++) {
                char ch = text.charAt(i);

                if(Character.isUpperCase(ch)) {
                    hasUpper = true;
                }

                if(Character.isLowerCase(ch)) {
                    hasLower = true;
                }
            }

            if(!hasLower || !hasUpper) {
                return "Hasło musi zawierac duże i małe litery";
            }

            return "";
        });
    }

    boolean validate(EditText input, TextView errorLabel, InputValidatorHandler handler) {
        String error = handler.validate(input.getText().toString());
        errorLabel.setText(error);

        return error.isEmpty();
    }

    void regInput(EditText input, TextView errorLabel, InputValidatorHandler handler) {
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate(input, errorLabel, handler);
            }
        });
    }

}