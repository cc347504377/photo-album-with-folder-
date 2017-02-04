package com.example.whr.photoalbum.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.whr.photoalbum.R;
import com.example.whr.photoalbum.info.ImageInfo;
import com.example.whr.photoalbum.util.ImageLoader;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by whr on 16-12-27.
 */

public class ImageChildAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private List<ImageInfo> imageParent;
    private Context context;
    private Handler handler = new Handler();
    private final ThreadPoolExecutor executor;
    private ExecutorService service;
    private int i = 0;

    public ImageChildAdapter(Context context, List<ImageInfo> imageParent) {
        inflater = LayoutInflater.from(context);
        this.imageParent = imageParent;
        this.context = context;
        executor = new ThreadPoolExecutor(10, 15, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        service = Executors.newCachedThreadPool();
        executor.allowCoreThreadTimeOut(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.child_item, parent, false);
        return new ImageChildAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ImageChildAdapter.ViewHolder viewHolder = (ImageChildAdapter.ViewHolder) holder;
        viewHolder.imageView.setTag(position);
        service.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", i++ + "");
                ImageLoader.getInstance().disPlayImage(imageParent.get(position).getImagePath(), viewHolder.imageView, 4,
                        ((Activity) context).getWindowManager().getDefaultDisplay().getWidth(), position, handler);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageParent.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
