package com.example.schooler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<Schedule> daySchedule;
    public ScheduleAdapter(ArrayList<Schedule> Schedule) {this.daySchedule = Schedule;}
    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View scheduleView = inflater.inflate(R.layout.recycleritem_schedule,parent,false);
        return new ViewHolder(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        Schedule schedule = daySchedule.get(position);
        holder.tvNameProf.setText(schedule.getClassName());
        holder.tvHour.setText(schedule.getClassHour()+"");
    }

    @Override
    public int getItemCount() {
        return daySchedule.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameProf;
        private TextView tvHour;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProf = (TextView) itemView.findViewById(R.id.tvNameProf);
            tvHour = (TextView) itemView.findViewById(R.id.tvHour);
        }
    }
}
