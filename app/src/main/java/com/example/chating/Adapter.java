package com.example.chating;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ChatViewHolder> {
    private Context context;
    private ArrayList<Mdel> list;

    public Adapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_item, parent, false);
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ChatViewHolder holder, int position) {
        holder.msg.setText(list.get(position).getMessages());
        holder.timestamp.setText("Time" + " " + list.get(position).getTime_stamp());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView msg, timestamp;

        public ChatViewHolder(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.msginb);
            timestamp = itemView.findViewById(R.id.time);
        }
    }
}
