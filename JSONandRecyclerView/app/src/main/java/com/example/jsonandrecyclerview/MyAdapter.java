package com.example.jsonandrecyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by tim on 1/30/18.
 */

class MyAdapter extends RecyclerView.Adapter {

    private List<ListItem> listItems;

    public MyAdapter(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
       public MyViewHolder(LinearLayout linearLayout) {
            super(linearLayout);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(
                (LinearLayout) LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        TextView textViewHead = holder.itemView.findViewById(R.id.textViewName);
        textViewHead.setText(listItems.get(position).getName());

        TextView textViewDesc = holder.itemView.findViewById(R.id.textViewRealname);
        textViewDesc.setText(listItems.get(position).getRealname());

        new AsyncTask<String, Void, Bitmap>() {
            protected Bitmap doInBackground(String... urls) {
                InputStream is = null;
                try { is = (InputStream) new URL(urls[0]).getContent(); }
                catch (IOException e) { Log.e("MyAdapter", e.getMessage()); }
                return BitmapFactory.decodeStream(is);
            }
            protected void onPostExecute(Bitmap bitmap) {
                ImageView imageView = holder.itemView.findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            }
        }.execute(listItems.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
