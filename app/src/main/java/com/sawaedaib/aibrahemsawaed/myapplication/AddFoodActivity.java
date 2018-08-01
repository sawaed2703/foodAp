package com.sawaedaib.aibrahemsawaed.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TimeUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

import static android.app.PendingIntent.getActivity;

public class AddFoodActivity extends AppCompatActivity {

 //todo add FireBase
    private StorageReference storageRef;
    private FirebaseAuth auth;
    private static final int SELECT_IMAGE = 1;
    private Spinner spin_catg;
    private ProgressBar mProgressBar;
    private Button btnSave, btnAdd_img;
    private ImageView imageView;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private String city = "",food = "",phone = " ",
            start = " ",end = " " ,address = " ",detailes = " ";

    EditText etCity,etFood,etPhone,et_start, et_end, etAddress,etDetailes;
    String userName  ;
    String strUri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        //addListenerOnButton();
        addListenerOnSpinnerItemSelection();

        btnAdd_img = findViewById(R.id.btnAdd_img);
        imageView = findViewById(R.id.image_food);
        mProgressBar = findViewById(R.id.progressBar1);
        btnSave = findViewById(R.id.btn_save_de);

        et_end = findViewById(R.id.etClose);
        et_start = findViewById(R.id.etOpen);
        etCity = findViewById(R.id.etCity);
        etPhone = findViewById(R.id.etPhoneNum);
        etFood = findViewById(R.id.etFood);
        etAddress = findViewById(R.id.etAddress);
        etDetailes = findViewById(R.id.etDetailes);



        

        //FireBase:>
        //Todo : replace ref to the current user name

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!= null) {
            userName = auth.getCurrentUser().getDisplayName();
            Toast.makeText(this, userName, Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "User Null cannot upload", Toast.LENGTH_SHORT).show();
        }
       // etFood.setText(userName);
            mStorageRef = FirebaseStorage.getInstance().getReference("User");

            mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });







        btnAdd_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });
    }

    private String getFileExtention(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();

        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadData() {
        if (mImageUri != null){

             final StorageReference fileRef = mStorageRef.child("User").child(userName).child(mImageUri.getPath());
            fileRef.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    },5000);

                    Toast.makeText(AddFoodActivity.this, "Upload Success", Toast.LENGTH_LONG).show();


                    if (etToStr()) {
                       try {

                           fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                   strUri = uri.toString();
                                   Upload upload = new Upload(food,String.valueOf(spin_catg.getSelectedItem()) ,strUri, city, address, phone, start, end, detailes);
                                   Toast.makeText(AddFoodActivity.this, strUri, Toast.LENGTH_SHORT).show();

                                   Picasso.with(AddFoodActivity.this).load(taskSnapshot.getUploadSessionUri()).into(imageView);

                                   String uploadId = mDatabaseRef.push().child(userName +" :"+ new Date().toString() ).getKey();
                                   Toast.makeText(AddFoodActivity.this,uploadId , Toast.LENGTH_SHORT).show();

                                   mDatabaseRef.child(uploadId).setValue(upload);

                               }
                           });


                          // String uploadId = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                       }catch (Exception e){
                           Toast.makeText(AddFoodActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                           Toast.makeText(AddFoodActivity.this, "errrrrrr", Toast.LENGTH_SHORT).show();

                      }

                    }//else Toast.makeText(AddFoodActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddFoodActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    mProgressBar.setProgress((int)progress);

                }
            });

        }else {
            Toast.makeText(this, "No data added", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean etToStr() {
        if (!TextUtils.isEmpty(et_end.getText().toString().trim())){
            end = et_end.getText().toString();
        }if (!TextUtils.isEmpty(et_start.getText().toString().trim())){
            start = et_start.getText().toString().trim();
        }if (!TextUtils.isEmpty(etDetailes.getText().toString().trim())){
            detailes = etDetailes.getText().toString().trim();
        }if (!TextUtils.isEmpty(etAddress.getText().toString().trim())){
            address = etAddress.getText().toString().trim();
        }if (!TextUtils.isEmpty(etFood.getText().toString().trim())){
            food = etFood.getText().toString();
        }if (!TextUtils.isEmpty(etCity.getText().toString().trim())){
            city = etCity.getText().toString();
        }if (!TextUtils.isEmpty(etPhone.getText().toString().trim())){
            phone = etPhone.getText().toString();
        }
        return true;
    }

    private void addImage() {
        Intent intent_img = new Intent();
        intent_img.setType("image/*");
        intent_img.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent_img,"select Pic"),SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE){
            if (resultCode == Activity.RESULT_OK){
                if (data != null){
                    try {

                        mImageUri = data.getData();
                      //  imageView.setImageBitmap(bitmap);
                        Picasso.with(this).load(mImageUri).into(imageView);
                    }catch (Exception e){
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }else if (resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addListenerOnSpinnerItemSelection() {
        spin_catg = findViewById(R.id.spin_catg);
        spin_catg.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton(){
        spin_catg = findViewById(R.id.spin_catg);
        btnSave = findViewById(R.id.btn_save_de);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddFoodActivity.this, "OnClickListener : " +
                        "Spinner 1 : "+ String.valueOf(spin_catg.getSelectedItem()) +
                        "No Thing", Toast.LENGTH_LONG).show();
            }
        });

    }
}
