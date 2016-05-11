package com.wuyin.supermarket.adapter.base;

import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.holder.base.ListBaseHolder;
import com.wuyin.supermarket.holder.base.BaseHolder;
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
