package com.wuyin.supermarket.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.utils.UIUtils;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by yinlong on 2016/5/11.
 */
public class DetailSafeHolder  extends BaseHolder<AppInfo>{

    private RelativeLayout safe_layout;
    private LinearLayout safe_content;
    private ImageView safe_arrow;
    ImageView[] ivs;
    ImageView[] iv_des;
    TextView[] tv_des;
    LinearLayout[] des_layout;
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.detail_safe);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        ivs = new ImageView[4]; // 初始化标题栏的图片
        ivs[0] = (ImageView) view.findViewById(R.id.iv_1);
        ivs[1] = (ImageView) view.findViewById(R.id.iv_2);
        ivs[2] = (ImageView) view.findViewById(R.id.iv_3);
        ivs[3] = (ImageView) view.findViewById(R.id.iv_4);
        iv_des = new ImageView[4]; // 初始化每个条目描述的图片
        iv_des[0] = (ImageView) view.findViewById(R.id.des_iv_1);
        iv_des[1] = (ImageView) view.findViewById(R.id.des_iv_2);
        iv_des[2] = (ImageView) view.findViewById(R.id.des_iv_3);
        iv_des[3] = (ImageView) view.findViewById(R.id.des_iv_4);
        tv_des = new TextView[4]; // 初始化每个条目描述的文本
        tv_des[0] = (TextView) view.findViewById(R.id.des_tv_1);
        tv_des[1] = (TextView) view.findViewById(R.id.des_tv_2);
        tv_des[2] = (TextView) view.findViewById(R.id.des_tv_3);
        tv_des[3] = (TextView) view.findViewById(R.id.des_tv_4);

        des_layout = new LinearLayout[4]; // 初始化条目线性布局
        des_layout[0] = (LinearLayout) view.findViewById(R.id.des_layout_1);
        des_layout[1] = (LinearLayout) view.findViewById(R.id.des_layout_2);
        des_layout[2] = (LinearLayout) view.findViewById(R.id.des_layout_3);
        des_layout[3] = (LinearLayout) view.findViewById(R.id.des_layout_4);

       /* LayoutParams layoutParams = safe_content.getLayoutParams();
        layoutParams.height=0;
        safe_content.setLayoutParams(layoutParams);
        safe_arrow.setImageResource(R.mipmap.arrow_down);*/
    }

    @Override
    public void refreshData(AppInfo data) {

    }
}
