package com.cloudhouse.apitest.module.video.mvp.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.cloudhouse.apitest.bean.VideoBean;
import com.cloudhouse.apitest.module.video.mvp.model.VideoModel;
import com.cloudhouse.apitest.module.video.mvp.model.impl.VideoModelImpl;
import com.cloudhouse.apitest.module.video.mvp.view.VideoView;
import com.cloudhouse.apitest.net.ApiProvider;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Chenqk on 2017/6/13. 16:48
 */

public class VideoPresenter {
    private VideoModel mVideoModel;
    private VideoView mVideoView;
    private Context mContext;
    private String mImgUrl;

    public VideoPresenter(Context context,VideoView videoView) {
        mVideoView = videoView;
        mContext = context;
        mVideoModel = new VideoModelImpl(context);
    }

    public void playVideo(){
        //开始访问服务器
        mVideoView.onLoading();

        Observable<VideoBean> observable = mVideoModel.getVideo();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<VideoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(VideoBean videoBean) {
                String videoUrl = ApiProvider.BASE_URL +videoBean.getMp4();
                mImgUrl = ApiProvider.BASE_URL+videoBean.getImg();
                String title = videoBean.getTitle();
                getImg(mContext,mImgUrl);
                mVideoView.onSuccess(videoBean);
            }

            @Override
            public void onError(Throwable e) {
                    mVideoView.onError("网络请求出错");
            }

            @Override
            public void onComplete() {
                    mVideoView.onFinish();
            }
        });
    }

    public void getImg(Context context,String imgUrl){
        Observable<Bitmap> o = mVideoModel.getVideoImg(context, imgUrl);
        o.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mVideoView.onImgLoad(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
