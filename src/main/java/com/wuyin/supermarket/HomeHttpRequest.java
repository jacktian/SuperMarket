package com.wuyin.supermarket;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.wuyin.supermarket.http.OkHttpManager;

import java.io.IOException;

/**
 * Created by wuyin on 2016/5/2.
 */
public class HomeHttpRequest {

    private String url = "http://127.0.0.1:8090/home?index=";

    public void load(int index) {
        //读取本地信息
        String json = loadLocal();

        //如果本地没有
        if (json==null) {
            //请求服务器
            json = loadServer(index);
            if (json != null) {
                //保存到本地
                saveLocal(json, index);
            }
        }
        if (json !=null){
            parseJson();
        }
    }

    private void parseJson() {

    }

    private void saveLocal(String json, int index) {

    }

    private String loadLocal() {

        return null;
    }


    public String loadServer(int index) {
        OkHttpManager.getAsync(url+index, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.d("HomeHttpRequest", result.toString());

            }
        });

        return null;
    }
}
