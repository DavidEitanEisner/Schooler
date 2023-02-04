package com.example.schooler;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
public class GradesAdapter extends RecyclerView.Adapter<GradesAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View gradeView = inflater.inflate(R.layout.recycleritem_grade,parent,false);
        return new ViewHolder(gradeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Grades grade = grades.get(i);
        viewHolder.tvProf.setText(grade.getProf());
        viewHolder.tvSubject.setText(grade.getSubject());
    }

    @Override
    public int getItemCount() {return grades.size();}

    private ArrayList<Grades> grades;
    public GradesAdapter(ArrayList<Grades> grades) {
        this.grades = grades;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvGrades;
        public TextView tvSubject;
        public TextView tvProf;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGrades = (TextView) itemView.findViewById(R.id.tvGrades);
            tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
            tvProf = (TextView) itemView.findViewById(R.id.tvProffesion);
        }
    }
}
