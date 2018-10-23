package com.test.exhibitionstask;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.ViewHolder> {
    private List<String> mData;

    public void setData(List<String> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image_view);
        }
    }

    public ImagesRecyclerAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public ImagesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.images_recycler_item, parent,false);
        ImagesRecyclerAdapter.ViewHolder vh = new ImagesRecyclerAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ImagesRecyclerAdapter.ViewHolder holder, int position) {
        Picasso.get().load(mData.get(position)).into(holder.mImageView);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
