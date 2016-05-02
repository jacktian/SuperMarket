package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.view.LoadingPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends BaseFragment {


    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}
