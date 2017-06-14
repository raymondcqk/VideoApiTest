package com.cloudhouse.apitest.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chenqk on 2017/6/13. 16:34
 */

public class VideoBean {

    /**
     * id : 1
     * title : 恐龙快跑
     * imgurl : /myres/bg0.jpg
     * mp4url : /myres/m.mp4
     */

    private String id;
    private String title;
    @SerializedName("imgurl")
    private String img;
    @SerializedName("mp4url")
    private String mp4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }
}
