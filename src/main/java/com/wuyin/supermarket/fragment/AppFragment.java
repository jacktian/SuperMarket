package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.wuyin.supermarket.adapter.base.ListBaseAdapter;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.httpresult.AppHttpRequest;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends BaseFragment {


    List<AppInfo> appInfos = new ArrayList<>();

    /**
     * 请求服务器，加载数据
     * @return
     */
    @Override
    public LoadingPage.LoadResult load() {
        AppHttpRequest request = new AppHttpRequest();
        appInfos = request.load(0);
        return checkLoad(appInfos);
    }

    /**
     * 当加载成功的时候显示界面
     * @return
     */
    @Override
    public View createSuccessView() {
        ListView listView  = new ListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(appInfos,listView) {
            @Override
            protected List<AppInfo> onLoad() {
                AppHttpRequest request = new AppHttpRequest();
                List<AppInfo> load = request.load(appInfos.size());
                appInfos.addAll(load);
                return load;
            }
        });
        return listView;
    }


}
