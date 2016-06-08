package com.chat.androidchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        editTextName = (EditText) findViewById(R.id.editTxtName);

        user = mAuth.getCurrentUser();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d("profile","sign in");

                    Toast.makeText(ProfileActivity.this,"Aun sign in welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("profile error","sign out");
                    Toast.makeText(ProfileActivity.this, "Session Cerrada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuth != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signOut(View view) {
        Log.d("profile btn signOut","salir");
        FirebaseAuth.getInstance().signOut();
    }

    public void setProfile(View view) {
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(editTextName.getText().toString())
                .build();

        user.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d("Profile update","User profile updated");
                    Toast.makeText(ProfileActivity.this, "Bienvenido" + user.getDisplayName(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
