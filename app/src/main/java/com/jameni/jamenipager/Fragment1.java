package com.jameni.jamenipager;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jameni.jamenipagerlib.view.JameniPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.frag1)
public class Fragment1 extends Fragment {

    @FragmentArg
    String parmas;

    @ViewById
    TextView show;

    @AfterViews
    void initPage() {


        show.setText(parmas == null ? "null pointer" : parmas);
    }
}
