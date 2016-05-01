package com.wuyin.supermarket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by wuyin on 2016/5/2.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new HomeFragment();
        } else {
            return new AppFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
