package com.example.apple.wyymusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.wyymusic.adapter.songSheetAdapter;
import com.example.apple.wyymusic.bg_utils.FastBlur;
import com.example.apple.wyymusic.http.HttpUtils;
import com.example.apple.wyymusic.image_load.GlideImageLoader;
import com.example.apple.wyymusic.model.SongList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class songSheetDetail extends Activity {

    LinearLayout llSongSheet;
    ImageView ivSheetCover,ivAuthor;
    TextView tvSheetName,tvAuthor;
    Bitmap myBitmap;
    Context context;
    Handler handler;
    GlideImageLoader imageLoader;
    String coverUrl,bgUrl,authorUrl;
    List<SongList.BodyBean> bodyBeenList;
    ListView lvSongSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_sheet_detail);
        llSongSheet=(LinearLayout)findViewById(R.id.llSongSheet);
        ivSheetCover= (ImageView) findViewById(R.id.ivSheetCover);
        ivAuthor= (ImageView) findViewById(R.id.ivAuthor);
        tvSheetName= (TextView) findViewById(R.id.tvSheetName);
        tvAuthor= (TextView) findViewById(R.id.tvAuthor);
        lvSongSheet= (ListView) findViewById(R.id.lvSongSheet);
        imageLoader=new GlideImageLoader();
        context=this;
        View vHead= View.inflate(this, R.layout.list_header, null);
        lvSongSheet.addHeaderView(vHead);
        String id=getIntent().getStringExtra("id");
        switch (id){
            case "2819914042":
                coverUrl="https://p2.music.126.net/4YS9qGwJ66GjwBroqTfumA==/109951164137790113.jpg";
                bgUrl="https://p1.music.126.net/m-7Z9JIdaULQ8tPJHht6fA==/109951164137779973.jpg";
                authorUrl="https://p1.music.126.net/U6JAUQBC7XkT_Dwske347g==/109951164141430035.jpg";
                break;
            case "2806513246":
                tvAuthor.setText("Lashara");
                tvSheetName.setText("巨蟹座｜夏月初上，语挚情长");
                coverUrl="https://p2.music.126.net/qaQMp7Oac7DgbOSWxVvsfQ==/109951164086802512.jpg";
                bgUrl="https://p1.music.126.net/7iw_ypWZnet78k7_XRjaHw==/109951163086031971.jpg";
                authorUrl="https://p1.music.126.net/7iw_ypWZnet78k7_XRjaHw==/109951163086031971.jpg";
                break;
            case "2616416061":
                tvAuthor.setText("南方安某人");
                tvSheetName.setText("【节奏控】超带感特别推荐BGM");
                coverUrl="https://p1.music.126.net/XoEnosQt7V0rQV49_AbbOg==/109951163809052537.jpg";
                bgUrl="https://p1.music.126.net/XoEnosQt7V0rQV49_AbbOg==/109951163809052537.jpg";
                authorUrl="https://p1.music.126.net/oszR9SAimR6iaOnZ5jrXcQ==/109951164002318700.jpg";
                break;
            case "2771748219":
                tvAuthor.setText("凛然Asuka");
                tvSheetName.setText("凉城夏日＆在盛夏里，饮一杯清凉纯音水");
                coverUrl="https://p2.music.126.net/vK-Zcszp7X2PVg4ZHujglg==/109951164062857612.jpg";
                bgUrl="https://p2.music.126.net/vK-Zcszp7X2PVg4ZHujglg==/109951164062857612.jpg";
                authorUrl="https://p1.music.126.net/mWgJZxVDg6TSL_ISL6hK-w==/109951164093499074.jpg";
                break;
            case "2708862630":
                tvAuthor.setText("樱桃味音乐");
                tvSheetName.setText("『学霸模式』只要学不死，就往死里学");
                coverUrl="https://p1.music.126.net/1v_Diy2ZRuwUqayM6DnuQA==/109951163956793571.jpg";
                bgUrl="https://p1.music.126.net/5bDS2WzDirYZcecOewNgmg==/109951163732180716.jpg";
                authorUrl="https://p1.music.126.net/UQ7TVG036kyzZH3mLKgvYg==/109951163192255089.jpg";
                break;
            case "2755549164":
                tvAuthor.setText("踿裟");
                tvSheetName.setText("学习用『纯音乐』（欢迎补充）");
                coverUrl="https://p2.music.126.net/FyYufJ6GDp5Krqb3ecihow==/109951164001046710.jpg";
                bgUrl="https://p2.music.126.net/FyYufJ6GDp5Krqb3ecihow==/109951164001046710.jpg";
                authorUrl="https://p1.music.126.net/r2aChooC7zbBU8z7NoAsNA==/109951164136197363.jpg";
                break;
        }
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==0)
                    llSongSheet.setBackground(new BitmapDrawable(FastBlur.doBlur(myBitmap,30,true)));
                if(msg.what==1){
                    songSheetAdapter adapter=new songSheetAdapter(context,bodyBeenList);
                    lvSongSheet.setAdapter(adapter);
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                myBitmap=GlideImageLoader.getImage(context,bgUrl);
                handler.sendEmptyMessage(0);
            }
        }).start();
        imageLoader.circleImage(context,coverUrl,ivSheetCover,2);
        imageLoader.circleImage(context,authorUrl,ivAuthor,15);
        HttpUtils.sendOkHttpRequest("https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=songlist&id="+id, new Callback() {
            @Override public void onFailure(Call call, IOException e) { }
            @Override public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String responseString2 = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseString2);
                    JSONArray songList=jsonObject.getJSONArray("Body");
                    String body=songList.toString();
                    bodyBeenList = gson.fromJson(body,new TypeToken<List<SongList.BodyBean>>(){}.getType());
                    handler.sendEmptyMessage(1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                }
        });
        lvSongSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(context,musicPlayer.class);
                intent.putExtra("listobj",(Serializable)bodyBeenList);
                if(position==0){
                    position=(int)(Math.random() * (lvSongSheet.getAdapter().getCount()-1));
                }
                intent.putExtra("position",position-1);
                startActivity(intent);
            }
        });
    }

    public void exit(View v){
        this.finish();
    }

}
