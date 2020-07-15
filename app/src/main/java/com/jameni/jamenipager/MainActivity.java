package com.jameni.jamenipager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        HomeActivity_.intent(this).start();

//        startActivity(new Intent(this, HomeActivity2.class));
        startActivity(new Intent(this, BigImageActivity.class));
        finish();
    }
}
