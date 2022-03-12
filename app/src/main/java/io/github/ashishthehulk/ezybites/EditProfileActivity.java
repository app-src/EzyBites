package io.github.ashishthehulk.ezybites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import io.github.ashishthehulk.ezybites.Screens.AddPostActivity;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
        intent.putExtra("frag",3);
        startActivity(intent);
        finish();

    }
}