package com.wuyin.supermarket;

import com.wuyin.supermarket.adapter.BaseHolder;
import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.model.AppInfo;

import java.util.List;

/**
 * Created by yinlong on 2016/5/11.
 */
public class ListBaseAdapter extends DefaultAdapter<AppInfo> {
    public ListBaseAdapter(List<AppInfo> datas) {
        super(datas);
    }

    @Override
    public BaseHolder<AppInfo> getHolder() {
        return new ListBaseHolder();
    }
}
