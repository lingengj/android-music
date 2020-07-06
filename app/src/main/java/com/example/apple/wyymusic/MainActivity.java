package com.example.apple.wyymusic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.wyymusic.MusicService.musicPlayerService;
import com.example.apple.wyymusic.adapter.MainFragmentAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView menu,exitLogin;
    Handler handler;
    private final String[] TAB_TITLES = new String[]{"我的","发现","朋友","视频"};
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initWindow();
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_na);
        navigationView = (NavigationView) findViewById(R.id.nav);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        menu= (TextView) findViewById(R.id.main_menu);
        navigationView.setBackgroundColor(getResources().getColor(R.color.white));
        navigationView.setItemIconTintList(null);
        View headerView = navigationView.getHeaderView(0);//获取头布局
        exitLogin= (TextView) headerView.findViewById(R.id.exitLogin);
        final Intent intent=new Intent(this, login.class);
        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                SharedPreferences.Editor editor=getSharedPreferences("status",MODE_PRIVATE).edit();
                editor.putString("logStatus", "unLogin");
                editor.commit();
                startActivity(intent);
                return false;
            }
        });
        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });
        menu.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //item.setChecked(true);
                Toast.makeText(MainActivity.this,item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
        // 初始化页卡
        initPager();
        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.item_main_menu);
                }
                TextView tvTitle = (TextView) tab.getCustomView().findViewById(R.id.txt_tab);
                tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
                tvTitle.setTextSize(16);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.item_main_menu);
                }
                TextView tvTitle = (TextView) tab.getCustomView().findViewById(R.id.txt_tab);
                tvTitle.setTypeface(Typeface.DEFAULT);
                tvTitle.setTextSize(14);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //检测登录状态
        SharedPreferences status=getSharedPreferences("status",MODE_PRIVATE);
        if(status.getString("logStatus","unLogin").equals("unLogin")) {
            startActivity(new Intent(this, login.class));
            finish();
        }
        else
            Toast.makeText(this,"已登录",Toast.LENGTH_LONG).show();

    }


    public void search(View v){
        Intent searchIntent=new Intent(this,search.class);
        startActivity(searchIntent);
    }

    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, String[] tabTitles) {
        for (int i = 0; i < tabTitles.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            tab.setCustomView(view);
            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            if(i==0){
                tvTitle.setTextSize(16);
                tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
            }
            tvTitle.setText(tabTitles[i]);
            tabLayout.addTab(tab);
        }
    }

    private void initPager() {
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // 关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu://点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
        }
    }

    private void initWindow() {//初始化窗口属性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}