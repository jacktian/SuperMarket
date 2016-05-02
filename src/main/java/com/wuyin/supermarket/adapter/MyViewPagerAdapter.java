package com.wuyin.supermarket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wuyin.supermarket.fragment.AppFragment;
import com.wuyin.supermarket.fragment.HomeFragment;

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

    @Override
    public CharSequence getPageTitle(int position) {
        return "标签" + position;
    }
}
