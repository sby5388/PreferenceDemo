package com.by5388.demo.preference;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator  on 2019/9/26.
 */
public class MyFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.container, MyPreferenceFragment.newInstance()).commit();
    }
}
