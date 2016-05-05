package com.wuyin.supermarket.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.httpresult.HomeHttpRequest;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    List<AppInfo> appInfos = new ArrayList<>();
    AppInfo appInfo;

    /**
     * 当fragment挂载到activity中的时候调用，要不然有个问题
     * 就是当我们第一次进入到app的时候，因为第一个首先加载的是homefragment
     * 所以我们要去加载之前去加载一下
     *
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


    @Override
    public View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new HomeAdapter(appInfos));
        return listView;
    }

    /**
     * adapter
     */
    class HomeAdapter extends DefaultAdapter<AppInfo> {

        public HomeAdapter(List<AppInfo> datas) {
            super(datas);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AppInfo appInfo = appInfos.get(position);
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.setAppInfo(appInfo);

            return holder.getContenView();
        }
    }

    /**
     * ViewHolder
     */
    class ViewHolder {
        ImageView image_item;
        TextView item_title, item_size, item_bottom;
        RatingBar item_rating;

        private View contenView;

        private AppInfo appInfo;

        public void setAppInfo(AppInfo appInfo) {
            this.appInfo = appInfo;
            refreshView();
        }

        public View getContenView() {
            return contenView;
        }

        public ViewHolder() {
            contenView = View.inflate(UIUtils.getContext(), R.layout.home_item, null);
            image_item = (ImageView) contenView.findViewById(R.id.item_icon);
            item_title = (TextView) contenView.findViewById(R.id.item_title);
            item_size = (TextView) contenView.findViewById(R.id.item_size);
            item_bottom = (TextView) contenView.findViewById(R.id.item_bottom);
            item_rating = (RatingBar) contenView.findViewById(R.id.item_rating);
            contenView.setTag(this);
        }

        /**
         * 刷新view的方法
         */
        public void refreshView() {
            //加载图片
            Glide.with(UIUtils.getContext())
                    .load(Constants.IMAGE_URL + appInfo.getIconUrl())
                    .error(R.mipmap.ic_default)
                    .into(image_item);

            item_title.setText(appInfo.getName());

            //得到app的大小
            String size = android.text.format.Formatter.formatFileSize(UIUtils.getContext(), appInfo.getSize());
            item_size.setText(size);
            item_bottom.setText(appInfo.getDes());

            //设置ratingBar
            float stars = appInfo.getStars();
            item_rating.setRating(stars);
        }
    }


}
