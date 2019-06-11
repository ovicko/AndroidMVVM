/*
 * Copyright (c) Amwollo Victor 2019.
 */

package com.ovicko.viewmodelandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ovicko.viewmodelandroid.model.Hit;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.PhotoViewHolder> {

    Context mcontext;
    List<Hit> imageList;

    public ImageAdapter(Context mcontext, List<Hit> imageList) {
        this.mcontext = mcontext;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.image_item, viewGroup, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.PhotoViewHolder photoViewHolder, int position) {
        Hit imageItem = imageList.get(position);
        Glide.with(mcontext)
                .load(imageItem.getPreviewURL())
                .into(photoViewHolder.imageView);
        photoViewHolder.textView.setText(imageItem.getTags());
    }

    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }

    public void setImageAdapter(List<Hit> hits) {
        this.imageList = hits;
        notifyDataSetChanged();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textLikes);
        }
    }
}
