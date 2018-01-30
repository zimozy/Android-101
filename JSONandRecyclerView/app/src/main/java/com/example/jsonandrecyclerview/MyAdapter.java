package com.example.jsonandrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textViewHead = holder.itemView.findViewById(R.id.textViewHead);
        textViewHead.setText(listItems.get(position).getHead());

        TextView textViewDesc = holder.itemView.findViewById(R.id.textViewDesc);
        textViewDesc.setText(listItems.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
