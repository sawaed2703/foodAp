package com.sawaedaib.aibrahemsawaed.myapplication.LoginTools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sawaedaib.aibrahemsawaed.myapplication.MainActivity;
import com.sawaedaib.aibrahemsawaed.myapplication.R;

import java.util.Arrays;

import static com.google.common.collect.ComparisonChain.start;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset, btnSignAnonymous;
    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Toast.makeText(this, auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();


           // startActivity(new Intent(LoginActivity.this, LoginActivity.class));

        } else{
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .build(),
                    RC_SIGN_IN);



        }



     // return;





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
au
    }

    private void start1() {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
    }

    private void signIn() {

        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);
        btnReset = findViewById(R.id.btn_reset_password);
        btnSignAnonymous = findViewById(R.id.btn_login_anonymous);

        btnSignAnonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });


        auth = FirebaseAuth.getInstance();

        auth = FirebaseAuth.getInstance();



        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build(),

                                        new AuthUI.IdpConfig.EmailBuilder().build(),
                                        new AuthUI.IdpConfig.PhoneBuilder().build()))
                                .build(),
                        RC_SIGN_IN);

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            String email = inputEmail.getText().toString();
                                            final String password = inputPassword.getText().toString();
                                            if (TextUtils.isEmpty(email)) {
                                                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                                                return;
                                            }

                                            if (TextUtils.isEmpty(password)) {
                                                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                                                return;
                                            }


                                            progressBar.setVisibility(View.VISIBLE);

                                            auth.signInWithEmailAndPassword(email, password)
                                                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            progressBar.setVisibility(View.GONE);
                                                            if (!task.isSuccessful()) {
// there was an error
                                                                if (password.length() < 6) {
                                                                    inputPassword.setError(getString(R.string.minimum_password));
                                                                } else {
                                                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                                                }
                                                            } else {
                                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                                finish();
                                                            }
                                                        }
                                                    });
                                            auth = FirebaseAuth.getInstance();


                                        }


});
    }
}


// old logIn methods :>
/**
 *

 auth = FirebaseAuth.getInstance();

 if (auth.getCurrentUser() != null) {
 startActivity(new Intent(LoginActivity.this, MainActivity.class));
 finish();
 }

 inputEmail = findViewById(R.id.email);
 inputPassword = findViewById(R.id.password);
 progressBar = findViewById(R.id.progressBar);
 btnSignup = findViewById(R.id.btn_signup);
 btnLogin = findViewById(R.id.btn_login);
 btnReset = findViewById(R.id.btn_reset_password);


 auth = FirebaseAuth.getInstance();




 btnSignup.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
finish();
}
});
 btnReset.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
}
});

 btnLogin.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
String email = inputEmail.getText().toString();
final String password = inputPassword.getText().toString();
if (TextUtils.isEmpty(email)) {
Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
return;
}

if (TextUtils.isEmpty(password)) {
Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
return;
}


progressBar.setVisibility(View.VISIBLE);

auth.signInWithEmailAndPassword(email, password)
.addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {
progressBar.setVisibility(View.GONE);
if (!task.isSuccessful()) {
// there was an error
if (password.length() < 6) {
inputPassword.setError(getString(R.string.minimum_password));
} else {
Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
}
} else {
Intent intent = new Intent(LoginActivity.this, MainActivity.class);
startActivity(intent);
finish();
}
}
});
auth = FirebaseAuth.getInstance();

//  DatabaseReference db= FirebaseDatabase.getInstance().getReference();
//  DatabaseReference users = db.child("Users").child(auth.getCurrentUser().getUid());
//  users.child("Name").setValue("<NAME>");

}


});

 */
