package com.example.intern1project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.intern1project.databinding.ActivityFinalBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class finalActivity extends AppCompatActivity {

    ProgressDialog dialog;
    ActivityFinalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Page is refreshing by Aryan So please wait guys :-)");
        dialog.setCancelable(false);

        DetailFragment details = new DetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearLayout,details);
        transaction.commit();

        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int selected = item.getItemId();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                if(selected == R.id.details)
                {
                    DetailFragment fragment = new DetailFragment();
                    transaction1.replace(R.id.linearLayout,details);
                    transaction1.commit();
                    return true;
                }
                else if(selected == R.id.addData)
                {
                    AddDataFragment addData = new AddDataFragment();
                    transaction1.replace(R.id.linearLayout,addData);
                    transaction1.commit();
                    return true;
                }
                else
                {
                    DeletedDataFragment deleteFragment = new DeletedDataFragment();
                    transaction1.replace(R.id.linearLayout,deleteFragment);
                    transaction1.commit();
                    return true;
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.topMenuitem)
        {
            dialog.show();
            Log.d("Aryan","Turn On");
            Thread thread = new Thread()
            {
              public void run()
              {
                  try {
                      sleep(2000);
                  }
                  catch (Exception e)
                  {
                      Log.d("Aryan",e+" Error is");
                  }
                  finally {
                      Log.d("Aryan","Turn off");
                    dialog.dismiss();
                  }
              }
            };
            thread.start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}