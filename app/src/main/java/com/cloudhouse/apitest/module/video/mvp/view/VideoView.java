package com.cloudhouse.apitest.module.video.mvp.view;

import android.graphics.Bitmap;

import com.cloudhouse.apitest.bean.VideoBean;

/**
 * Created by Chenqk on 2017/6/13. 16:46
 */

public interface VideoView {

    void onLoading();
    void onFinish();
    void onSuccess(VideoBean videoBean);
    void onError(String error);
    void onImgLoad(Bitmap bitmap);
}
