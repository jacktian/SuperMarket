package com.wuyin.supermarket.holder;

import android.os.ParcelUuid;
import android.view.View;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class MoreHolder extends BaseHolder<Integer> {

    public static final int HAS_NO_MORE = 0;     //没有额外的数据
    public static final int LOAD_ERROR = 1;   //加载失败
    public static final int HAS_MORE = 2;   //有额外的数据

    /**
     * 当holder显示的时候显示的样子
     *
     * @return
     */
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.load_more);

        return view;
    }

    @Override
    public void refreshData(Integer data) {

    }
}
