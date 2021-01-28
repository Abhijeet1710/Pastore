package com.example.pastore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pastore.Model.Model;
import com.example.pastore.adapter.MyRecyclerView;
import com.example.pastore.sp.Sp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;

    Sp s = new Sp();

    static String theme = "light";

    TextView appName;
    ConstraintLayout parent;
    ImageView bulb;
    LinearLayout devider1;
    LinearLayout nothingToShow;

    RecyclerView myRecyclerView;
    private RecyclerView.Adapter MyRecyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = findViewById(R.id.appName);
        parent = findViewById(R.id.parent);
        bulb = findViewById(R.id.bulb);
        devider1 = findViewById(R.id.devider1);
        nothingToShow = findViewById(R.id.nothingToShow);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerViewAdapter = new MyRecyclerView(getApplicationContext(), createList());
        myRecyclerView.setAdapter(MyRecyclerViewAdapter);

    }

    public void funChangeTheme(View view) {

//        if(theme == "dark"){
//            appName.setTextColor(ContextCompat.getColor(this, R.color.black));
//            parent.setBackgroundColor(ContextCompat.getColor(this, R.color.backWhite));
//            bulb.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icons8_idea_32px));
//            devider1.setBackgroundColor(ContextCompat.getColor(this, R.color.fentblack));
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
//            theme = "light";
//
//        }else if(theme == "light"){
//            appName.setTextColor(ContextCompat.getColor(this, R.color.white));
//            parent.setBackgroundColor(ContextCompat.getColor(this, R.color.backBlack));
//            bulb.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icons8_idea_32px_1));
//            devider1.setBackgroundColor(ContextCompat.getColor(this, R.color.fentWhite));
//
//
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.backBlack));
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            theme = "dark";
//
//        }

//        if(theme == "light"){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }

    }

    public ArrayList<Model> createList() {

        sharedPreferences = getSharedPreferences(s.MYPREFERENCES, MODE_PRIVATE);

        ArrayList<Model> list = new ArrayList<>();

        int total = sharedPreferences.getInt(s.TOTAL, 0);
        if(total == 0) {

            nothingToShow.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Nothing inside SharedPreferences", Toast.LENGTH_LONG).show();

        }else{

            for(int i=1; i<=total; i++){

                String current = s.MYPREFERENCES+String.valueOf(i);
                sharedPreferences1 = getSharedPreferences(current, MODE_PRIVATE);

                String title = sharedPreferences1.getString(s.TITLE, "");
                String desc = sharedPreferences1.getString(s.DESCRIPTION, "");
                String username = sharedPreferences1.getString(s.USERNAME, "");
                String password = sharedPreferences1.getString(s.PASSWORD, "");
                String img = sharedPreferences1.getString(s.IMG, "null");

                list.add(new Model(title, desc, username, password, img));
            }

        }
       return list;
    }

    public void funAdd(View view) {

        Intent mainIntent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(mainIntent);
    }

    @Override
    public void onBackPressed() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.exitdialog, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
        Button btnCancal = dialog.findViewById(R.id.btnCancal);
        Button btnExit = dialog.findViewById(R.id.btnExit);

        btnCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.super.onBackPressed();
                finishAffinity();
            }
        });

    }
}