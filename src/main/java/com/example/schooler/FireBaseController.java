package com.example.schooler;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.startActivity;
import static androidx.core.content.ContextCompat.startForegroundService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.Executor;

public class FireBaseController {
    private static FirebaseDatabase DataBase;
    private static DatabaseReference reference;
    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth getFirebaseAuth(){
        if (firebaseAuth == null)
            firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }


    public static FirebaseDatabase getDataBase(){
        if (DataBase == null)
            DataBase = FirebaseDatabase.getInstance();
        return DataBase;
    }
    public static DatabaseReference getDataBaseReference(){
        if (reference == null)
            reference = getDataBase().getReference();
        return reference;
    }


    public static void createNewStudent(String Uid,String userName){
        DataBase = getDataBase();
        DataBase.getReference("users").child(Uid).child("userName").setValue(userName);
    }
    public static void createNewTeacher(Teacher nTec){
        DataBase = getDataBase();
        reference = getDataBaseReference();
        DataBase.getReference("users").child(nTec.getUserName()).child("userName").setValue(nTec.getUserName());
        DataBase.getReference("users").child(nTec.getUserName()).child("email").setValue(nTec.getEmail());
        DataBase.getReference("users").child(nTec.getUserName()).child("phone").setValue(nTec.getPhone());
    }

}
