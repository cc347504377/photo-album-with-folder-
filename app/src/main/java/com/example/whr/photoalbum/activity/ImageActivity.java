package com.example.whr.photoalbum.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.whr.photoalbum.R;
import com.example.whr.photoalbum.adapter.ImageChildAdapter;
import com.example.whr.photoalbum.info.ImageInfo;
import com.example.whr.photoalbum.util.ImageUtil;

import java.util.List;
import java.util.Map;

public class ImageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        initdata();
    }

    private void initdata() {
        String parentName = getIntent().getStringExtra("parentName");
        Map<String, List<ImageInfo>> images = ImageUtil.getInstance(this).getImages();
        recyclerView.setAdapter(new ImageChildAdapter(this, images.get(parentName)));
    }
}
