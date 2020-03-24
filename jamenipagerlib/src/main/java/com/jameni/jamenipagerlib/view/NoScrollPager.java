package com.jameni.jamenipagerlib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class NoScrollPager extends ViewPager {


    public NoScrollPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollPager(Context context) {
        super(context);
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (isScrollEnable())
            return super.onTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (isScrollEnable())
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {

        super.setCurrentItem(item, isSmoothScrolEnable());
    }

    private boolean scrollEnable = true;//是否可以手势滑动viewpager
    private boolean smoothScrolEnable = true;//是否有滑动效果


    public void setScrollEnable(boolean scrollEnable) {
        this.scrollEnable = scrollEnable;
    }

    public boolean isScrollEnable() {
        return scrollEnable;
    }

    public void setSmoothScrolEnable(boolean smoothScrolEnable) {
        this.smoothScrolEnable = smoothScrolEnable;
    }

    public boolean isSmoothScrolEnable() {
        return smoothScrolEnable;
    }
}
