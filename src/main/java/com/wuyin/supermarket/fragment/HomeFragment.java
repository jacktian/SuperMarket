package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.view.LoadingPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {



    /**
     * 当fragment挂载到activity中的时候调用，要不然有个问题
     * 就是当我们第一次进入到app的时候，因为第一个首先加载的是homefragment
     * 所以我们要去加载之前去加载一下
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    public LoadingPage.LoadResult load() {


        return LoadingPage.LoadResult.success;
    }

    /**
     * 创建成功的界面
     *
     * @return
     */
    public View createSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setTextSize(30);
        tv.setText("加载成功");
        return tv;
    }




}
