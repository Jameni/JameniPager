package com.jameni.jamenipager;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jameni.jamenipagerlib.view.JameniPager;


public class HomeActivity extends AppCompatActivity {
    JameniPager myPager;
    Fragment1 fragment1, fragment2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home);
        myPager = findViewById(R.id.myPager);
        initPage();
    }


    void initPage() {

        fragment1 = new Fragment1();
        fragment2 = new Fragment1();



        myPager.setFragmentManager(getSupportFragmentManager());
        myPager.addPage("page1", fragment1);
        myPager.addPage("page2", fragment2);
        myPager.setLine_color(R.color.colorPrimary);
        myPager.setText_normal_color(R.color.colorAccent);
        myPager.setText_selected_color(R.color.colorPrimaryDark);
        myPager.setBg_normal_color(R.color.bg_normal_color);
        myPager.setLine_padding(10);
        myPager.setDivider_width(2);
        myPager.setDivider_padding(20);
        myPager.setDivider_color_rgb(Color.rgb(100, 200, 100));
        myPager.setIndicatorVisiable(true);
        myPager.setSmoothScrolEnable(true);
        myPager.setScrollEnable(true);
        myPager.init();

    }
}
