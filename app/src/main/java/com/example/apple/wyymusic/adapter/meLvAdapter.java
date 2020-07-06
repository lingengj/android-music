package com.example.apple.wyymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apple.wyymusic.R;

import java.util.List;

/**
 * Created by apple on 2019/6/20.
 */

public class meLvAdapter extends BaseAdapter {
    private List<Integer> imgData;
    private List<String> titleData;
    private LayoutInflater mInflater;

    public meLvAdapter(Context context, List<Integer> imgData,List<String> titleData) {
        this.imgData = imgData;
        this.titleData = titleData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titleData.size();
    }

    @Override
    public Object getItem(int position) {
        return titleData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_me_list, null);
            viewHolder.imageView = (TextView) convertView.findViewById(R.id.listview_image);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.listview_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setText(imgData.get(position));
        viewHolder.textView.setText(titleData.get(position).toString());
        return convertView;
    }

    private final class ViewHolder {
        TextView imageView;
        TextView textView;
    }
}
