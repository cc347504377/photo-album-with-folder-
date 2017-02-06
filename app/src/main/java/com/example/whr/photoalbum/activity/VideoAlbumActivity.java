package com.example.whr.photoalbum.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.whr.photoalbum.R;
import com.example.whr.photoalbum.adapter.ImageParentAdapter;
import com.example.whr.photoalbum.adapter.VideoAdapter;
import com.example.whr.photoalbum.info.ImageInfo;
import com.example.whr.photoalbum.info.ParentInfo;
import com.example.whr.photoalbum.info.VideoInfo;
import com.example.whr.photoalbum.util.ImageUtil;
import com.example.whr.photoalbum.util.VideoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VideoAlbumActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<VideoInfo> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_album);

        findViewById(R.id.recyclerView);
    }

    private void initReView() {
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new VideoAdapter(this, videos));
    }

    private void initData() {
        videos = VideoUtil.getinstance(getContentResolver()).getVideos();
    }
}
