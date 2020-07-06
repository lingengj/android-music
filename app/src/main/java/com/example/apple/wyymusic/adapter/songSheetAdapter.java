package com.example.apple.wyymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apple.wyymusic.R;
import com.example.apple.wyymusic.model.SongList;

import java.util.List;

/**
 * Created by ASUS on 2019/6/24.
 */

public class songSheetAdapter extends BaseAdapter {
    private List<SongList.BodyBean> bodyBeenList;
    private LayoutInflater mInflater;

    public songSheetAdapter(Context context, List<SongList.BodyBean> bodyBeenList) {
        this.bodyBeenList = bodyBeenList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bodyBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return bodyBeenList.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_song_sheet_list, null);
            viewHolder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            viewHolder.tvRank = (TextView)convertView.findViewById(R.id.tvRank);
            viewHolder.tvSongName = (TextView)convertView.findViewById(R.id.tvSongName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvRank.setText(position+1+"");
        viewHolder.tvAuthor.setText(bodyBeenList.get(position).getAuthor());
        viewHolder.tvSongName.setText(bodyBeenList.get(position).getTitle());
        return convertView;
    }

    private final class ViewHolder {
        TextView tvSongName,tvAuthor,tvRank;
    }
}