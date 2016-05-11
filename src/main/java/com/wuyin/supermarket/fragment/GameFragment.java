package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wuyin.supermarket.ListBaseAdapter;
import com.wuyin.supermarket.R;
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
        listView.setAdapter(new ListBaseAdapter(appInfos));
        return listView;
    }
}
