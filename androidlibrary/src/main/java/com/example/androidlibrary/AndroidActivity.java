package com.example.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidActivity extends AppCompatActivity {
    public static final String Test_Joke = "TestJoke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        String Joke = getIntent().getStringExtra(Test_Joke);

        TextView jokers = findViewById(R.id.androidJoke_tv);
        jokers.setText(Joke);
    }
}
