package com.jameni.jamenipager;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EFragment(R.layout.frag2)
public class Fragment2 extends Fragment {

    @FragmentArg
    String parmas;

    @ViewById
    TextView show;

    @StringRes
    String des;

    @AfterViews
    void initPage() {
        show.setText(parmas + des);
    }
}
