package com.bignerdranch.android.myread;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class ReadPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> aList;
    public ReadPageAdapter(FragmentManager fm, ArrayList<Fragment>aList) {
            super(fm);
        this.aList=aList;
    }
    @Override
    public Fragment getItem(int i) {
        return aList.get(i);
    }
    @Override
    public int getCount() {
        return aList.size();
    }
}
