package com.example.textvalidation2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @FunctionalInterface
    interface InputValidatorHandler {
        String validate(String text);
    }

    HashMap<EditText, Boolean> validStates = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        regInput(findViewById(R.id.nameInp), findViewById(R.id.nameErr), (text) -> {
            int len = text.length();

            if(len == 0) {
                return "Imię nie może być puste";
            }

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

            boolean wasDot = false;

            for(int i = 0; i < len; i++) {
                char ch = text.charAt(i);
                boolean isDot = ch == '.';

                if(isDot && wasDot) {
                    return "'.' nie moga się powtarzać";
                }

                if(isDot && (i == 0 || i == len - i )) {
                    return "'.' nie może być na końcu ani początku";
                }

                if(ch == '@') {
                    if(hasAt) {
                        return "Adres może zawierać tylko 1 '@'";
                    }

                    hasAt = true;
                }

                wasDot = isDot;
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
            boolean hasDigit = false;

            for(int i = 0; i < len; i++) {
                char ch = text.charAt(i);

                if(Character.isUpperCase(ch)) {
                    hasUpper = true;
                }

                if(Character.isLowerCase(ch)) {
                    hasLower = true;
                }

                if(Character.isDigit(ch)) {
                    hasDigit = true;
                }
            }

            if(!hasLower || !hasUpper) {
                return "Hasło musi zawierac duże i małe litery";
            }

            if(!hasDigit) {
                return "Hasło musi zawierać cyfry";
            }

            return "";
        });

        updateButton();
    }

    boolean validate(EditText input, TextView errorLabel, InputValidatorHandler handler) {
        String error = handler.validate(input.getText().toString());
        errorLabel.setText(error);

        boolean ok = error.isEmpty();
        validStates.put(input, ok);

        return ok;
    }

    boolean validateSilent(EditText input, InputValidatorHandler handler) {
        boolean ok = handler.validate(input.getText().toString()).isEmpty();
        validStates.put(input, ok);

        return ok;
    }

    boolean areAllValid() {
        for(Map.Entry<EditText, Boolean> entry : validStates.entrySet()) {
            if(!entry.getValue()) {
                return false;
            }
        }

        return true;
    }

    void updateButton() {
        findViewById(R.id.btn).setEnabled(areAllValid());
    }

    void regInput(EditText input, TextView errorLabel, InputValidatorHandler handler) {
        validateSilent(input, handler);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {}
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate(input, errorLabel, handler);
                updateButton();
            }
        });
    }
}