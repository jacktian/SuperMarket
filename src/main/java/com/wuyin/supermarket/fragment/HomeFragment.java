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
import com.wuyin.supermarket.adapter.BaseHolder;
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
        public BaseHolder<AppInfo> getHolder() {

            return new ViewHolder();
        }
    }

    /**
     * ViewHolder
     */
    class ViewHolder extends BaseHolder<AppInfo> {
        ImageView image_item;
        TextView item_title, item_size, item_bottom;
        RatingBar item_rating;

        private View contenView;



        public View getContenView() {
            return contenView;
        }

        public ViewHolder() {

        }

        @Override
        public View initView() {
            contenView = View.inflate(UIUtils.getContext(), R.layout.home_item, null);
            image_item = (ImageView) contenView.findViewById(R.id.item_icon);
            item_title = (TextView) contenView.findViewById(R.id.item_title);
            item_size = (TextView) contenView.findViewById(R.id.item_size);
            item_bottom = (TextView) contenView.findViewById(R.id.item_bottom);
            item_rating = (RatingBar) contenView.findViewById(R.id.item_rating);
            contenView.setTag(this);
            return contenView;
        }

        @Override
        public void refreshData(AppInfo data) {
            //加载图片
            Glide.with(UIUtils.getContext())
                    .load(Constants.IMAGE_URL + data.getIconUrl())
                    .error(R.mipmap.ic_default)
                    .into(image_item);

            item_title.setText(data.getName());

            //得到app的大小
            String size = android.text.format.Formatter.formatFileSize(UIUtils.getContext(), data.getSize());
            item_size.setText(size);
            item_bottom.setText(data.getDes());

            //设置ratingBar
            float stars = data.getStars();
            item_rating.setRating(stars);
        }

    }


}
