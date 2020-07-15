package com.jameni.jamenipager;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jameni.jamenipagerlib.view.JameniPager2;


public class HomeActivity2 extends AppCompatActivity {
    JameniPager2 myPager;
    Fragment1 fragment1, fragment2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_home2);
        myPager = findViewById(R.id.myPager);
        initPage();
    }


    void initPage() {

        fragment1 = new Fragment1();
        fragment2 = new Fragment1();

        //添加过的fragment 不能重复添加，不然会闪退
        myPager.setFragmentActivity(this);
        myPager.addPage("page1", fragment1);
        myPager.addPage("page2", fragment2);
        myPager.addPage("page3", new Fragment1());
        myPager.addPage("page4", new Fragment1());
        myPager.addPage("page5", new Fragment1());
        myPager.addPage("page6", new Fragment1());
        myPager.addPage("page7", new Fragment1());
        myPager.addPage("page8", new Fragment1());
//        myPager.addPage("page1", fragment1);
//        myPager.addPage("page2", fragment2);
        myPager.addPage("page3", new Fragment1());
        myPager.addPage("page4", new Fragment1());
        myPager.addPage("page5", new Fragment1());
        myPager.addPage("page6", new Fragment1());
        myPager.addPage("page7", new Fragment1());
        myPager.addPage("page8", new Fragment1());
//        myPager.addPage("page1", fragment1);
//        myPager.addPage("page2", fragment2);
        myPager.addPage("page3", new Fragment1());
        myPager.addPage("page4", new Fragment1());
        myPager.addPage("page5", new Fragment1());
        myPager.addPage("page6", new Fragment1());
        myPager.addPage("page7", new Fragment1());
        myPager.addPage("page8", new Fragment1());

        myPager.setLine_color(R.color.colorPrimary);
        myPager.setText_normal_color(R.color.colorAccent);
        myPager.setText_selected_color(R.color.colorPrimaryDark);
        myPager.setBg_normal_color(R.color.bg_normal_color);
        myPager.setLine_padding(10);
        myPager.setDivider_width(2);
        myPager.setDivider_padding(20);
        myPager.setDivider_color_rgb(Color.rgb(100, 200, 100));
        myPager.setIndicatorVisiable(true);
        myPager.setScrollEnable(true);
//        myPager.setPagerVertical();
        myPager.setPagerHorizontal();
        myPager.setPageIndexVisiable(false);
        myPager.setTabAverage(true);//每个tab是否撑满
        myPager.setTabAverage(false);//每个tab是否撑满
        myPager.init();

    }
}
