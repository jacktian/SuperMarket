package com.wuyin.supermarket;


import android.content.res.Resources;

import com.wuyin.supermarket.application.BaseApplication;

/**
 * Created by wuyin on 2016/5/2.
 */
public class UIUtils {

    /**
     * 获取到xml中的字符数组
     *
     * @param tab_names 字符数组的ID
     * @return
     */
    public static String[] getStringArray(int tab_names) {
        return getResources().getStringArray(tab_names);
    }

    /**
     * 获取getResources()
     *
     * @return
     */
    private static Resources getResources() {
        return BaseApplication.getmApplication().getResources();
    }


    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** pxz转换dip */

    public static int px2dip(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
