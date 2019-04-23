package com.jameni.jamenipagerlib.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jameni.jamenipagerlib.R;
import com.jameni.jamenipagerlib.adapter.NormalPagerAdapter;
import com.jameni.jamenipagerlib.i.PageChangeListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 好用的 viewpager
 */

public class JameniPager extends LinearLayout {

    private Context context;
    private NoScrollPager pager;
    //    private ViewPager pager;
    private View view;
    private MagicIndicator indicator;
    private NormalPagerAdapter adapter;

    private List<String> tabList;
    private int currentPosition;

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
    private boolean smoothScrolEnable = true;//是否有滑动动画

    private PageChangeListener listener;

    private FragmentManager fragmentManager;
    private ArrayList<Fragment> pageList = new ArrayList<>();


    public JameniPager(Context context) {
        super(context);
        this.context = context;
        initView(context, null);
    }

    public JameniPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public JameniPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.context = context;

        view = LayoutInflater.from(context).inflate(R.layout.view_jameni_pager, null);
        pager = view.findViewById(R.id.jameni_pager);
        indicator = view.findViewById(R.id.jameni_indicator);
        addView(view);
    }


    public void init() {

        //初始化指示条
        if (getFragmentManager() == null) {
            return;
        }
        initIndicator();

        //初始化viewpager
        if (getFragmentManager() != null && getPageSize() > 0) {
            adapter = new NormalPagerAdapter(getFragmentManager(), getPageList());
            pager.setAdapter(adapter);
            pager.setOffscreenPageLimit(getPageSize());
            pager.setBackgroundColor(Color.WHITE);
            pager.setSmoothScrolEnable(isSmoothScrolEnable());
            pager.setScrollEnable(isScrollEnable());
        }

    }

    CommonNavigator commonNavigator;

    private void initIndicator() {

        if (!isIndicatorVisiable() || getTabSize() == 0) {
            indicator.setVisibility(View.GONE);
            return;
        } else {
            indicator.setVisibility(View.VISIBLE);
        }

        commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {

                int count = 0;
                if (getTabList() != null && getTabList().size() > 0) {
                    count = getTabList().size();
                }
                return count;

            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                ColorTransitionPagerTitleView titleView = new ColorTransitionPagerTitleView(context);

//                设置文本默认颜色，和选中后的颜色
                titleView.setNormalColor(getResources().getColor(getText_normal_color()));
                titleView.setSelectedColor(getResources().getColor(getText_selected_color()));

                String text = "";
                if (getTabList().get(i) != null) {
                    text = getTabList().get(i);
                }

                //设置标题
                titleView.setText(text);

                //设置标题点击监听
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击部位
                        setCurrentPosition(i);
                        pager.setCurrentItem(i);
                    }
                });

                if (listener != null) {
                    listener.pageSelected(i);
                }


                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                linePagerIndicator.setColors(getResources().getColor(getLine_color()));//指示线的颜色
                linePagerIndicator.setLineHeight(getLine_height());//指示线的高度
                linePagerIndicator.setXOffset(getLine_padding());//指示线距离边缘的距离
//                linePagerIndicator.setBackgroundColor(getResources().getColor(getLine_color()));
                return linePagerIndicator;
            }
        });

        indicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        //设置指示线和 上下边缘的距离
        titleContainer.setDividerPadding(getDivider_padding());
        //设置分割线的rgb颜色 和 宽度
        titleContainer.setDividerDrawable(new ColorDrawable(getDivider_color_rgb()) {
            @Override
            public int getIntrinsicWidth() {
                return getDivider_width();
            }
        });

//        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        indicator.setBackgroundColor(getResources().getColor(getBg_normal_color()));
        ViewPagerHelper.bind(indicator, pager);
    }

    public List<String> getTabList() {
        return tabList;
    }

    public int getTabSize() {
        if (getTabList() != null) {
            return getTabList().size();
        }
        return 0;
    }

    public void setTabList(List<String> tabList) {
        this.tabList = tabList;
    }

    public void addTab(String tab) {

        if (tabList == null) {
            this.tabList = new ArrayList<>();
        }
        tabList.add(tab);

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
        addTab(title);
        addPage(fragment);
    }


    public void setPageList(ArrayList<Fragment> pageList) {
        if (pageList != null) {
            this.pageList = pageList;
        }
    }

    public ArrayList<Fragment> getPageList() {
        if (this.pageList != null) {
            return this.pageList;
        }

        return null;
    }

    public int getPageSize() {
        if (this.pageList != null) {
            return this.pageList.size();
        }
        return 0;
    }

    public Fragment getPage(int position) {
        if (this.pageList != null && this.pageList.size() > 0 && this.pageList.size() > position) {
            return this.pageList.get(position);
        }
        return null;
    }

    public void setFragmentManager(FragmentManager manager) {
        this.fragmentManager = manager;
    }

    public FragmentManager getFragmentManager() {
        return this.fragmentManager;
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

    public int getBg_normal_color() {
        return bg_normal_color == 0 ? R.color.bg_normal_color : bg_normal_color;
    }

    public void setBg_normal_color(int bg_normal_color) {
        this.bg_normal_color = bg_normal_color;
    }


    //更新标题
    public void updateTitle(String title, int index) {

        if (commonNavigator != null) {
            if (tabList != null && index < tabList.size()) {
                tabList.remove(index);
                tabList.add(index, title == null ? "" : title);
                if (commonNavigator.getAdapter() != null) {
                    commonNavigator.getAdapter().notifyDataSetChanged();
                }
            }
        }

    }

    public NoScrollPager getPager() {
        return pager;
    }

    public void showPage(int position) {
        pager.setCurrentItem(position);
        setCurrentPosition(position);
    }

    public void setListener(PageChangeListener listener) {
        this.listener = listener;
    }
}
