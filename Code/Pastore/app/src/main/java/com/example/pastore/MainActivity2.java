package com.example.pastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pastore.sp.Sp;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;

    Sp s = new Sp();

    EditText title, desc, username, password;
    CheckBox checkBox;
    Spinner spinnersites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) setTheme(R.style.Theme_PastoreNight);

        if(s.DAYMODE){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        title = findViewById(R.id.ettitle);
        desc = findViewById(R.id.etdesc);
        username = findViewById(R.id.etusername);
        password = findViewById(R.id.etpassword);
        checkBox = findViewById(R.id.checkbox);

        spinnersites = findViewById(R.id.spinnersites);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sites, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersites.setAdapter(adapter);

    }

    public void funBack(View view) {
        Intent mainIntent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(mainIntent);
        MainActivity2.this.finish();
    }

    public void funAdd(View view){

        String title1 = title.getText().toString().trim();
        String desc1 = desc.getText().toString().trim();
        String username1 = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        if(title1.isEmpty() || title1 == "" || desc1.isEmpty() || desc1 == "" || username1.isEmpty() || username1 == "" || password1.isEmpty() || password1 == "" ){

            Toast.makeText(MainActivity2.this, "Please Fill all the field's", Toast.LENGTH_LONG).show();

        }else{

            String img = getSiteName(title1, desc1);

            sharedPreferences = getSharedPreferences(s.MYPREFERENCES, MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedPreferences.edit();
            int total = sharedPreferences.getInt(s.TOTAL, 0);
            total += 1;
            ed.putInt(s.TOTAL, total);
            ed.commit();

            String current = s.MYPREFERENCES+String.valueOf(total);
            sharedPreferences1 = getSharedPreferences(current, MODE_PRIVATE);
            SharedPreferences.Editor ed1 = sharedPreferences1.edit();
            ed1.putString(s.TITLE, title1);
            ed1.putString(s.DESCRIPTION, desc1);
            ed1.putString(s.USERNAME, username1);
            ed1.putString(s.PASSWORD, password1);
            ed1.putString(s.IMG, img);

            ed1.commit();

            view.clearFocus();
            Toast.makeText(MainActivity2.this, "Added Successfully", Toast.LENGTH_LONG).show();

            title.getText().clear();
            desc.getText().clear();
            username.getText().clear();
            password.getText().clear();

            funBack(view);

        }

    }

    public String getSiteName(String title, String description) {

        String title1 = title.toLowerCase();

        if(title1.contains("google")) return "google";
        if(title1.contains("facebook")) return "facebook";
        if(title1.contains("linkedin")) return "linkedin";
        if(title1.contains("whatsapp")) return "whatsapp";

        return "null";

    }

//    public void funPassword(View view) {
//        if(checkBox.isChecked()){
//            password.setText(generatePassword(8));
//        }
//    }

    private String generatePassword(int length) {

        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }

        return String.valueOf(password);

    }

    public void funCheckbox(View view) {
        if(checkBox.isChecked()){
            password.setText(generatePassword(8));
        }else{
            password.setText("");
        }
    }
}