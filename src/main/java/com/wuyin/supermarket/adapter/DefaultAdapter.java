package com.wuyin.supermarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.wuyin.supermarket.holder.BaseHolder;
import com.wuyin.supermarket.holder.MoreHolder;

import java.net.PortUnreachableException;
import java.util.List;

/**
 * Created by yinlong on 2016/5/5.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {

    private static final int DEFAULT_ITEM = 0;
    private static final int MORE_ITEM = 1;

    protected List<T> datas;

    public DefaultAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size() + 1;  //最后的一个加载更多的条目
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 当前lietview有几种view类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {  //当前最后的一个条目
            return MORE_ITEM;
        }
        return getInnerItemType(position);
    }

    /**
     * @param position
     * @return
     */
    private int getInnerItemType(int position) {

        return DEFAULT_ITEM;
    }


    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* BaseHolder holder = null;
        *//*if (position == datas.size()) {  //如果是最后一个就显示加载的view
            holder = getMoreHolder();
            return holder.getContentView();
        }*//*
        if (convertView == null) {
            if (getInnerItemType(position) == DEFAULT_ITEM) {
                holder = getHolder();  //普通条目的holder
            } else if(getInnerItemType(position) == MORE_ITEM){
                holder = getMoreHolder();
            }
        } else {
            if (getItemViewType(position) == DEFAULT_ITEM) {
                holder = (BaseHolder) convertView.getTag();
            } else {
                holder = getMoreHolder();
            }
        }

        if (getInnerItemType(position) == DEFAULT_ITEM) {
            T info = datas.get(position);
            holder.setData(info);
        }

        return holder.getContentView();*/
        BaseHolder holder = null;

        switch (getItemViewType(position)) {  // 判断当前条目时什么类型
            case MORE_ITEM:
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                break;
            case DEFAULT_ITEM:
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                if (position < datas.size()) {
                    holder.setData(datas.get(position));
                }
                break;
        }
        return holder.getContentView();  //  如果当前Holder 恰好是MoreHolder  证明MoreHOlder已经显示
    }

    private BaseHolder getMoreHolder() {
        return new MoreHolder();
    }

    public abstract BaseHolder<T> getHolder();
}
