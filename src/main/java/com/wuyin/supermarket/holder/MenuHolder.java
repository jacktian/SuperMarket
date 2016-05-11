package com.wuyin.supermarket.holder;

import android.view.View;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.UserInfo;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class MenuHolder extends BaseHolder<UserInfo> {
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.menu_holder);
        return view;
    }

    @Override
    public void refreshData(UserInfo data) {

    }

}
