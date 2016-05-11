package com.wuyin.supermarket.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.wuyin.supermarket.httpresult.DetailHttpRequest;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.ui.base.BaseActivity;
import com.wuyin.supermarket.view.LoadingPage;

public class DetailActivity extends BaseActivity {

    private String packageName;
    private AppInfo appInfo;



    @Override
    protected void initView() {
        LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public LoadResult load() {

                return DetailActivity.this.load();
            }

            @Override
            public View createSuccessView() {

                return DetailActivity.this.createSuccessView();
            }
        };
        setContentView(loadingPage);
    }

    /**
     * 加载成功的解码
     *
     * @return
     */
    private View createSuccessView() {

        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        packageName = getIntent().getStringExtra("packageName");
        super.onCreate(savedInstanceState);
    }

    /**
     * 请求服务器加载数据
     *
     * @return
     */
    private LoadingPage.LoadResult load() {
        DetailHttpRequest request = new DetailHttpRequest(packageName);
        appInfo = request.load(0);
        if (appInfo == null) {
            return LoadingPage.LoadResult.error;
        } else {
            return LoadingPage.LoadResult.success;
        }
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        ActionBar actionBar = getSupportActionBar();
        //显示按钮
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
