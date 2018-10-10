package com.jameni.jamenipager;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.jameni.jamenilistlib.view.RefreshView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.frag3)
public class Fragment3 extends Fragment {

    @FragmentArg
    String parmas;

    @ViewById
    RefreshView lvMain;
    private ItemAdapter adapter;
    private List<ItemModel> datalist;


    @AfterViews
    void initPage() {

        datalist = new ArrayList<>();

        datalist.add(new ItemModel(parmas));

        for (int i = 0; i < 20; i++) {
            datalist.add(new ItemModel("item==" + i));
        }


        adapter =new ItemAdapter();
        adapter.update(datalist);
        lvMain.setLinearManager();
        lvMain.setAdapter(adapter);
        lvMain.setDivider();

    }
}
