package com.zcy.ghost.myapplication;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    SubsamplingScaleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (SubsamplingScaleImageView) findViewById(R.id.imageView);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setMinScale(1.0F);//最小显示比例
        imageView.setMaxScale(10.0F);//最大显示比例（太大了图片显示会失真，因为一般微博长图的宽度不会太宽）
        final String testUrl = "http://cache.attach.yuanobao.com/image/2016/10/24/332d6f3e63784695a50b782a38234bb7/da0f06f8358a4c95921c00acfd675b60.jpg";
        final File downDir = Environment.getExternalStorageDirectory();
        //下载图片保存到本地
        Glide.with(this)
                .load(testUrl).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
            }});
    }
}
