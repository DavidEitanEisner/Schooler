package com.example.schooler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    @NonNull
    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messageView = inflater.inflate(R.layout.recycleritem_message,parent,false);
        return new ViewHolder(messageView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.ViewHolder holder, int position) {
        Messages message = messages.get(position);
        holder.tvMessageTitle.setText(message.getMessageTitle());
        holder.tvMessageContent.setText(message.getMessageContent());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private ArrayList<Messages> messages;
    public MessagesAdapter(ArrayList<Messages> messages){this.messages = messages;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMessageTitle;
        private TextView tvMessageContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessageTitle = (TextView) itemView.findViewById(R.id.tvMessageTitle);
            tvMessageContent = (TextView) itemView.findViewById(R.id.tvMessageContent);
        }
    }
}
