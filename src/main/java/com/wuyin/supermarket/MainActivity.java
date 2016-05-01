package com.wuyin.supermarket;


import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener, ActionBar.TabListener {


    private DrawerLayout mDrawerLayout;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
        //显示原始的箭头的
        // mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

        addTab(mActionBar);

        drawerToggle();

    }

    private void drawerToggle() {
        /*
         *	1）显示Navigation Drawer的 Activity 对象
			2） DrawerLayout 对象
			3）一个用来指示Navigation Drawer的 drawable资源
			4）一个用来描述打开Navigation Drawer的文本 (用于支持可访问性)。
			5）一个用来描述关闭Navigation Drawer的文本(用于支持可访问性).
		 */
        mDrawerToggle =
                new ActionBarDrawerToggle(this,
                        mDrawerLayout,
                        R.mipmap.ic_drawer_am,
                        R.string.open_drawer,
                        R.string.close_drawer) {

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        Toast.makeText(MainActivity.this, "抽屉关闭", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        Toast.makeText(MainActivity.this, "抽屉打开", Toast.LENGTH_SHORT).show();
                    }
                };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //让开关和actionBar建立关系
        mDrawerToggle.syncState();
    }

    /**
     * 添加tab标签
     *
     * @param actionBar
     */
    private void addTab(ActionBar actionBar) {
        ActionBar.Tab tab1 = actionBar.newTab().setText("标签一")
                .setTabListener(this);
        actionBar.addTab(tab1);
        ActionBar.Tab tab2 = actionBar.newTab().setText("标签二")
                .setTabListener(this);
        actionBar.addTab(tab2);
        ActionBar.Tab tab3 = actionBar.newTab().setText("标签三")
                .setTabListener(this);
        actionBar.addTab(tab3);
        ActionBar.Tab tab4 = actionBar.newTab().setText("标签四")
                .setTabListener(this);
        actionBar.addTab(tab4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        if (Build.VERSION.SDK_INT > 11) {
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                    .getActionView();
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                break;
           /* case R.id.action_settings:
                openSettings();
                break;*/
           /* case android.R.id.home:
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
                break;*/
            default:
                break;
        }
        return mDrawerToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }

    /**
     * 打开设置的方法
     */
    private void openSettings() {

    }

    /**
     * 打开搜索方法
     */
    private void openSearch() {
        // Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
    }

    /**
     * 搜索text提交的时候调用的方法
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * 搜索框中字符变化的时候调用
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
