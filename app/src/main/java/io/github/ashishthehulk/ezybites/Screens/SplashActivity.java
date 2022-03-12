package io.github.ashishthehulk.ezybites.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import io.github.ashishthehulk.ezybites.MainActivity;
import io.github.ashishthehulk.ezybites.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();

        startThread();



    }


    private void startThread() {

        Thread thread = new Thread(){
            public void run(){

                try{
                    sleep(3000);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if(auth.getCurrentUser()!=null)
                    {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("frag",2);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        thread.start();

    }


    @Override
    protected void onResume() {
        super.onResume();

        startThread();
    }
}