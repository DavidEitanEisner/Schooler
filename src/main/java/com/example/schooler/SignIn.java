package com.example.schooler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignIn;
    private Button btnRegister;
    public GoogleSignInOptions gso;
    public GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        btnRegister = findViewById((R.id.btnRegister));
        btnRegister.setOnClickListener(this);
        gso = new GoogleSignInOptions.Builder().requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSignIn)
            startActivity(new Intent(this, MainActivity.class));
        if (view == btnRegister) {
            signIn();
        }
    }
    void signIn(){
        Intent SignInIntent = gsc.getSignInIntent();
        startActivityForResult(SignInIntent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Intent regIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(regIntent);
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }
}