package com.example.customview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.customview.customview.CustomButton;
import com.example.customview.customview.CustomEditText;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private CustomButton myButton;
    private CustomEditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.customButton);
        myEditText = findViewById(R.id.customEditText);

        setMyButtonEnable();

        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setMyButtonEnable();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        myButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, myEditText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setMyButtonEnable() {
        Editable result = myEditText.getText();
        if (result != null && !result.toString().isEmpty()) {
            myButton.setEnabled(true);
        } else {
            myButton.setEnabled(false);
        }
    }
}
