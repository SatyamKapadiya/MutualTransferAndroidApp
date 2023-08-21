package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class AdapterDemo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<PrimaryResult> primaryResults;

    public AdapterDemo(List<PrimaryResult> primaryResults, Context mContext) {
        this.primaryResults = primaryResults;
        this.mContext = mContext;
    }

    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;
    Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_demo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DemoHolder demoHolder = (DemoHolder) holder;
        demoHolder.txtName.setText(primaryResults.get(position).getName());
        demoHolder.txtDesignation.setText(primaryResults.get(position).getDesignation());
        demoHolder.txtState.setText(primaryResults.get(position).getState());
//        demoHolder.txtEmailContact.setText(primaryResults.get(position).getEmail());

        Picasso.with(this.mContext).load(primaryResults.get(position).getAvatar()).transform(new CircleTransformpic()).into(demoHolder.imgProfilePic);
        demoHolder.txtSerialNumber.setText((position+1)+"/"+primaryResults.size());

        demoHolder.llDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ContactActivity.class);
                String img=primaryResults.get(position).getAvatar();
                intent.putExtra("pic",img);
                String name=primaryResults.get(position).getName();
                intent.putExtra("name",name);
                String designation=primaryResults.get(position).getDesignation();
                intent.putExtra("designation",designation);
                String phoneNumber=primaryResults.get(position).getMobileNumber();
                intent.putExtra("phoneNumber",phoneNumber);
                String department=primaryResults.get(position).getDepartment_name();
                intent.putExtra("department",department);
                String email=primaryResults.get(position).getEmail();
                intent.putExtra("email",email);
                mContext.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {

        return primaryResults.size();
    }

    public class DemoHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDesignation, txtState,txtSerialNumber;
        ImageView imgProfilePic;
        LinearLayout llDemo;

        public DemoHolder(final View itemView) {

            super(itemView);
            txtSerialNumber= (TextView) itemView.findViewById(R.id.txtSerialNumber);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDesignation = (TextView) itemView.findViewById(R.id.txtDesignation);
            txtState = (TextView) itemView.findViewById(R.id.txtState);
//            txtEmailContact= (TextView) itemView.findViewById(R.id.txtEmailContact);
            imgProfilePic = (ImageView) itemView.findViewById(R.id.imgProfilePic);
            llDemo= (LinearLayout) itemView.findViewById(R.id.llDemo);

        }
    }

}

class CircleTransformpic implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}