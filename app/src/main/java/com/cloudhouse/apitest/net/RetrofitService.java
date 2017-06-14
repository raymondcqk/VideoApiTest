package com.cloudhouse.apitest.net;

import com.cloudhouse.apitest.bean.VideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Chenqk on 2017/6/13. 16:33
 */

public interface RetrofitService {

    @GET(ApiProvider.VIDEO_FIND_1)
    Observable<VideoBean> getVideo();
}
