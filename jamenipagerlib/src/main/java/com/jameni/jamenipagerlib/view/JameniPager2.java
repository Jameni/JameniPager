package com.jameni.jamenipagerlib.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jameni.jamenipagerlib.R;
import com.jameni.jamenipagerlib.adapter.NormalPagerAdapter2;
import com.jameni.jamenipagerlib.i.PageChangeListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 好用的 viewpager2
 */

public class JameniPager2 extends LinearLayout {

    private Context context;
    private ViewPager2 pager;
    private View view;
    private TabLayout tabs;
    private TextView tvPageIndex;
    private NormalPagerAdapter2 adapter;

    //    private List<String> tabList;
    private List<TabLayout.Tab> tabViewList;
    private int currentPosition;//当前位置
    private int tabMode;//tab模式
    private int tabGravity;//tab gravity

    private int text_selected_color;//选中文本的颜色
    private int text_normal_color;//未选中文本（默认文本颜色）
    private int bg_normal_color;//未选中背景颜色（默认）
//    private int bg_selected_color;//选中的背景颜色


    private int line_color;//字下面的 指示线的颜色
    private int line_height = 7;//字下面的 指示线的高度
    private int line_padding = 20;//字下面的 指示线 和左右两边的距离

    private int divider_width = 2;/// 字与字之间的分割线 宽度
    private int divider_padding = 15;// 分割线的和边缘的距离
    private int divider_color_rgb = Color.rgb(42, 170, 235);// 字与字之间的分割线的颜色值 用rgb

    private boolean indicatorVisiable = true;//默认展示指示条

    private boolean scrollEnable = true;//是否可以手势滑动viewpager
    //    private boolean smoothScrolEnable = true;//是否有滑动动画
    private boolean pageIndexVisiable = false;//是否可见页码
    private boolean isTabAverage = true;//是否tab等分屏幕宽度


    private int pagerOrientation = ViewPager2.ORIENTATION_HORIZONTAL;//滑动方向

    private PageChangeListener listener;

    private FragmentActivity fragmentActivity;
    private ArrayList<Fragment> pageList = new ArrayList<>();


    public JameniPager2(Context context) {
        super(context);
        this.context = context;
        initView(context, null);
    }

    public JameniPager2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public JameniPager2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.context = context;

