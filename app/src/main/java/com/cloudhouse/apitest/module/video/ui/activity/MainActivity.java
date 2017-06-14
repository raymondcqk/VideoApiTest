package com.cloudhouse.apitest.module.video.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudhouse.apitest.R;
import com.cloudhouse.apitest.bean.VideoBean;
import com.cloudhouse.apitest.module.video.mvp.presenter.VideoPresenter;
import com.cloudhouse.apitest.module.video.mvp.view.VideoView;
import com.cloudhouse.apitest.net.ApiProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity {

    VideoPresenter mPresenter;

    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard mVideoPlayer;

    @BindView(R.id.textView)
    TextView mTextView;

    VideoView mVideoView = new VideoView() {
        @Override
        public void onLoading() {
            Toast.makeText(MainActivity.this, "发起Json请求", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {
            Toast.makeText(MainActivity.this, "请求完毕", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onSuccess(VideoBean videoBean) {
            mTextView.setText(ApiProvider.BASE_URL +videoBean.getMp4());
            mVideoPlayer.setUp(ApiProvider.BASE_URL +videoBean.getMp4(),JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,videoBean.getTitle());

//            mVideoPlayer.thumbImageView.setImageBitmap();

        }

        @Override
        public void onError(String error) {
            Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onImgLoad(Bitmap bitmap) {
            mVideoPlayer.thumbImageView.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter = new VideoPresenter(this,mVideoView);
        mPresenter.playVideo();







    }
}
