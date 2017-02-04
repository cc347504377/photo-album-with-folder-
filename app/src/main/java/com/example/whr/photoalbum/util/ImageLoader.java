package com.example.whr.photoalbum.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Created by whr on 16-12-27.
 */

public class ImageLoader {
    private static ImageLoader imageloader;
    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        if (imageloader==null)
            imageloader = new ImageLoader();
        return imageloader;
    }

    /**
     *
     * @param path 图片路径
     * @param imageView 显示的View
     * @param spanCount 缩放倍数
     * @param displayW 屏幕宽
     * @param position 位置
     * @param handler 用于异步操作
     */
    public void disPlayImage(final String path, ImageView imageView, int spanCount, int displayW, int position, Handler handler) {
        if (path==null||imageView==null)
            return;
        Bitmap bitmap;
        bitmap = CacheUtil.getCacheChild().getImageFromCache(path);
        int tag = (int) imageView.getTag();
        //判断当前imageView是否可见
        if (bitmap == null) {
            if (tag != position) {
                return;
            }
            bitmap = getThumbnail(path,195);
            if (bitmap!=null)
            CacheUtil.getCacheChild().addImageToCache(path, bitmap);
        }
        if (bitmap == null) {
            return;
        }
        //得到换算后的imageView
        final ImageView newview = imageviewtransform(imageView, displayW, spanCount);
        handler.post(new Runnable() {
            @Override
            public void run() {
                newview.setImageBitmap(CacheUtil.getCacheChild().getImageFromCache(path));
            }
        });
    }

    /**
     * 为了不让封面和内容混淆
     */
    public void disPlayImageP(final String path, final ImageView imageView,int position, Handler handler) {
        final CacheUtil cacheParent = CacheUtil.getCacheParent();
        if (path == null || imageView == null)
            return;
        Bitmap bitmap;
        bitmap = cacheParent.getImageFromCache(path);
        int tag = (int) imageView.getTag();
        //判断当前imageView是否可见
        if (bitmap == null) {
            if (tag != position) {
                return;
            }
            bitmap = getThumbnail(path,360);
            if (bitmap != null)
                cacheParent.addImageToCache(path, bitmap);
        }
        if (bitmap == null) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(cacheParent.getImageFromCache(path));
            }
        });
    }


    private Bitmap getThumbnail(String path,int reqWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options, reqWidth);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path, options);

    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        int width = options.outWidth;
        int simpleSize = 1;
        if ( width > reqWidth) {
            simpleSize = 2;
            while (width / simpleSize > reqWidth) {
                simpleSize *= 2;
            }
        }
        return simpleSize;
    }

    private ImageView imageviewtransform(ImageView imageView, int displayw,int count) {
        imageView.getLayoutParams().width = displayw/count;
        imageView.getLayoutParams().height = displayw/count;
        return imageView;
    }

}
