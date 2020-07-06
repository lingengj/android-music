package com.example.apple.wyymusic.MusicService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.example.apple.wyymusic.model.SongList;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2019/6/25.
 */

public class musicPlayerService extends Service {

    public static final String TAG = "com.example.apple.wyymusic";
    private MyBinder mBinder;
    //标记当前歌曲的序号
    private int i = 0;
    //歌曲路径
    private String url;
    //初始化MediaPlayer
    public MediaPlayer mMediaPlayer;
    List<SongList.BodyBean> listObj;
    SongList.BodyBean song;
    Intent intent;

    public musicPlayerService() {
        intent=new Intent();
        intent.setAction(TAG);
        mBinder = new MyBinder();
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        listObj = (List<SongList.BodyBean>) intent.getSerializableExtra("songlist");
        i=intent.getIntExtra("position",0);
        song=listObj.get(i);
        url=song.getUrl();
        iniMediaPlayerFile();
        return mBinder;
    }

    public class MyBinder extends Binder {
        /**
         * 播放暂停音乐
         */

        public void playPause() {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
                intent.putExtra("status", "start");
                sendBroadcast(intent);
            }
            else {
                mMediaPlayer.pause();
                intent.putExtra("status", "pause");
                sendBroadcast(intent);
            }
        }

        /**
         * reset
         */
        public void resetMusic() {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.reset();
                iniMediaPlayerFile();
            }
        }

        /**
         * 关闭播放器
         */
        public void closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }

        /**
         * 下一首
         */
        public void nextMusic() {
            if (mMediaPlayer != null  && i >= 0) {
                mMediaPlayer.reset();
                i++;
                url=listObj.get(i).getUrl();
                iniMediaPlayerFile();
            }
        }

        /**
         * 上一首
         */
        public void previousMusic() {
            if (mMediaPlayer != null && i > 0) {
                mMediaPlayer.reset();
                i--;
                url=listObj.get(i).getUrl();
                iniMediaPlayerFile();
            }
        }

        /**
         * 获取歌曲长度
         **/
        public int getProgress() {

            return mMediaPlayer.getDuration();
        }

        /**
         * 获取播放位置
         */
        public int getPlayPosition() {
            return mMediaPlayer.getCurrentPosition();
        }
        /**
         * 播放指定位置
         */
        public void seekToPositon(int msec) {
            mMediaPlayer.seekTo(msec);
        }

    }


    /**
     * 添加file文件到MediaPlayer对象并且准备播放音频
     */
    private void iniMediaPlayerFile() {
        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    intent.putExtra("key", "start");
                    mMediaPlayer.start();
                    sendBroadcast(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
