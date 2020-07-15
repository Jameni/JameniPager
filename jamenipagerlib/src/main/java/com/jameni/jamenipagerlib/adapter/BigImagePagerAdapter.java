package com.jameni.jamenipagerlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.jameni.jamenipagerlib.R;
import com.jameni.jamenipagerlib.model.ImageModel;
import com.jameni.jamenipagerlib.view.PinchImageView;

import java.util.ArrayList;
import java.util.List;

public class BigImagePagerAdapter extends PagerAdapter {

    private List<View> list = new ArrayList<>();
    private Context context;


    public BigImagePagerAdapter(Context context, List<ImageModel> datalist) {
        this.context = context;
        if (datalist != null) {
            for (ImageModel item : datalist) {
                if (item != null && item.getImgUrl() != null) {

                    switch (item.getImgType()) {
                        case 0:
                        case 1:
                            list.add(getImageViewByUrl(item.getImgUrl()));
                            break;
                        case 2:
                            list.add(getImageViewById(item.getImgResId()));
                            break;
                    }
                }
            }
        }
    }

    public BigImagePagerAdapter(Context context, ArrayList<String> datalist) {
        this.context = context;
        if (datalist != null) {
            for (String item : datalist) {

                if (item != null && !item.equals("")) {
                    list.add(getImageViewByUrl(item));
                }

            }
        }

    }


    private View getImageViewByUrl(String imgUrl) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_big_image, null);
        PinchImageView img = view.findViewById(R.id.imgPic);
        Glide.with(context).load(imgUrl).thumbnail(0.35f).into(img);
        return view;
    }

    private View getImageViewById(int resId) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_big_image, null);
        PinchImageView img = view.findViewById(R.id.imgPic);
        Glide.with(context).load(resId).thumbnail(0.35f).into(img);
        return view;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View item = list.get(position);
        container.addView(item, 0);
        return item;

    }
}
