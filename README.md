# JameniPager

    @ViewById
    JameniPager myPager;
    Fragment1 fragment1, fragment2;

    @AfterViews
    void initPage() {

        fragment1 = Fragment1_.builder().parmas("第一 个fragmentmen 页面").build();
        fragment2 = Fragment1_.builder().parmas("第二 个fragmentmen 页面").build();

        myPager.setFragmentManager(getSupportFragmentManager());
        myPager.addPage("page1",fragment1);
        myPager.addPage("page2",fragment2);
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


    ******使用的时候注意事项*******

    1.如果fragment中嵌套的 listview或者recycleview,
    列表的item中如果有textview 的gravity属性含有  center、right这两个属性的时候，会影响pager的左右滑动焦点