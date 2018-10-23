package com.test.exhibitionstask;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.exhibitionstask.model.Exhibit;

import java.util.List;

public class ExhibitsRecyclerViewAdapter extends RecyclerView.Adapter<ExhibitsRecyclerViewAdapter.ViewHolder> {
    private List<Exhibit> mData;
    private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public RecyclerView mImagesRecyclerView;
        public ImagesRecyclerAdapter mImagesRecyclerAdapter;
        public ViewHolder(View view) {
            super(view);
            mTitleTextView = view.findViewById(R.id.text_view_title);
            mImagesRecyclerView = view.findViewById(R.id.images_recycler_view);
            mImagesRecyclerAdapter = null;
        }
    }

    public ExhibitsRecyclerViewAdapter(List<Exhibit> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exhibition_recycler_item, parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (mData.get(position).getTitle() != null) {
            holder.mTitleTextView.setText(mData.get(position).getTitle());
        }
        if (mData.get(position).getImages() != null) {
            if (holder.mImagesRecyclerAdapter == null) {
                holder.mImagesRecyclerAdapter = new ImagesRecyclerAdapter(mData.get(position).getImages());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holder.mImagesRecyclerView.setLayoutManager(linearLayoutManager);
                holder.mImagesRecyclerView.setAdapter(holder.mImagesRecyclerAdapter);
            } else {
                holder.mImagesRecyclerAdapter.setData(mData.get(position).getImages());
            }
            holder.mImagesRecyclerAdapter.notifyDataSetChanged();
        }

        //holder.mImagesRecyclerView.getAdapter().
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
