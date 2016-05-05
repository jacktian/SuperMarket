package com.wuyin.supermarket.adapter;

import android.view.View;

/**
 * Created by yinlong on 2016/5/5.
 */
public abstract class BaseHolder<T> {

    private View contentView;

    private T data;



    public BaseHolder() {
        contentView = initView();
        contentView.setTag(this);
    }

    public abstract View initView();

    public void setData(T data) {
        this.data = data;
        refreshData(data);
    }

    public abstract void refreshData(T data) ;


    public View getContenView() {

        return contentView;
    }
}
