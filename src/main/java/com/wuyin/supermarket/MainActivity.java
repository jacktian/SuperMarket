package com.wuyin.supermarket;


import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.action_settings:
                openSettings();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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

}
