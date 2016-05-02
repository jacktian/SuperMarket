package com.wuyin.supermarket.fragment.base;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.utils.ViewUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private FrameLayout mFrameLayout;

    /**
     * 四种界面
     */
    private View loadView;//加载中的界面
    private View errorView;//加载错误界面
    private View emptyView;//加载空界面
    private View successView;//加载成功的界面

    /**
     * 五种状态
     */
    public static final int STATE_UNKNOE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    public static int STATE = STATE_UNKNOE;

    private Button btnInit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.fragment_home, container, false);
        //framelayout不为空的时候在去new
        if (mFrameLayout == null) {
            mFrameLayout = new FrameLayout(getActivity());
            init();  //在FrameLayout中添加四种不同的布局
        } else {
            //先干掉之前的parent
            ViewUtils.remoteParent(mFrameLayout);
        }
        // showPage();//根据不同的状态显示不同的界面
        show();//根据服务器的数据切换状态

        return mFrameLayout;
    }

    /**
     * 根据服务器的数据切换状态
     */
    private void show() {

        if (STATE == STATE_ERROR || STATE == STATE_EMPTY) {
            STATE = STATE_LOADING;
        }

        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                STATE = result.getValue();
                                showPage();  //状态改变，重新判断当前应该显示的是哪个界面
                            }
                        }
                    });
                }
            }
        }.start();
        //请求服务器，获取服务器上的数据，进行判断
        showPage();

    }


    /**
     * 请求服务器
     * @return
     */
    public abstract LoadResult load();

    /**
     * 创建成功的界面
     * @return
     */
    public abstract View createSuccessView();

    public enum LoadResult {
        error(2), empty(3), success(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    /**
     * 添加四种不同的界面
     */
    private void init() {

        if (loadView == null) {
            loadView = createLoadingView();//创建加载中的界面
            mFrameLayout.addView(loadView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }


        if (errorView == null) {
            errorView = createErrorView();//创建加载失败的界面
            mFrameLayout.addView(errorView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }


        if (emptyView == null) {
            emptyView = createEmptyView();
            mFrameLayout.addView(emptyView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

    }


    /**
     * 创建加载为空的界面
     *
     * @return
     */
    private View createEmptyView() {
        View view = View.inflate(getActivity(), R.layout.empty_page, null);

        return view;
    }

    /**
     * 创建加载失败的view
     *
     * @return
     */
    private View createErrorView() {
        View view = View.inflate(getActivity(), R.layout.error_page, null);
        btnInit = (Button) view.findViewById(R.id.page_bt);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    /**
     * 根据不同的状态显示不同的界面
     * 1、加载中的界面
     * 2、错误界面
     * 3、数据为空的界面
     * 4、请求成功的界面
     */
    private void showPage() {

        if (loadView != null) {
            loadView.setVisibility(STATE == STATE_UNKNOE ||
                    STATE == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(STATE == STATE_ERROR
                    ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(STATE == STATE_EMPTY
                    ? View.VISIBLE : View.INVISIBLE);
        }
        if (STATE == STATE_SUCCESS) {
            successView = createSuccessView();
            if (successView != null) {
                mFrameLayout.addView(successView,
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                // successView.setVisibility(View.VISIBLE);
            }
        }
    }



    /**
     * 创建加载中的界面
     *
     * @return
     */
    private View createLoadingView() {
        View view = View.inflate(getActivity(), R.layout.loading_page, null);

        return view;
    }



}
