package com.sawaedaib.aibrahemsawaed.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder > {

    private Context mContext;
    private List<Upload>mUploads;

    public ImagesAdapter (Context context, List<Upload>uploads){
        mContext = context;
        mUploads = uploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {
        final Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText("food name :"+uploadCurrent.getFood());
        holder.tvCity.setText("city : "+uploadCurrent.getCity().toString());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "open :"+ uploadCurrent.getStart_hour() + " to :"+ uploadCurrent.getEnd_hour(), Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar.make(holder.itemView,"open :"+ uploadCurrent.getStart_hour() + " to :"+ uploadCurrent.getEnd_hour(),Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        holder.tvCatgory.setText("Phone Number : " + uploadCurrent.getPhone());
       // Picasso.with(mContext).load(img).into(holder.imageView);
        String uri =
            "https://firebasestorage.googleapis.com/v0/b/myapp-8b063.appspot.com/o/User%2FUser%2Faib%20Sa%3A%201532782755756?alt=media&token=d4b91c52-060e-44ed-ba7b-566a3d4b867f";
        Picasso.with(mContext).load(uploadCurrent.getImageUri())
                .fit().centerInside().into(holder.imageView);
        //StorageReference filePath = FirebaseStorage.getInstance().getReference("User");
        //Picasso.with(mContext).load(String.valueOf(filePath.getDownloadUrl())).into(holder.imageView);
      // Toast.makeText(mContext, uploadCurrent.getImageUri(), Toast.LENGTH_SHORT).show();
       // StorageReference filePath = FirebaseStorage.getInstance().getReference("User").child("User").

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView imageView;
        public TextView tvCity;
        public TextView tvCatgory;
        public ImageViewHolder(View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.text_view_city);
            tvCatgory = itemView.findViewById(R.id.tvCatogory);
            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}
