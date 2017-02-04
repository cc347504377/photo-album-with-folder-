package com.example.whr.photoalbum.util;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

/**
 * Created by whr on 17-2-4.
 */

public class CacheUtil {

    private static CacheUtil cacheUtilParent;
    private static CacheUtil cacheUtilChild;

    private CacheUtil() {

    }

    public static CacheUtil getCacheParent() {
        if (cacheUtilParent == null) {
            cacheUtilParent = new CacheUtil();
        }
        return cacheUtilParent;
    }

    public static CacheUtil getCacheChild() {
        if (cacheUtilChild == null) {
            cacheUtilChild = new CacheUtil();
        }
        return cacheUtilChild;
    }

    private LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(1024 * 1024 * 20){
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



    public void addImageToCache(String path, Bitmap bitmap) {
        lruCache.put(path, bitmap);
    }

    public Bitmap getImageFromCache(String path) {
        return  lruCache.get(path);
    }
}
