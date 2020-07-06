package com.example.apple.wyymusic;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.apple.wyymusic.MusicService.musicPlayerService;
import com.example.apple.wyymusic.bg_utils.FastBlur;
import com.example.apple.wyymusic.image_load.GlideImageLoader;
import com.example.apple.wyymusic.model.SongList;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class musicPlayer extends Activity {

    private Handler mHandler,handler;

    private musicPlayerService.MyBinder mMyBinder;
//    private MediaService mMediaService;

    private TextView playButton,nextButton,preciousButton,now,title,total,artist;
    private SeekBar mSeekBar;
    //进度条下面的当前进度文字，将毫秒化为m:ss格式
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    //“绑定”服务的intent
    Intent MediaServiceIntent;
    List<SongList.BodyBean> listObj;
    SongList.BodyBean song;
    int position;
    LocationReceiver locationReceiver;
    RelativeLayout rl;
    Bitmap myBitmap;
    Context context;
    String bgUrl;
    ImageView listen_changpian_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        context=this;
        mHandler = new Handler();
        rl=(RelativeLayout)findViewById(R.id.rl);
        listen_changpian_img = (ImageView) findViewById(R.id.listen_changpian_img);
        playButton = (TextView) findViewById(R.id.listen_pause_img);
        nextButton = (TextView) findViewById(R.id.listen_next_img);
        preciousButton = (TextView) findViewById(R.id.listen_back_img);
        mSeekBar = (SeekBar) findViewById(R.id.listen_jindutiao_sb);
        now = (TextView) findViewById(R.id.listen_current_tv);
        total=(TextView)findViewById(R.id.listen_length_tv);
        title=(TextView)findViewById(R.id.listen_title_tv);
        artist=(TextView)findViewById(R.id.listen_artist_tv);
        listObj = (List<SongList.BodyBean>) getIntent().getSerializableExtra("listobj");
        position = getIntent().getIntExtra("position", 0);
        song = listObj.get(position);
        initView();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0)
                    rl.setBackground(new BitmapDrawable(FastBlur.doBlur(myBitmap, 10, true)));
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.music_notify);
                remoteViews.setTextViewText(R.id.tvName,song.getTitle());
                remoteViews.setTextViewText(R.id.tvAuthor,song.getAuthor());
                remoteViews.setImageViewBitmap(R.id.ivCover,myBitmap);
                Notification.Builder notification = new Notification.Builder(context)
                        .setContent(remoteViews)
                        .setSmallIcon(R.mipmap.ic_launcher);
                manager.notify(0, notification.build());
            }
        };
        MediaServiceIntent = new Intent(this, musicPlayerService.class);
        MediaServiceIntent.putExtra("position", position);
        MediaServiceIntent.putExtra("songlist", (Serializable) listObj);
        bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        locationReceiver = new LocationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(musicPlayerService.TAG);
        registerReceiver(locationReceiver, filter);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (musicPlayerService.MyBinder) service;
            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser){
                        mMyBinder.seekToPositon(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void playStatus(View v) {
        switch (v.getId()) {
            case R.id.listen_pause_img:
                mMyBinder.playPause();
                break;
            case R.id.listen_next_img:
                mMyBinder.nextMusic();
                position++;
                song=listObj.get(position);
                initView();
                break;
            case R.id.listen_back_img:
                mMyBinder.previousMusic();
                position--;
                song=listObj.get(position);
                initView();
                break;
        }
    }

    public void exit(View v){
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
        mMyBinder.closeMedia();
        unbindService(mServiceConnection);
        unregisterReceiver(locationReceiver);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mSeekBar.setProgress(mMyBinder.getPlayPosition());
            now.setText(time.format(mMyBinder.getPlayPosition()));
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    public class LocationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            final Animation animation = AnimationUtils.loadAnimation(context, R.anim.image_rotate_animate);
            animation.setInterpolator(new LinearInterpolator());
            String intentAction = intent.getAction();
            String status=intent.getStringExtra("status");
            if (intentAction.equals(musicPlayerService.TAG)&&intent.getStringExtra("key").equals("start")) {
                mHandler.post(mRunnable);
                total.setText(time.format(mMyBinder.getProgress()));
                mSeekBar.setMax(mMyBinder.getProgress());
                listen_changpian_img.startAnimation(animation);
            }
            if(status!=null&&status.equals("start")){
                playButton.setText(R.string.musicPlay);
                listen_changpian_img.startAnimation(animation);
            }
            if(status!=null&&status.equals("pause")){
                playButton.setText(R.string.musicPause);
                listen_changpian_img.clearAnimation();
            }
        }
    }

    public void initView(){
        bgUrl=song.getPic();
        artist.setText(song.getAuthor());
        title.setText(song.getTitle());
        new GlideImageLoader().circleImage(context,bgUrl,listen_changpian_img,125);
        new Thread(new Runnable() {
            @Override
            public void run() {
                myBitmap= GlideImageLoader.getImage(context,bgUrl);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

}
