package com.by5388.demo.preference;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.toPreference).setOnClickListener(v -> startActivity(new Intent(this, MyFragmentActivity.class)));

    }
}
