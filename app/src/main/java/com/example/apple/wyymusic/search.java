package com.example.apple.wyymusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.apple.wyymusic.adapter.songSheetAdapter;
import com.example.apple.wyymusic.bg_utils.FastBlur;
import com.example.apple.wyymusic.http.HttpUtils;
import com.example.apple.wyymusic.model.SongList;
import com.example.apple.wyymusic.refresh.RefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.R.attr.data;

public class search extends Activity {

    List<SongList.BodyBean> bodyBeenList;
    ListView lvSongSheet;
    Handler handler;
    Context context;
    EditText edtSongName;
    String key;
    String url;
    int num;
    View vHead,vFoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context=this;
        lvSongSheet= (ListView) findViewById(R.id.lvSongSheet);
        edtSongName= (EditText) findViewById(R.id.edtSongName);
        vHead= View.inflate(this, R.layout.list_header, null);
        lvSongSheet.addHeaderView(vHead);
        num=10;
        url="https://api.mlwei.com/music/api/wy/?key=523077333&id=抖音热歌&type=so&cache=0&nu="+num;
        vFoot = LayoutInflater.from(context).inflate(R.layout.load_footer, null);
        final RefreshLayout upRefresh=new RefreshLayout(this,lvSongSheet);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    songSheetAdapter adapter=new songSheetAdapter(context,bodyBeenList);
                    lvSongSheet.setAdapter(adapter);
                }
                if(msg.what==2){
                    upRefresh.setLoading(false);
                }
            }
        };
        searchDeal();
        lvSongSheet.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(upRefresh.canLoadMore()&&key!=null){
                    upRefresh.setLoading(true);
                    num=num+10;
                    url="https://api.mlwei.com/music/api/wy/?key=523077333&id="+key+"&type=so&cache=0&nu="+num;
                    SystemClock.sleep(3000);
                    searchDeal();
                    handler.sendEmptyMessage(2);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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

    public void search(View v){
        key=edtSongName.getText().toString();
        if(key==""||key==null){
            return;
        }
        url="https://api.mlwei.com/music/api/wy/?key=523077333&id="+key+"&type=so&cache=0&nu="+num;
        searchDeal();
        lvSongSheet.addFooterView(vFoot);
    }

    public void searchDeal() {
        HttpUtils.sendOkHttpRequest(url, new Callback() {
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
    }

}
