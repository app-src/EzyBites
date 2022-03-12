package io.github.ashishthehulk.ezybites.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import io.github.ashishthehulk.ezybites.R;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText verifyNumber;
    private MaterialButton sendOtpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firestore = FirebaseFirestore.getInstance();
        try {
            auth = FirebaseAuth.getInstance();
            user = auth.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        verifyNumber = findViewById(R.id.verifyNumber);
        sendOtpBtn = findViewById(R.id.sendOtpBtn);


        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verifyNumber.getEditableText().length()<10)
                {
                    verifyNumber.setError("Invalid Phone Number!");
                    verifyNumber.requestFocus();

                }
                else
                {
                    String number = "+91"+verifyNumber.getEditableText().toString();
                    Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                    intent.putExtra("phone", number);
                    startActivity(intent);
                }

            }
        });




    }
}