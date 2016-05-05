package com.wuyin.supermarket;

import android.os.SystemClock;
import android.util.Log;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import com.wuyin.supermarket.http.OkHttpManager;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyin on 2016/5/2.
 */
public class HomeHttpRequest {

    private String url = "http://127.0.0.1:8090/home?index=";
    private String results = "";

    public List<AppInfo> load(int index) {
        //读取本地信息
        loadServer(index);

       /* //如果本地没有
        if (json==null) {
            //请求服务器
            json = loadServer(index);
          *//*  Log.d("HomeHttpRequest", json.toString());
            if (json != null) {
                //保存到本地
                saveLocal(json, index);
            }*//*
        } else {
            Log.d("HomeHttpRequest", "复用了本地缓存");
        }*/
        SystemClock.sleep(1000);
        if (results !=null){
          return  parseJson(results);
        } else {
            return null;
        }
    }

    /**
     * 解析json数据
     *
     *  private String id;
     private String name;
     private String packageName;
     private String iconUrl;
     private float stars;
     private long size;
     private String downloadUrl;
     private String des;
     */
    private List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0 ; i < jsonArray.length() ; i ++){
                JSONObject result = jsonArray.getJSONObject(i);
                String id = result.getString("id");
                String name = result.getString("name");
                String packageName = result.getString("packageName");
                String iconUrl = result.getString("iconUrl");
                float stars = result.getLong("stars");
                long size = result.getLong("size");
                String downLoadUrl = result.getString("downloadUrl");
                String des = result.getString("des");
                AppInfo appInfo = new AppInfo(id,name,packageName,iconUrl,stars,size,downLoadUrl,des);
                appInfos.add(appInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return appInfos;
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
            //bw.write(System.currentTimeMillis() +1000*100 + "");
            bw.write(System.currentTimeMillis()  + "");
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
    private String loadLocal(int index) {
        //如果發現這個文件已經過期了，就不要去服複用緩存了
        File dir = FileUtils.getCacheDir();  //获取缓存文件夹
        File file = new File(dir,"home_" + index);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            long outOfDate = Long.parseLong(br.readLine());
            long currentTime = System.currentTimeMillis();
            //已经过期
            if (currentTime >= outOfDate){
                return null;
            } else {  //没有过期
                String str = null;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null){
                    //写到内存中
                    sw.write(str);
                }
                return sw.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
