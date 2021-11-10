package com.alphaomardiallo.topquiz2.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alphaomardiallo.topquiz2.Model.User;
import com.alphaomardiallo.topquiz2.R;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Connecting variables and view with findViewById method
        mGreetingTextView = findViewById(R.id.mainTextViewWelcome);
        mNameEditText = findViewById(R.id.mainEditTextTextPersonName);
        mPlayButton = findViewById(R.id.mainButtonPlay);

        User mUser = new User();

// Disabled playButton in order create method that enables player to play only when he gives his name and add listener
        mPlayButton.setEnabled(false);
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // detect string length and enables button PLAY to be pressed
                mPlayButton.setEnabled(!editable.toString().isEmpty());
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
                mUser.setFirstName(mNameEditText.getText().toString());
            }
        });

    }
}