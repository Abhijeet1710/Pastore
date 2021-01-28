package com.example.pastore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pastore.sp.Sp;

public class MainActivity3 extends AppCompatActivity {

    MainActivity2 mainActivity2 = new MainActivity2();

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor ed1;

    Sp s = new Sp();

    int position;

    EditText title1, desc1, username1, password1;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        position = intent.getIntExtra("position" , 0);

        title1 = findViewById(R.id.ettitle);
        desc1 = findViewById(R.id.etdesc);
        username1 = findViewById(R.id.etusername);
        password1 = findViewById(R.id.etpassword);
        img1 = findViewById(R.id.img);

        String current = s.MYPREFERENCES+String.valueOf(position);
        sharedPreferences1 = getSharedPreferences(current, MODE_PRIVATE);
        ed1 = sharedPreferences1.edit();

        String title = sharedPreferences1.getString(s.TITLE, "");
        String desc = sharedPreferences1.getString(s.DESCRIPTION, "");
        String username = sharedPreferences1.getString(s.USERNAME, "");
        String password = sharedPreferences1.getString(s.PASSWORD, "");
        String img = sharedPreferences1.getString(s.IMG, "null");

        title1.setText(title);
        desc1.setText(desc);
        username1.setText(username);
        password1.setText(password);
        setImg(img);


    }

    public void setImg(String img) {
        Context context = MainActivity3.this;

        switch (img){
            case "google": img1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.google));
                break;
            case "facebook": img1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fb));
                break;
            case "linkedin": img1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.linkedin));
                break;
            case "whatsapp": img1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.whatsapp));
                break;
            default:  img1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fb_msgr));
        }
    }

    public void funBack(View v) { checkShowDlgAndUpdate(); }

    @Override
    public void onBackPressed() {
        checkShowDlgAndUpdate();
    }

    public void checkShowDlgAndUpdate() {

        String title = title1.getText().toString().trim();
        String desc = desc1.getText().toString().trim();
        String username = username1.getText().toString().trim();
        String password = password1.getText().toString().trim();

        String title2 = sharedPreferences1.getString(s.TITLE, "");
        String desc2 = sharedPreferences1.getString(s.DESCRIPTION, "");
        String username2 = sharedPreferences1.getString(s.USERNAME, "");
        String password2 = sharedPreferences1.getString(s.PASSWORD, "");


        if(!title.equals(title2) || !desc.equals(desc2) || !username.equals(username2) || !password.equals(password2)) {

            builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.updateordiscarddialog, null);
            builder.setView(view);
            dialog = builder.create();
            dialog.show();
            Button btnCancal = dialog.findViewById(R.id.btnCancal);
            Button btnExit = dialog.findViewById(R.id.btnExit);

            btnCancal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainIntent = new Intent(MainActivity3.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            });

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    funUpdate();
                    Intent mainIntent = new Intent(MainActivity3.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            });

        }else{
            Intent mainIntent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(mainIntent);
        }

    }

    public void funUpdate() {

        String title = title1.getText().toString().trim();
        String desc = desc1.getText().toString().trim();
        String username = username1.getText().toString().trim();
        String password = password1.getText().toString().trim();

        if(title.isEmpty() || title == "" || desc.isEmpty() || desc == "" || username.isEmpty() || username == "" || password.isEmpty() || password == "" ){

            Toast.makeText(MainActivity3.this, "Please Fill all the field's", Toast.LENGTH_LONG).show();

        }else {

            ed1.putString(s.TITLE, title);
            ed1.putString(s.DESCRIPTION, desc);
            ed1.putString(s.USERNAME, username);
            ed1.putString(s.PASSWORD, password);

            String img = mainActivity2.getSiteName(title, desc);
            setImg(img);
            ed1.putString(s.IMG, img);

            ed1.commit();
            Toast.makeText(MainActivity3.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

        }

    }


}