        view = LayoutInflater.from(context).inflate(R.layout.view_jameni_pager2, null);
        pager = view.findViewById(R.id.vp2);
        tabs = view.findViewById(R.id.tabs);
        tvPageIndex = view.findViewById(R.id.tvPageIndex);
        addView(view);
    }

    public void setTabModeScroll() {
        tabMode = TabLayout.MODE_SCROLLABLE;
        if (tabs != null) {
            tabs.setTabMode(tabMode);
        }
    }

    public void setTabModeFixed() {
        tabMode = TabLayout.MODE_FIXED;
        if (tabs != null) {
            tabs.setTabMode(tabMode);
        }
    }

    public void setTabGravityCenter() {
        tabGravity = TabLayout.GRAVITY_CENTER;
        if (tabs != null) {
            tabs.setTabGravity(tabGravity);
        }
    }

    public void setTabGravityFill() {
        tabGravity = TabLayout.GRAVITY_FILL;
        if (tabs != null) {
            tabs.setTabGravity(tabGravity);
        }
    }


    public void init() {
        if (getFragmentActivity() == null) return;

        //初始化viewpager2
        if (getPageSize() > 0) {
            adapter = new NormalPagerAdapter2(getPageList(), getFragmentActivity());
            pager.setAdapter(adapter);
            pager.setBackgroundColor(Color.WHITE);

            //滑动监听
            pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

//                    PrintUtil.printMsg("当前位置：" + position);

                    setCurrentPosition(position);
                    setIndexText();
                    if (listener != null) {
                        listener.pageSelected(position);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });


            pager.setUserInputEnabled(scrollEnable);//设置是否可以滑动
            pager.setOrientation(getPagerOrientation());
            //初始化指示条
            initIndicator();
            setIndexText();


        }
    }


    //设置当前页码
    private void setIndexText() {

        tvPageIndex.setVisibility(pageIndexVisiable ? VISIBLE : GONE);
        if (getPageSize() > 0) {
            String page = (getCurrentPosition() + 1) + " " + File.separator + " " + getPageSize();
            tvPageIndex.setText(page);
        } else {

            tvPageIndex.setVisibility(GONE);
        }

    }


    private void initIndicator() {

        if (!isIndicatorVisiable() || getTabSize() == 0) {
            tabs.setVisibility(View.GONE);
            return;
        } else {

            TabLayoutMediator mediator = new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                    if (position < getTabSize()) {
                        tab.setText(tabViewList.get(position).getText().toString());
                    }
                }
            });
            mediator.attach();
            tabs.setVisibility(View.VISIBLE);


            if (isTabAverage) {
                setTabGravityFill();
                setTabModeFixed();
            }

            //设置下划线颜色
            tabs.setSelectedTabIndicatorColor(ContextCompat.getColor(context, line_color));
            tabs.setTabTextColors(ContextCompat.getColor(context, text_normal_color), ContextCompat.getColor(context, text_selected_color));
        }


    }

    private int getTabSize() {

        if (tabViewList != null) {
            return tabViewList.size();
        }
        return 0;

    }

    public void addTab(String text) {

        if (tabViewList == null) {
            this.tabViewList = new ArrayList<>();
        }
        TabLayout.Tab tab = tabs.newTab();
        tab.setText(text);
        tabViewList.add(tab);


    }

    //更新标题
    public void updateTab(@NonNull String title, int index) {

        if (tabViewList != null && index < tabViewList.size()) {

            TabLayout.Tab tab = tabViewList.get(index);
            tab.setText(title);
        }
    }


    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    public int getLine_color() {
        return line_color == 0 ? R.color.text_selected_color : line_color;
    }

    public void setLine_color(int line_color) {
        this.line_color = line_color;
    }

    public int getText_selected_color() {
        return text_selected_color == 0 ? R.color.text_selected_color : text_selected_color;
    }

    public void setText_selected_color(int text_selected_color) {
        this.text_selected_color = text_selected_color;
    }

    public int getText_normal_color() {
        return text_normal_color == 0 ? R.color.text_normal_color : text_normal_color;
    }

    public void setText_normal_color(int text_normal_color) {
        this.text_normal_color = text_normal_color;
    }

    public int getLine_height() {
        return line_height;
    }

    public void setLine_height(int line_height) {
        this.line_height = line_height;
    }


    public void addPage(Fragment fragment) {
        if (fragment != null && this.pageList != null) {
            this.pageList.add(fragment);
        }
    }

    public void addPage(String title, Fragment fragment) {
//        PrintUtil.printMsg("add page  " + title);
        addTab(title);
        addPage(fragment);
    }

    public void setPageList(ArrayList<Fragment> pageList) {
        this.pageList = pageList;
    }

    public ArrayList<Fragment> getPageList() {
        return pageList == null ? pageList = new ArrayList<>() : pageList;
    }

    public int getPageSize() {
        if (this.pageList != null) {
            return this.pageList.size();
        }
        return 0;
    }

    public Fragment getPage(int position) {
        return this.pageList != null && this.pageList.size() > 0 && this.pageList.size() > position ? this.pageList.get(position) : null;
    }

    public void setFragmentActivity(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public FragmentActivity getFragmentActivity() {
        return fragmentActivity;
    }

    public int getDivider_width() {
        return divider_width;
    }

    public void setDivider_width(int divider_width) {
        this.divider_width = divider_width;
    }


    public int getDivider_padding() {
        return divider_padding;
    }

    public void setDivider_padding(int divider_padding) {
        this.divider_padding = divider_padding;
    }

    public int getDivider_color_rgb() {
        return divider_color_rgb;
    }

    public void setDivider_color_rgb(int divider_color_rgb) {
        this.divider_color_rgb = divider_color_rgb;
    }

    public int getLine_padding() {
        return line_padding;
    }

    //    padding 左右的距离
    public void setLine_padding(int line_padding) {
        this.line_padding = line_padding;
    }

    public boolean isIndicatorVisiable() {
        return indicatorVisiable;
    }

    public void setIndicatorVisiable(boolean indicatorVisiable) {
        this.indicatorVisiable = indicatorVisiable;
    }

    public boolean isPageIndexVisiable() {
        return pageIndexVisiable;
    }

    public void setPageIndexVisiable(boolean pageIndexVisiable) {
        this.pageIndexVisiable = pageIndexVisiable;
    }

    public void setScrollEnable(boolean scrollEnable) {
        this.scrollEnable = scrollEnable;
    }

    public boolean isScrollEnable() {
        return scrollEnable;
    }

    public int getBg_normal_color() {
        return bg_normal_color == 0 ? R.color.bg_normal_color : bg_normal_color;
    }

    public void setBg_normal_color(int bg_normal_color) {
        this.bg_normal_color = bg_normal_color;
    }

    public void setListener(PageChangeListener listener) {
        this.listener = listener;
    }

    public ViewPager2 getPager() {
        return pager;
    }

    public List<TabLayout.Tab> getTabViewList() {
        return tabViewList;
    }

    public TabLayout getTabs() {
        return tabs;
    }

    public View getView() {
        return view;
    }

    public void showPage(int position) {
        pager.setCurrentItem(position);
        setCurrentPosition(position);
        setIndexText();
    }

    public void setPagerVertical() {
        this.pagerOrientation = ViewPager2.ORIENTATION_VERTICAL;
        if (pager != null) {
            pager.setOrientation(pagerOrientation);
        }
    }

    public void setPagerHorizontal() {
        this.pagerOrientation = ViewPager2.ORIENTATION_HORIZONTAL;
        if (pager != null) {
            pager.setOrientation(pagerOrientation);
        }
    }

    public int getPagerOrientation() {
        return pagerOrientation;
    }

    public void setTabAverage(boolean tabAverage) {
        isTabAverage = tabAverage;
    }

    public TextView getTvPageIndex() {
        return tvPageIndex;
    }
}
