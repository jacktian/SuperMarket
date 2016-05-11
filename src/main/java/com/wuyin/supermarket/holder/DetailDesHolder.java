package com.wuyin.supermarket.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class DetailDesHolder extends BaseHolder<AppInfo>{


    private TextView des_content;
    private TextView des_author;
    private ImageView des_arrow;
    private RelativeLayout des_layout;
    @Override
    public View initView() {
        View view= UIUtils.inflate(R.layout.detail_des);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        des_content = (TextView) view.findViewById(R.id.des_content);
        des_author = (TextView) view.findViewById(R.id.des_author);
        des_arrow = (ImageView) view.findViewById(R.id.des_arrow);
        des_layout = (RelativeLayout) view.findViewById(R.id.des_layout);
    }

    @Override
    public void refreshData(AppInfo data) {

    }
}
