package com.example.newsdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends Activity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button login;
    private Button signUp;
    ProgressDialog loading = null;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        emailEditText=(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.pass);
        login=findViewById(R.id.loginbutton);
        signUp=findViewById(R.id.signup);

        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loading = new ProgressDialog(v.getContext());
                loading.setCancelable(false);
                loading.setMessage("Authenticating...");
                loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                loading.show();

                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(v.getContext(),"Enter non empty Credentials!",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(v.getContext(),"Login success",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(v.getContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                loading.dismiss();
                                finish();
                            }else{
                                loading.dismiss();
                                Toast.makeText(AuthActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                /*.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(v.getContext(),"Login success",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(v.getContext(),MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            loading.dismiss();
                            finish();
                        }
                    });*/
//                    if(bool==false) {
//                        loading.dismiss();
//                        Toast.makeText(v.getContext(), "Enter Valid Credentials!", Toast.LENGTH_SHORT).show();
//                    }
            }
        }});
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }
    public void guestMode(View v){
        Intent intent=new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
