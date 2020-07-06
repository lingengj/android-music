package com.example.apple.wyymusic.image_load;

/**
 * Created by ASUS on 2019/6/24.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.Target;
import com.example.apple.wyymusic.shape_utils.RoundCornersTransformation;
import com.youth.banner.loader.ImageLoader;

import java.util.concurrent.ExecutionException;

public class GlideImageLoader extends ImageLoader {

    public void circleImage(Context context, Object path, ImageView imageView,int borderRadius) {
        Glide.with(context)
                .load((String) path)
                .transform(new CenterCrop(context),new RoundCornersTransformation(context,borderRadius))
                .into(imageView);
    }

    public static Bitmap getImage(Context context,String url) {
        Bitmap bitmap=null;
        try {
            bitmap=Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load((String) path)
                .transform(new CenterCrop(context),new RoundCornersTransformation(context,10))
                .into(imageView);
    }
}
