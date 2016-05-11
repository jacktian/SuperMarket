package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.ListBaseAdapter;
import com.wuyin.supermarket.ListBaseHolder;
import com.wuyin.supermarket.adapter.BaseHolder;
import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.httpresult.HomeHttpRequest;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    List<AppInfo> appInfos = new ArrayList<>();

    /**
     * 当fragment挂载到activity中的时候调用，要不然有个问题
     * 就是当我们第一次进入到app的时候，因为第一个首先加载的是homefragment
     * 所以我们要去加载之前去加载一下
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    public LoadingPage.LoadResult load() {

        HomeHttpRequest httpRequest = new HomeHttpRequest();
        appInfos = httpRequest.load(0);

        return checkLoad(appInfos);
    }


    @Override
    public View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(appInfos));
        return listView;
    }

   /* *//**
     * adapter
     *//*
    class HomeAdapter extends DefaultAdapter<AppInfo> {

        public HomeAdapter(List<AppInfo> datas) {
            super(datas);
        }



        @Override
        public BaseHolder<AppInfo> getHolder() {

            return new ListBaseHolder();
        }
    }
*/



}
