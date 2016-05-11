package com.wuyin.supermarket.adapter;

import com.wuyin.supermarket.holder.ListBaseHolder;
import com.wuyin.supermarket.holder.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;

import java.util.List;

/**
 * Created by yinlong on 2016/5/11.
 */
public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {
    public ListBaseAdapter(List<AppInfo> datas) {
        super(datas);
    }

    @Override
    public BaseHolder<AppInfo> getHolder() {
        return new ListBaseHolder();
    }

    @Override
    protected abstract List<AppInfo> onLoad();
}
