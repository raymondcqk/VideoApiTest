package com.cloudhouse.apitest.module.video.mvp.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.cloudhouse.apitest.bean.VideoBean;

import io.reactivex.Observable;

/**
 * Created by Chenqk on 2017/6/13. 16:43
 *
 * 业务模型类
 *
 * 描述： 获取 视频数据
 */

public interface VideoModel {

    Observable<VideoBean> getVideo();
    Observable<Bitmap> getVideoImg(Context context,String imgUrl);
}
