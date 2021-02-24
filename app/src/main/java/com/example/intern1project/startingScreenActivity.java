package com.example.intern1project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class startingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        getSupportActionBar().hide();

        Thread thread = new Thread(){
            public void run(){

                try{
                    sleep(3000);
                }
                catch (Exception e)
                {
                    Log.d("Aryan","Error occured");
                }
                finally {
                    startActivity(new Intent(startingScreenActivity.this,MainActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}