package com.example.apple.wyymusic.icon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ASUS on 2019/6/21.
 */

@SuppressLint("AppCompatCustomView")
public class FontIconView extends TextView {

    public FontIconView(Context context) {
        super(context);
        init(context);
    }

    public FontIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FontIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        //设置字体图标
        Typeface font = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
        this.setTypeface(font);
    }


}