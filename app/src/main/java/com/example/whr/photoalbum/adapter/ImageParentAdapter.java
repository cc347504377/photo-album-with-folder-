package com.example.whr.photoalbum.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whr.photoalbum.MyApplication;
import com.example.whr.photoalbum.R;
import com.example.whr.photoalbum.activity.ImageActivity;
import com.example.whr.photoalbum.info.ParentInfo;
import com.example.whr.photoalbum.util.ImageLoader;

import java.util.List;

/**
 * Created by whr on 16-12-26.
 */

public class ImageParentAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final LayoutInflater inflater;
    private List<ParentInfo> data;
    private Context context;
    private Handler handler = new Handler();
    private int windowWidth = MyApplication.windowWidth;
    public ImageParentAdapter(Context context,List<ParentInfo> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.parent_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ParentInfo info = data.get(position);
        viewHolder.textView.setText(info.getName());
        viewHolder.imageView.setTag(position);
        ImageLoader.getInstance().disPlayImageP(info.getChildPath(), viewHolder.imageView, position, handler);
        viewHolder.imageView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("parentName", data.get((Integer) v.getTag()).getName());
        context.startActivity(intent);
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.parentImage);
            textView = (TextView) itemView.findViewById(R.id.textView);
            cardView = (CardView) itemView.findViewById(R.id.parentLayout);
            cardView.getLayoutParams().height = windowWidth / 2;
        }
    }

}
