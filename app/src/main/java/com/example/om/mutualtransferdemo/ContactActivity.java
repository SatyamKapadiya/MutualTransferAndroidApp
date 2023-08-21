package com.example.om.mutualtransferdemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactActivity extends AppCompatActivity {

    ImageView imgContact;
    TextView txtNameContact, txtMobileNoContact, txtDepartmentName, txtDesignationName,txtEmailContact;
    Button btnContact;
    private Menu menu;
    private MenuItem item;
    List<PrimaryResult> primaryResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        imgContact = (ImageView) findViewById(R.id.imgContact);
        txtNameContact = (TextView) findViewById(R.id.txtNameContact);
        txtEmailContact= (TextView) findViewById(R.id.txtEmailContact);
        txtMobileNoContact = (TextView) findViewById(R.id.txtMobileNoContact);
        txtDepartmentName = (TextView) findViewById(R.id.txtDepartmentName);
        txtDesignationName = (TextView) findViewById(R.id.txtDesignationName);
        btnContact = (Button) findViewById(R.id.btnContact);

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + txtMobileNoContact.getText().toString()));
                startActivity(callIntent);

            }
        });

        txtMobileNoContact.setText(getIntent().getExtras().getString("phoneNumber"));

        String img=getIntent().getStringExtra("pic");
        Picasso.with(ContactActivity.this).load(img).transform(new CircleTransformpic()).into(imgContact);
        txtNameContact.setText(getIntent().getExtras().getString("name"));
        txtDesignationName.setText(getIntent().getExtras().getString("designation"));
        txtDepartmentName.setText(getIntent().getExtras().getString("department"));
        txtEmailContact.setText(getIntent().getExtras().getString("email"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sharemenu)
        {
//            Toast.makeText(ContactActivity.this, "you select 1st option", Toast.LENGTH_LONG).show();
            Intent sendIntent=new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"This is my text to send....");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
