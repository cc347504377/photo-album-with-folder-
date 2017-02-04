package com.example.whr.photoalbum.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.whr.photoalbum.R;
import com.example.whr.photoalbum.activity.ImageActivity;

import java.util.List;

/**
 * Created by whr on 16-12-26.
 */

public class ImageParentAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private final LayoutInflater inflater;
    private List<String> imageParent;
    private Context context;
    public ImageParentAdapter(Context context,List<String> imageParent) {
        inflater = LayoutInflater.from(context);
        this.imageParent = imageParent;
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
        viewHolder.textView.setText(imageParent.get(position));
        viewHolder.textView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return imageParent.size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("parentName", ((TextView) v).getText());
        context.startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
