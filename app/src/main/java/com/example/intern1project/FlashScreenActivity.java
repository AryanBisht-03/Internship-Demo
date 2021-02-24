package com.example.intern1project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class FlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        getSupportActionBar().hide();

        try{
            Thread thread = new Thread(){
                public void run()
                {
                    try{
                        sleep(4000);
                    }
                    catch (Exception e)
                    {
                        Log.d("Aryan","THere is error in sleep"+e);
                    }
                    finally {
                        startActivity(new Intent(FlashScreenActivity.this,finalActivity.class));
                    }
                }
            };
            thread.start();
        }
        catch (Exception e)
        {
            Log.d("Aryan","THere is error in sleep"+e);
        }


    }
}