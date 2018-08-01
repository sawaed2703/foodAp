package com.sawaedaib.aibrahemsawaed.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sawaedaib.aibrahemsawaed.myapplication.LoginTools.LoginActivity;
import com.sawaedaib.aibrahemsawaed.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    Button btnChangePassword, btnDeleteAccount, btnSignOut;
    EditText etpass_old,etNew_pass,etpassre;
    private ProgressBar progressBar;



    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        btnChangePassword = view.findViewById(R.id.btnSave);
        btnDeleteAccount = view.findViewById(R.id.btn_delete);
        btnSignOut = view.findViewById(R.id.btn_signOut);
        etpass_old = view.findViewById(R.id.et_old_pass);
        etNew_pass= view.findViewById(R.id.et_newpass1);
        etpassre = view.findViewById(R.id.et_new_pass2);
        progressBar = view.findViewById(R.id.progressBar);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            // this listener will be called when there is change in firebase user session
                FirebaseAuth.getInstance().signOut();
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            // user auth state is changed - user is null
                            // launch login activity
                            startActivity(new Intent(getContext(), LoginActivity.class));

                        }
                    }
                };
            }
        });



        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!checkPassword()){
                final String newPassword = etNew_pass.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.updatePassword(newPassword.trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Password is updated!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Failed to update password!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }else {
                    Toast.makeText(getContext(), "pass error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPassword() {

        if (etNew_pass.getText().toString().trim() != etpassre.getText().toString().trim()){
            return false;
        }
        else return true;

    }
}
