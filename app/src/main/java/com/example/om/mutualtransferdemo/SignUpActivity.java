package com.example.om.mutualtransferdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ImageView imgPictureSignUpActivity;
    TextView txtFullNameSignUpActivity;
    TextView txtEmailSignUpActivity;
    TextView txtMobileNumberSignUpActivity;
    TextView txtStateSignUpActivity;
    EditText edtFullNameSignUpActivity;
    EditText edtEmailSignUpActivity;
    EditText edtMobileNumberSignUpActivity;
    Button btnNextSignUpActivity;
    Spinner spinnerStateSignUpActivity;
    List<StateName> states;
    public static final String MyPERF = "myper";
    public SharedPreferences sharedPreferences;
    AuthConfig.Builder authCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);

        spinnerStateSignUpActivity = (Spinner) findViewById(R.id.spinnerStateSignUpActivity);

        authCallback =new AuthConfig.Builder();
        authCallback.withAuthCallBack(new AuthCallback() {
            @Override
            public void success(DigitsSession session, String phoneNumber) {
                sharedPreferences = getSharedPreferences(MyPERF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                final String state=spinnerStateSignUpActivity.getSelectedItem().toString();
                editor.putString("phoneNumber",phoneNumber);
                editor.putString("State",state);
                editor.apply();

//                Toast.makeText(SignUpActivity.this,"Success " + phoneNumber,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SignUpActivity.this,EmployDetailSignUpActivity.class);
                intent.putExtra("phoneNumber",phoneNumber);
//                intent.putExtra("name",name);
                startActivity(intent);

                finish();
            }

            @Override
            public void failure(DigitsException error) {
                error.printStackTrace();
                Toast.makeText(SignUpActivity.this,"Fail " + error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Call<List<StateName>> call = AppController.getInstance().getApiInterface().getStateName();
        call.enqueue(new Callback<List<StateName>>() {
            @Override
            public void onResponse(Call<List<StateName>> call, Response<List<StateName>> response) {
                ArrayList<String> temp = new ArrayList<String>();

                states = response.body();
                for (int i = 0; i < states.size(); i++) {
                    temp.add(states.get(i).getState());
                }


                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_item, temp); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStateSignUpActivity.setAdapter(spinnerArrayAdapter);

            }

            @Override
            public void onFailure(Call<List<StateName>> call, Throwable t) {

            }
        });

        imgPictureSignUpActivity = (ImageView) findViewById(R.id.imgPictureSignUpActivity);
        txtFullNameSignUpActivity = (TextView) findViewById(R.id.txtFullNameSignUpActivity);
        txtEmailSignUpActivity = (TextView) findViewById(R.id.txtEmailSignUpActivity);
        txtStateSignUpActivity = (TextView) findViewById(R.id.txtStateSignUpActivity);
        txtMobileNumberSignUpActivity = (TextView) findViewById(R.id.txtMobileNumberSignUpActivity);
        edtFullNameSignUpActivity = (EditText) findViewById(R.id.edtFullNameSignUpActivity);
        edtEmailSignUpActivity = (EditText) findViewById(R.id.edtEmailSignUpActivity);
        edtMobileNumberSignUpActivity = (EditText) findViewById(R.id.edtMobileNumberSignUpActivity);
        btnNextSignUpActivity = (Button) findViewById(R.id.btnNextSignUpActivity);

        SharedPreferences prefs = getSharedPreferences(MyPERF, MODE_PRIVATE);

        String picUrl = prefs.getString("picture", "picture");
        String name = prefs.getString("name", "name");
        String email = prefs.getString("email", "email");
        Picasso.with(SignUpActivity.this).load(picUrl).transform(new CircleTransform()).into(imgPictureSignUpActivity);

        edtFullNameSignUpActivity.setText(name);
        edtEmailSignUpActivity.setText(email);

        btnNextSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("Provide correct information. This can not be edited.");
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if( edtMobileNumberSignUpActivity.getText().toString().isEmpty() && edtMobileNumberSignUpActivity.getText().toString().length()==0)
                        {
                            edtMobileNumberSignUpActivity.setError("please enter your MobileNo.");
                            edtMobileNumberSignUpActivity.requestFocus();
                        }
                        else {
                            Digits.authenticate(authCallback.build());
                        }
                    }
                });
                builder.setNegativeButton("EDIT", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


    }
}

class CircleTransform implements Transformation {
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