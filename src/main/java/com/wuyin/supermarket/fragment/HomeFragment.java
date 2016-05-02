package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wuyin.supermarket.HomeHttpRequest;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.http.OkHttpManager;
import com.wuyin.supermarket.utils.LogUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.io.IOException;


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
        String url = "http://192.168.1.103:8080/WebInfos/app/homelist0";

        final HomeHttpRequest request = new HomeHttpRequest();

        // request.load(0);
        OkHttpManager.getAsync(url, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                parseJson(result);
            }
        });

        return LoadingPage.LoadResult.success;
    }

    private void parseJson(String result) {

        Log.d("HomeFragment", result);
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
