package com.cloudhouse.apitest.module.video.mvp.model.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.cloudhouse.apitest.bean.VideoBean;
import com.cloudhouse.apitest.module.video.mvp.model.VideoModel;
import com.cloudhouse.apitest.net.RetrofitHelper;
import com.cloudhouse.apitest.net.RetrofitService;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Chenqk on 2017/6/13. 16:44
 *
 * 业务模型实现类
 *
 * 描述： 通过网络获取视频数据
 */

public class VideoModelImpl implements VideoModel{

    private RetrofitService mRetrofitService;

    public VideoModelImpl(Context context) {
        mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    @Override
    public Observable<VideoBean> getVideo() {
        return mRetrofitService.getVideo();
    }

    @Override
    public Observable<Bitmap> getVideoImg(final Context context, final String imgUrl) {

        return Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                Bitmap bitmap = Glide.with(context).load(imgUrl).asBitmap().centerCrop().into(200, 200).get();
                if (bitmap!=null){
                    e.onNext(bitmap);
                    e.onComplete();
                }else {
                    e.onError(new RuntimeException());
                    e.onComplete();
                }

            }
        });

    }


}
