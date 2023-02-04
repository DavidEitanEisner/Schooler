package com.example.schooler;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InClassActivity extends AppCompatActivity{
    private TextView tvInClassName;
    private ImageButton btnBack;
    private RecyclerView rvTasks;

    private Classes specClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class);
        tvInClassName = findViewById(R.id.tvInClassName);
        btnBack = findViewById(R.id.btnBack);
        specClasses = (Classes) getIntent().getSerializableExtra("specClasses");

        tvInClassName.setText(specClasses.getClassName());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //RecyclerView Tasks//
        RecyclerView tasksRecyclerView = findViewById(R.id.rvTasks);
        RecyclerView.LayoutManager tasksLayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        tasksRecyclerView.setLayoutManager(tasksLayout);
        TasksAdapter tasksAdapter = new TasksAdapter(specClasses.getTasks());
        tasksRecyclerView.setAdapter(tasksAdapter);
    }
}