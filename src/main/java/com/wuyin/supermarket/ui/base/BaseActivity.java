package com.wuyin.supermarket.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.wuyin.supermarket.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BaseActivity extends ActionBarActivity {

    //管理运行的所有的activity  增删比较快
    List<BaseActivity> mActivities = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        synchronized (mActivities) {
            mActivities.add(this);
        }

        init();
        initView();
        initActionBar();

    }

    /**
     * 杀死所有的activity，退出程序
     */
    public void killAll() {
        /*for (BaseActivity activity : mActivities) {
            activity.finish();
        }*/
        //复制了一份MainActivity
        List<BaseActivity> actis;
        synchronized (mActivities) {
             actis = new LinkedList<>(mActivities);
        }
        for (BaseActivity activity : actis) {
            activity.finish();
        }
        //杀死当前的进程
        android.os
                .Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
    }

    /**
     * 初始化ActionBar
     */
    protected void initActionBar() {

    }

    /**
     * 初始化布局控件
     */
    protected void initView() {

    }

    /**
     * 在布局文件之前初始化一些操作
     */
    protected void init() {

    }
}
