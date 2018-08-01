package com.sawaedaib.aibrahemsawaed.myapplication;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImagesAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Upload>mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();




        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");

        if (mDatabaseRef.getDatabase().getReference("User") == null){
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);
                    //Toast.makeText(ImagesActivity.this, upload.getImageUri(), Toast.LENGTH_SHORT).show();
                    mUploads.add(upload);
                }
                    try {
                        mAdapter = new ImagesAdapter(ImagesActivity.this, mUploads);
                        mRecyclerView.setAdapter(mAdapter);

                    }catch (Exception e){
                        Toast.makeText(ImagesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
