package com.wuyin.supermarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

/**
 * Created by yinlong on 2016/5/5.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {

    protected List<T> datas;

    public DefaultAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holder = null;
        if (convertView == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        T info = datas.get(position);
        holder.setData(info);

        return holder.getContentView();
    }

    public abstract BaseHolder<T> getHolder() ;
}
