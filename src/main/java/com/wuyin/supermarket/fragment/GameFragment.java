package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.fragment.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends BaseFragment {


    @Override
    public LoadResult load() {
        return LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}
