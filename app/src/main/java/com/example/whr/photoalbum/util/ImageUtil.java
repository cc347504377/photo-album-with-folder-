package com.example.whr.photoalbum.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.whr.photoalbum.info.ImageInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by whr on 16-12-15.
 */

public class ImageUtil {
    private Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static ContentResolver contentResolver;
    private static ImageUtil imageUtil;

    private ImageUtil() {

    }

    public static ImageUtil getInstance(ContentResolver contentResolver) {
        if (imageUtil == null) {
            ImageUtil.contentResolver = contentResolver;
            imageUtil = new ImageUtil();
        }
        return imageUtil;
    }

    /**
     * 通过ContentResolver获得系统图片集合
     * ContentResolver.query
     * 第一个参数为URI
     * 第二个参数为需要查询的列名
     * 第三个参数为过滤条件
     * 第四个参数为过滤条件辅助，当第三个参数中有‘？’通配符时使用
     * 第五个参数为排序
     * @return
     */
    public Map<String, List<ImageInfo>> getImages() {
        Map<String, List<ImageInfo>> images = new HashMap<>();
        Cursor cursor = contentResolver.query(uri, new String[]{ MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA}
                , null, null, null);
        while (cursor.moveToNext()) {
            String childPath = cursor.getString(1);
            String parentPath = new File(childPath).getParentFile().getName();
            ImageInfo imageInfo = new ImageInfo(cursor.getString(0), childPath, false);
            if (images.get(parentPath) == null) {
                List<ImageInfo> pathlist = new ArrayList<>();
                pathlist.add(imageInfo);

                images.put(parentPath, pathlist);
            }else {
                images.get(parentPath).add(imageInfo);
            }
        }
        cursor.close();
        return images;
    }

}
