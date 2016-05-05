package com.wuyin.supermarket;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.wuyin.supermarket.http.OkHttpManager;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wuyin on 2016/5/2.
 */
public class HomeHttpRequest {

    private String url = "http://127.0.0.1:8090/home?index=";
    private String results = "";

    public void load(int index) {
        //读取本地信息
        String json = loadLocal();

        //如果本地没有
        if (json==null) {
            //请求服务器
            json = loadServer(index);
            Log.d("HomeHttpRequest", json.toString());
            if (json != null) {
                //保存到本地
                saveLocal(json, index);
            }
        }
        if (json !=null){
            parseJson();
        }
    }

    /**
     * 解析json数据
     */
    private void parseJson() {

    }

    /**
     * 数据保存到本地
     * @param json  json字符串
     * @param index  标记
     */
    //1.把整個json數據存儲到本地文件中


    //2、每條數據都取出來，保存到數據庫中

    private void saveLocal(String json, int index) {



        BufferedWriter bw = null;
        try {
            File dir = FileUtils.getCacheDir();
            File file = new File(dir,"home_" + index);//   /mnt/sdcard/googleplay/cache/home_index
            FileWriter fileWriter = new FileWriter(file);
            bw = new BufferedWriter(fileWriter);
            //在第一行寫一個過期時間
            bw.write(System.currentTimeMillis() +1000*100 + "");
            //換行
            bw.newLine();
            bw.write(json);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

    /**
     * 从本地加载数据
     * @return
     */
    private String loadLocal() {
        //如果發現這個文件已經過期了，就不要去服複用緩存了

        return null;
    }


    /**
     * 从服务器端加载数据
     * @param index   下标
     * @return
     */
    public String loadServer(int index) {
        OkHttpManager.getAsync(url+index, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {

                results = result.toString();
            }
        });

        return results;
    }
}
