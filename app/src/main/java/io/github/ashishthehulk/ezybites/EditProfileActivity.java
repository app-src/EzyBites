package io.github.ashishthehulk.ezybites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import io.github.ashishthehulk.ezybites.Screens.AddPostActivity;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView backPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        backPost = findViewById(R.id.backPost);
        backPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                intent.putExtra("frag",2);
                startActivity(intent);
                finish();

            }
        });
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