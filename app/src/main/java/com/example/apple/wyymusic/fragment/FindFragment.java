package com.example.apple.wyymusic.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.wyymusic.R;
import com.example.apple.wyymusic.image_load.GlideImageLoader;
import com.example.apple.wyymusic.songSheetDetail;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {

    private Banner mBanner;
    private ArrayList<String> images;
    TextView tvSongSheet1,tvSongSheet2,tvSongSheet3,tvSongSheet4,tvSongSheet5,tvSongSheet6;
    ImageView ivSongSheet1,ivSongSheet2,ivSongSheet3,ivSongSheet4,ivSongSheet5,ivSongSheet6;

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        mBanner = (Banner) view.findViewById(R.id.banner);
        ivSongSheet1=(ImageView)view.findViewById(R.id.ivSongSheet1);
        ivSongSheet2=(ImageView)view.findViewById(R.id.ivSongSheet2);
        ivSongSheet3=(ImageView)view.findViewById(R.id.ivSongSheet3);
        ivSongSheet4=(ImageView)view.findViewById(R.id.ivSongSheet4);
        ivSongSheet5=(ImageView)view.findViewById(R.id.ivSongSheet5);
        ivSongSheet6=(ImageView)view.findViewById(R.id.ivSongSheet6);
        tvSongSheet1=(TextView) view.findViewById(R.id.tvSongSheet1);
        tvSongSheet2=(TextView)view.findViewById(R.id.tvSongSheet2);
        tvSongSheet3=(TextView)view.findViewById(R.id.tvSongSheet3);
        tvSongSheet4=(TextView)view.findViewById(R.id.tvSongSheet4);
        tvSongSheet5=(TextView)view.findViewById(R.id.tvSongSheet5);
        tvSongSheet6=(TextView)view.findViewById(R.id.tvSongSheet6);
        //初始化数据
        initData();
        //初始化view
        initView();
        new GlideImageLoader().displayImage(getActivity(),"https://p2.music.126.net/4YS9qGwJ66GjwBroqTfumA==/109951164137790113.jpg",ivSongSheet1);
        new GlideImageLoader().displayImage(getActivity(),"https://p2.music.126.net/qaQMp7Oac7DgbOSWxVvsfQ==/109951164086802512.jpg",ivSongSheet2);
        new GlideImageLoader().displayImage(getActivity(),"https://p1.music.126.net/XoEnosQt7V0rQV49_AbbOg==/109951163809052537.jpg",ivSongSheet3);
        new GlideImageLoader().displayImage(getActivity(),"https://p2.music.126.net/vK-Zcszp7X2PVg4ZHujglg==/109951164062857612.jpg",ivSongSheet4);
        new GlideImageLoader().displayImage(getActivity(),"https://p1.music.126.net/1v_Diy2ZRuwUqayM6DnuQA==/109951163956793571.jpg",ivSongSheet5);
        new GlideImageLoader().displayImage(getActivity(),"https://p2.music.126.net/FyYufJ6GDp5Krqb3ecihow==/109951164001046710.jpg",ivSongSheet6);
        return view;
    }

    private void initView() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setViewPagerIsScroll(true);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setImages(images)
                .start();
    }

    private void initData() {
        images = new ArrayList<>();
        images.add("https://p2.music.126.net/4YS9qGwJ66GjwBroqTfumA==/109951164137790113.jpg");
        images.add("https://p2.music.126.net/qaQMp7Oac7DgbOSWxVvsfQ==/109951164086802512.jpg");
        images.add("https://p1.music.126.net/XoEnosQt7V0rQV49_AbbOg==/109951163809052537.jpg");
        images.add("https://p2.music.126.net/4YS9qGwJ66GjwBroqTfumA==/109951164137790113.jpg");
        images.add("https://p2.music.126.net/qaQMp7Oac7DgbOSWxVvsfQ==/109951164086802512.jpg");
        images.add("https://p1.music.126.net/XoEnosQt7V0rQV49_AbbOg==/109951163809052537.jpg");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Intent intent=new Intent(getActivity(),songSheetDetail.class);
        tvSongSheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2819914042");
                startActivity(intent);
            }
        });
        tvSongSheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2806513246");
                startActivity(intent);
            }
        });
        tvSongSheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2616416061");
                startActivity(intent);
            }
        });
        tvSongSheet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2771748219");
                startActivity(intent);
            }
        });
        tvSongSheet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2708862630");
                startActivity(intent);
            }
        });
        tvSongSheet6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2755549164");
                startActivity(intent);
            }
        });
        ivSongSheet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2819914042");
                startActivity(intent);
            }
        });
        ivSongSheet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2806513246");
                startActivity(intent);
            }
        });
        ivSongSheet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2616416061");
                startActivity(intent);
            }
        });
        ivSongSheet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2771748219");
                startActivity(intent);
            }
        });
        ivSongSheet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2708862630");
                startActivity(intent);
            }
        });
        ivSongSheet6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id","2755549164");
                startActivity(intent);
            }
        });
    }

}
