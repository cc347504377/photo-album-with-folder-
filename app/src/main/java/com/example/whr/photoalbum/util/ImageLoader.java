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

    public void disPlayImage(final String path, ImageView imageView, int spanCount, int displayW, int position, Handler handler) {
        Bitmap bitmap;
        bitmap = CacheUtil.getImageFromCache(path);
        int tag = (int) imageView.getTag();
        //判断当前imageView是否可见
        if (bitmap == null) {
            if (tag != position) {
                return;
            }
            bitmap = getthumbnail(path);
            CacheUtil.addImageToCache(path, bitmap);
        }
        if (bitmap == null) {
            return;
        }
        //得到换算后的imageView
        final ImageView newview = imageviewtransform(imageView, displayW, spanCount);
        handler.post(new Runnable() {
            @Override
            public void run() {
                newview.setImageBitmap(CacheUtil.getImageFromCache(path));
            }
        });
    }

    private Bitmap getthumbnail(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateInSampleSize(options, 195);
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

    static class CacheUtil {
        private static LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(1024 * 1024 * 20){
            /**
             * 此方法用于计算图片大小，不同SDK计算方法不同，
             * 必须重新该方法
             * @param key
             * @param bitmap
             * @return
             */
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                    return bitmap.getByteCount();
                }
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };



        public static void addImageToCache(String path, Bitmap bitmap) {
            lruCache.put(path, bitmap);
        }

        public static Bitmap getImageFromCache(String path) {
            return  lruCache.get(path);
        }
    }

}
