package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.wuyin.supermarket.adapter.base.ListBaseAdapter;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.httpresult.GameHttpRequest;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends BaseFragment {

    List<AppInfo> appInfos;

    @Override
    public LoadingPage.LoadResult load() {
        GameHttpRequest request = new GameHttpRequest();
        appInfos = request.load(0);
        return checkLoad(appInfos);
    }

    @Override
    public View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(appInfos,listView) {
            @Override
            protected List<AppInfo> onLoad() {
                GameHttpRequest request = new GameHttpRequest();
                List<AppInfo> appInfos1 = request.load(datas.size());
               // appInfos.addAll(appInfos1);
                return appInfos1;
            }
        });
        return listView;
    }
}
