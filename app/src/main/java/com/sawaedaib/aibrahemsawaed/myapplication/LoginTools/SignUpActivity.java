package com.sawaedaib.aibrahemsawaed.myapplication.LoginTools;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sawaedaib.aibrahemsawaed.myapplication.MainActivity;
import com.sawaedaib.aibrahemsawaed.myapplication.R;

import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail, etUserName, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        auth = FirebaseAuth.getInstance();

        btnSignIn        = findViewById(R.id.sign_in_button);
        btnSignUp        = findViewById(R.id.sign_up_button);
        etUserName       = findViewById(R.id.etUser_name);
        inputEmail       = findViewById(R.id.email);
        inputPassword    = findViewById(R.id.password);
        progressBar      =  findViewById(R.id.progressBar);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        //todo Fix FireBase Ui
      // startActivityForResult(
      //         AuthUI.getInstance()
      //                 .createSignInIntentBuilder()
      //                 .setAvailableProviders(Arrays.asList(
      //                         new AuthUI.IdpConfig.GoogleBuilder().build(),
      //                         new AuthUI.IdpConfig.EmailBuilder().build(),
      //                         new AuthUI.IdpConfig.PhoneBuilder().build()))
      //                 .build(),
      //         RC_SIGN_IN);

       

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = etUserName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUpActivity.this, "Enter Ur Name !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this, "Enter password !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create USer:>

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, "" +
                                            "Authentication failed" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        progressBar.setVisibility(View.GONE);
    }
}
