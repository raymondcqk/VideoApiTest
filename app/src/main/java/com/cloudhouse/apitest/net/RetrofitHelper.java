package com.cloudhouse.apitest.net;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chenqk on 2017/6/13. 16:40
 */

public class RetrofitHelper {


    private  static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;


    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());

    /**
     * 然后定义了一个静态方法getInstance用于获取自身RetrofitHelper的实例化，并且只会实例化一次。
     * @param context
     * @return
     */
    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    public RetrofitHelper(Context context) {
        init();
    }

    private void init() {
        resetApp();
    }


    /**
     * Retrofit的创建
     *
     */
    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiProvider.BASE_URL +"/")
                .client(client) //为什么需要okhttp Client？不添加会有什么问题？
                .addConverterFactory(factory)//Gson转换工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * getServer方法就是为了获取RetrofitService接口类的实例化
     *
     * @return
     */
    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
}
