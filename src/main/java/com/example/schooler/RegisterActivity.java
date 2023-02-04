package com.example.schooler;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private EditText UserName;
    private EditText Password;
    private EditText Email;
    private EditText Phone;
    private ProgressBar pbRegistration;
    private Boolean profileType = false;
    private Switch userType;
    private Button btnReg;
    public GoogleSignInOptions gso;
    public GoogleSignInClient gsc;
    public GoogleSignInAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getApplicationContext(),gso);
        account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        UserName = findViewById(R.id.etUserName);
        Password = findViewById(R.id.etPassword);
        Email = findViewById(R.id.etEmail);
        Phone = findViewById(R.id.etPhone);
        userType = findViewById(R.id.sProfileType);
        btnReg = findViewById(R.id.btnReg);
        pbRegistration = findViewById(R.id.pbRegistration);
        UserName.setText(account.getDisplayName());
        Email.setText(account.getEmail());

        userType = findViewById(R.id.sProfileType);
        userType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileType)
                    userType.setText("Student");
                else
                    userType.setText("Teacher");
                profileType = !profileType;
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profileType)
                    FireBaseController.createNewTeacher(new Teacher(UserName.getText().toString(), account.getEmail(), Phone.getText().toString()));
                else
                    createStudent(account.getEmail(),Password.getText().toString());
        }});
    }
    public void createStudent(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            FireBaseController.createNewStudent(task.getResult().getUser().getUid(),firebaseAuth.getCurrentUser().getDisplayName());
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}