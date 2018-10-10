package com.jameni.jamenipager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import com.jameni.jamenipagerlib.view.JameniPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.page_home)
public class HomeActivity extends AppCompatActivity {


    @ViewById
    JameniPager myPager;
    Fragment1 fragment1, fragment2;
    Fragment2 fragment3, fragment4;//嵌套 scrollview
    Fragment3 fragment5, fragment6;//嵌套 recycleview
    @AfterViews
    void initPage() {

        fragment1 = Fragment1_.builder().parmas("第一 个fragmentmen 页面").build();
        fragment2 = Fragment1_.builder().parmas("第二 个fragmentmen 页面").build();

        fragment3 = Fragment2_.builder().parmas("第一 个fragmentmen 页面").build();
        fragment4 = Fragment2_.builder().parmas("第二 个fragmentmen 页面").build();


        fragment5 = Fragment3_.builder().parmas("第一 个fragmentmen 页面").build();
        fragment6 = Fragment3_.builder().parmas("第二 个fragmentmen 页面").build();


        myPager.setFragmentManager(getSupportFragmentManager());
        myPager.addPage("page1", fragment3);
        myPager.addPage("page2", fragment4);
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
