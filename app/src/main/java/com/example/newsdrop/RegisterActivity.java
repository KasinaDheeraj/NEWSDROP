package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button register;
    private Button login;

    ProgressDialog loading;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText=(EditText) findViewById(R.id.emailinreg);
        passwordEditText=(EditText) findViewById(R.id.passinreg);
        register=findViewById(R.id.registerbutton);
        login=findViewById(R.id.loginreg);

        auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = new ProgressDialog(v.getContext());
                loading.setCancelable(false);
                loading.setMessage("Registering...");
                loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                loading.show();

                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(v.getContext(),"Enter non empty Credentials!",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(v.getContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(v.getContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                loading.dismiss();
                                finish();
                            }else{
                                loading.dismiss();
                                Toast.makeText(v.getContext(),task.getException().getMessage().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),AuthActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }
    public void guestMode(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
