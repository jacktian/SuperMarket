package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.wuyin.supermarket.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends BaseFragment {


    @Override
    public LoadResult load() {
        return LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}
