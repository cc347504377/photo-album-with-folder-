package com.example.whr.photoalbum.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.example.whr.photoalbum.info.VideoInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whr on 17-2-6.
 */

public class VideoUtil {
    private static ContentResolver contentResolver1;
    private ContentResolver contentResolver;
    private Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    private static VideoUtil videoUtil;

    private VideoUtil() {

    }

    public static VideoUtil getinstance(ContentResolver contentResolver) {
        if (videoUtil == null) {
            VideoUtil.contentResolver1 = contentResolver;
            videoUtil = new VideoUtil();
        }
        return videoUtil;
    }


    public List<VideoInfo> getVideos() {
        List<VideoInfo> videoInfoList = new ArrayList<>();
        Cursor cursor = contentResolver.query(uri, new String[]{MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA, MediaStore.Video.Media.DURATION}
                , null, null, null);
        VideoInfo videoInfo;
        while (cursor.moveToNext()) {
            videoInfo = new VideoInfo(cursor.getString(0), cursor.getString(1), cursor.getLong(2));
            videoInfoList.add(videoInfo);
        }
        cursor.close();
        return videoInfoList;
    }
}
