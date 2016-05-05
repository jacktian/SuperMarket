package com.wuyin.supermarket.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wuyin.supermarket.HomeHttpRequest;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.application.BaseApplication;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.http.OkHttpManager;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.LogUtils;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    List<AppInfo> appInfos = new ArrayList<>();

    /**
     * 当fragment挂载到activity中的时候调用，要不然有个问题
     * 就是当我们第一次进入到app的时候，因为第一个首先加载的是homefragment
     * 所以我们要去加载之前去加载一下
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    public LoadingPage.LoadResult load() {

        HomeHttpRequest httpRequest = new HomeHttpRequest();
        appInfos = httpRequest.load(0);

        return checkLoad(appInfos);
    }

    /**
     * 校验数据
     * @param appInfos
     * @return
     */
    private LoadingPage.LoadResult checkLoad(List<AppInfo> appInfos) {
        if (appInfos == null){
            return LoadingPage.LoadResult.error;  //解析失敗
        } else if (appInfos.size() == 0){
            return LoadingPage.LoadResult.empty;
        } else {
            return LoadingPage.LoadResult.success;
        }
    }

    @Override
    public View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new HomeAdapter());
        return listView;
    }

    class HomeAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return appInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return appInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            AppInfo appInfo = appInfos.get(position);
            ViewHolder holder = null;
            if (convertView == null){
                view = View.inflate(UIUtils.getContext(),R.layout.home_item,null);
                holder = new ViewHolder();
                holder.image_item = (ImageView) view.findViewById(R.id.item_icon);
                holder.item_title = (TextView) view.findViewById(R.id.item_title);
                holder.item_size = (TextView) view.findViewById(R.id.item_size);
                holder.item_bottom = (TextView) view.findViewById(R.id.item_bottom);
                holder.item_rating = (RatingBar) view.findViewById(R.id.item_rating);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            //加载图片
            Glide.with(UIUtils.getContext())
                    .load(Constants.IMAGE_URL+appInfo.getIconUrl())
                    .error(R.mipmap.ic_default)
                    .into(holder.image_item);

            holder.item_title.setText(appInfo.getName());

            //得到app的大小
            String size = android.text.format.Formatter.formatFileSize(UIUtils.getContext(),appInfo.getSize());
            holder.item_size.setText(size);
            holder.item_bottom.setText(appInfo.getDes());

            //设置ratingBar
            float stars = appInfo.getStars();
            holder.item_rating.setRating(stars);

            return view;
        }
    }

    static class ViewHolder{
        ImageView image_item;
        TextView item_title,item_size,item_bottom;
        RatingBar item_rating;
    }


}
