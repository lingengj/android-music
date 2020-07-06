package com.example.apple.wyymusic.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by ASUS on 2019/4/23.
 */

public class RefreshLayout extends SwipeRefreshLayout {
    private float downY,upY;
    boolean isLoading=false;
    ListView lvWx;
    public RefreshLayout(Context context, ListView lvWx) {
        super(context);
        this.lvWx=lvWx;
        lvWx.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        upY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public boolean canLoadMore(){
        boolean condition1=downY-upY>0;
        boolean condition2=(lvWx.getAdapter().getCount()-1)==lvWx.getLastVisiblePosition();
        boolean condition3=!isLoading;
        return condition1&&condition2&&condition3;
    }

    public void setLoading(boolean loadState){
        isLoading=loadState;
    }

}
