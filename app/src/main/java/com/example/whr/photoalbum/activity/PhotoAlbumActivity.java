package com.example.whr.photoalbum.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.whr.photoalbum.info.ImageInfo;
import com.example.whr.photoalbum.info.ParentInfo;
import com.example.whr.photoalbum.util.ImageUtil;
import com.example.whr.photoalbum.R;
import com.example.whr.photoalbum.adapter.ImageParentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PhotoAlbumActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ParentInfo> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoalbum);
        getPermissionRW(this);
        initReView();
    }

    private void initReView() {
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ImageParentAdapter(this, data));
    }

    private void initData() {
        Map<String, List<ImageInfo>> images = ImageUtil.getInstance(getContentResolver()).getImages();
        data = new ArrayList<>();
        Set<String> keySet = images.keySet();
        for (String s : keySet) {
            ParentInfo info = new ParentInfo(s, images.get(s).get(0).getImagePath());
            data.add(info);
        }
    }

    /**
     * 申请权限
     */
    //声明常量权限
    private final static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void getPermissionRW(Activity activity) {
        // 是否添加权限
        int permissionW = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //如果没有权限,申请权限
        if (permissionW != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity, PERMISSIONS_STORAGE, 11
            );
        }
    }
}
