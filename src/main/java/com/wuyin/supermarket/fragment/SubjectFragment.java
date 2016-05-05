package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.model.SubjectInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;
import com.wuyin.supermarket.httpresult.SubjectHttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends BaseFragment {

    List<SubjectInfo> subjectInfos = new ArrayList<>();

    @Override
    public LoadingPage.LoadResult load() {

        SubjectHttpRequest httpRequest = new SubjectHttpRequest();
        subjectInfos =  httpRequest.load(0);

        return checkLoad(subjectInfos);
    }

    @Override
    public View createSuccessView() {

        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new MyAdapter());

        return listView;
    }

    class ViewHolder {
        ImageView item_img;
        TextView item_title;
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return subjectInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return subjectInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder = null;
            if (convertView == null){
                holder = new ViewHolder();
                view = View.inflate(UIUtils.getContext(), R.layout.subject_item,null);
                holder.item_img = (ImageView) view.findViewById(R.id.item_img);
                holder.item_title = (TextView) view.findViewById(R.id.item_title);
                view.setTag(holder);
            }else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            SubjectInfo subjectInfo = subjectInfos.get(position);
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + subjectInfo.getUrl())
                    .error(R.mipmap.ic_default)
                    .into(holder.item_img);

            holder.item_title.setText(subjectInfo.getDes());
            return view;
        }
    }




}
