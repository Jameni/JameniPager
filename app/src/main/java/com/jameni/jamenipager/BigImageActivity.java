package com.jameni.jamenipager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.jameni.jamenipagerlib.adapter.BigImagePagerAdapter;
import com.jameni.jamenipagerlib.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_big);

        ViewPager pager = findViewById(R.id.vp);
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2418635405,3223589249&fm=26&gp=0.jpg");
        list1.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=604589800,3757564481&fm=26&gp=0.jpg");
        list1.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3544011334,1953151633&fm=26&gp=0.jpg");

        list1.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2418635405,3223589249&fm=26&gp=0.jpg");
        list1.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=604589800,3757564481&fm=26&gp=0.jpg");
        list1.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3544011334,1953151633&fm=26&gp=0.jpg");

       List<ImageModel> list2 = new ArrayList<>();

        for (String url : list1) {
            list2.add(new ImageModel(url));
        }

//        pager.setAdapter(new BigImagePagerAdapter(this, list1));
        pager.setAdapter(new BigImagePagerAdapter(this, list2));
    }
}
