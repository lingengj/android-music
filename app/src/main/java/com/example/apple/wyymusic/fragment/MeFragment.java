package com.example.apple.wyymusic.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.wyymusic.R;
import com.example.apple.wyymusic.adapter.meLvAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    TabLayout meTabLayout;
    private List<String> names;
    private List<Integer> imgs;
    ListView lvMe;
    private List<Integer> imgData;
    private List<String> titleData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        lvMe= (ListView) view.findViewById(R.id.lvMe);
        meTabLayout= (TabLayout) view.findViewById(R.id.me_tab_layout);
        setTabs(meTabLayout,inflater,names,imgs);
        meLvAdapter myAdapter = new meLvAdapter(getContext(),imgData,titleData);
        lvMe.setAdapter(myAdapter);
        return view;
    }

    private void setTabs(TabLayout meTabLayout, LayoutInflater inflater, List<String> names,List<Integer> imgs) {
        for (int i = 0; i < names.size(); i++) {
            TabLayout.Tab tab=meTabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_model, null);
            tab.setCustomView(view);
            TextView tvTitle = (TextView) view.findViewById(R.id.model_tab);
            tvTitle.setText(names.get(i));
            TextView imgTab = (TextView) view.findViewById(R.id.img_tab);
            imgTab.setText(imgs.get(i));
            meTabLayout.addTab(tab);
        }
    }

    private void initData() {
        names = new ArrayList<>();
        names.add("小冰电台");
        names.add("驾驶模式");
        names.add("Sati空间");
        names.add("因乐交友");
        names.add("私人FM");
        names.add("私藏推荐");
        names.add("最新电音");
        names.add("亲子频道");
        names.add("古典专区");
        names.add("跑步FM");
        names.add("爵士电台");
        imgs=new ArrayList<>();
        imgs.add(R.string.ice_sucker);
        imgs.add(R.string.car);
        imgs.add(R.string.moon);
        imgs.add(R.string.heart);
        imgs.add(R.string.live_broadcast);
        imgs.add(R.string.collect);
        imgs.add(R.string.cloud);
        imgs.add(R.string.children);
        imgs.add(R.string.song_sheet);
        imgs.add(R.string.run);
        imgs.add(R.string.musical_note);
        imgData=new ArrayList<>();
        imgData.add(R.string.musical_note);
        imgData.add(R.string.play);
        imgData.add(R.string.download);
        imgData.add(R.string.transceiver);
        imgData.add(R.string.collect);
        titleData=new ArrayList<>();
        titleData.add("本地音乐");
        titleData.add("最近播放");
        titleData.add("下载管理");
        titleData.add("我的电台");
        titleData.add("我的收藏");
    }

    public MeFragment() {
        // Required empty public constructor
    }


}
