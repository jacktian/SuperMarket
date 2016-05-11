package com.wuyin.supermarket.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yinlong on 2016/5/11.
 */
public class HomePictureHolder extends BaseHolder<List<String>> {

    private ViewPager viewPager;
    List<String> datas = new ArrayList<>();

    //当创建的时候调用
    @Override
    public View initView() {
        viewPager = new ViewPager(UIUtils.getContext());
        viewPager.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, UIUtils
                .getDimens(R.dimen.home_picture_height)));
        return viewPager;
    }

    /**
     * 当设置data的时候调用
     *
     * @param data
     */
    @Override
    public void refreshData(List<String> data) {
        this.datas = data;
        viewPager.setAdapter(new HomeAdapter());
    }

    class HomeAdapter extends PagerAdapter {

        LinkedList<ImageView> convertView = new LinkedList<>();

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /*判断当前显示的view对象*/
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % datas.size();
            ImageView view = null;
            if (convertView.size() > 0) {
                view = convertView.remove(0);
            } else {
                view = new ImageView(UIUtils.getContext());
            }
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + datas.get(position))
                    .error(R.mipmap.ic_default)
                    .into(view);
            //添加进去imageview
            container.addView(view);
            return view;   //返回的对象ing
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            convertView.add(view);// 把移除的对象 添加到缓存集合中
            container.removeView(view);
        }
    }
}
