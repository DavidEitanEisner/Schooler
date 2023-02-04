package com.example.schooler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.viewHolder>{
    private ArrayList<Classes> myClasses;
    private Classes specificClasses;
    private View.OnClickListener onClassesClick;
    public ClassesAdapter(ArrayList<Classes> myClasses){
        this.myClasses = myClasses;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View classesView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleritem_classes,viewGroup,false);
        return new viewHolder(classesView);
    }
    public void setOnClassesClick(View.OnClickListener onClassesClick){
        this.onClassesClick = onClassesClick;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Classes classes = myClasses.get(i);
        this.specificClasses = classes;
        viewHolder.tvClassName.setText(classes.getClassName());
        viewHolder.ivIcon.setImageResource(
                viewHolder.ivIcon.getResources().getIdentifier(classes.getIcon(),"drawable",viewHolder.ivIcon.getContext().getPackageName())
        );
    }

    @Override
    public int getItemCount() {
        return myClasses.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView tvClassName;
        public ImageView ivIcon;
        public viewHolder(View itemView){
            super(itemView);
            tvClassName = (TextView) itemView.findViewById(R.id.tvClassName);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            itemView.setTag(this);
            itemView.setOnClickListener(onClassesClick);
        }
    }
}