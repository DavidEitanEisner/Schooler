package com.example.schooler;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private GoogleSignInAccount account;
    private Student profile = new Student("null","null","null");
    private Teacher Teacher;

    private ImageView ivUser;
    private ImageButton btnPreviousDay;
    private Button btnToday;
    private ImageButton btnNextDay;

    private int today;
    private int nowScheduleNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Student david = new Student("david","david","david");
        profile = david;
        Classes math = new Classes("math","art_icon");
        david.getMyClasses().add(math);
        math.getTasks().add(new Tasks("task"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        ivUser = findViewById(R.id.ivUser);
        btnPreviousDay = findViewById(R.id.btnPreviousDay);
        btnToday = findViewById(R.id.btnToday);
        btnNextDay = findViewById(R.id.btnNextDay);
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

        //RecyclerView schedule//
        RecyclerView scheduleRecyclerView = findViewById(R.id.rvSchedule);
        RecyclerView.LayoutManager scheduleLayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        scheduleRecyclerView.setLayoutManager(scheduleLayout);
        Format day = new SimpleDateFormat("u");
        String nowDay = day.format(new Date());
        int todayNum;
        switch (nowDay) {
            case "7":
                todayNum = 0;
                break;
            case "1":
                todayNum = 1;
                break;
            case "2":
                todayNum = 2;
                break;
            case "3":
                todayNum = 3;
                break;
            case "4":
                todayNum = 4;
                break;
            default:
                todayNum = 5;
                break;
        }
        today = todayNum;
        nowScheduleNum = todayNum;
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(profile.getMySchedule()[today]);
        scheduleRecyclerView.setAdapter(scheduleAdapter);

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleAdapter newScheduleAdapter;
                newScheduleAdapter = new ScheduleAdapter(profile.getMySchedule()[today]);
                scheduleRecyclerView.setAdapter(newScheduleAdapter);
                nowScheduleNum = today;
            }
        });
        btnPreviousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleAdapter newScheduleAdapter;
                if (nowScheduleNum!=0) {
                    nowScheduleNum--;
                }
                else{
                    nowScheduleNum = 6;
                }
                newScheduleAdapter = new ScheduleAdapter(profile.getMySchedule()[nowScheduleNum]);
                scheduleRecyclerView.setAdapter(newScheduleAdapter);
            }
        });
        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleAdapter newScheduleAdapter;
                if (nowScheduleNum!=6) {
                    nowScheduleNum++;
                }
                else{
                    nowScheduleNum = 0;
                }
                newScheduleAdapter = new ScheduleAdapter(profile.getMySchedule()[nowScheduleNum]);
                scheduleRecyclerView.setAdapter(newScheduleAdapter);
            }
        });

        //RecyclerView Messages//
        RecyclerView messagesRecyclerView = findViewById(R.id.rvMessages);
        RecyclerView.LayoutManager messagesLayout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        messagesRecyclerView.setLayoutManager(messagesLayout);
        MessagesAdapter messagesAdapter = new MessagesAdapter(profile.getMyMessages());
        messagesRecyclerView.setAdapter(messagesAdapter);

        //RecyclerView classes//
        RecyclerView classesRecyclerView = findViewById(R.id.rvClasses);
        RecyclerView.LayoutManager classesLayout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        classesRecyclerView.setLayoutManager(classesLayout);
        ClassesAdapter classesAdapter = new ClassesAdapter(profile.getMyClasses());
        classesAdapter.setOnClassesClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
                int position = viewHolder.getAbsoluteAdapterPosition();
                Classes specClasses = profile.getMyClasses().get(position);
                Intent nextIntent = new Intent(getApplicationContext(),InClassActivity.class);
                nextIntent.putExtra("specClasses",specClasses);
                startActivity(nextIntent);
            }
        });
        classesRecyclerView.setAdapter(classesAdapter);

        //RecyclerView grades//
        RecyclerView gradesRecyclerView = findViewById(R.id.rvGrades);
        RecyclerView.LayoutManager gradesLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true);
        gradesRecyclerView.setLayoutManager(gradesLayout);
        GradesAdapter gradesAdapter = new GradesAdapter(profile.getMyGrades());
        gradesRecyclerView.setAdapter(gradesAdapter);
    }
    private void showMenu(View v){
        PopupMenu menu = new PopupMenu(MainActivity.this,v);
        menu.getMenuInflater().inflate(R.menu.menu, menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.myProf) {
                    startActivity(new Intent(getApplicationContext(), MyProfile.class));
                    return true;
                }
                if (item.getItemId() == R.id.signOut) {
                    gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), SignIn.class));
                        }
                    });
                    return true;
                }
                return true;
            }
        });
        menu.show();
    }
}