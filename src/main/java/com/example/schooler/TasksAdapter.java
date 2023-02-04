package com.example.schooler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private ArrayList<Tasks> classTasks;
    public TasksAdapter(ArrayList<Tasks> classTasks) {this.classTasks = classTasks;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View taskView = inflater.inflate(R.layout.recycleritem_tasks,parent,false);
        return new ViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tasks task = classTasks.get(position);
        holder.tvTaskTitle.setText(task.getTaskName());
        holder.tvGivenDate.setText(task.getGivenDate()+"");
        if(task.getSubmissionDate() != null)
            holder.tvSubmissionDate.setText(task.getSubmissionDate()+"");
        else {
            holder.tvSubmissionDate.setText("no submission");
        }
    }

    @Override
    public int getItemCount() {
        return classTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTaskTitle;
        private TextView tvGivenDate;
        private TextView tvSubmissionDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskTitle = (TextView) itemView.findViewById(R.id.tvTaskTitle);
            tvGivenDate = (TextView) itemView.findViewById(R.id.tvGivenDate);
            tvSubmissionDate = (TextView) itemView.findViewById(R.id.tvSubmissionDate);
        }
    }
}
