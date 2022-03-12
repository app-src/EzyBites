package io.github.ashishthehulk.ezybites.Screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.github.ashishthehulk.ezybites.MainActivity;
import io.github.ashishthehulk.ezybites.R;

public class OtpActivity extends AppCompatActivity {

    private String value;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private FirebaseAuth firebaseAuth;
    private String mVerificationId, number;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private EditText otp1, otp2, otp3, otp4, otp5, otp6;
    private TextView txt2;
    private MaterialButton VerifyBtn;
    private  Thread thread;
    private Query query;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("phone");

        }

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        otp1 = findViewById(R.id.input_otp_1);
        otp2 = findViewById(R.id.input_otp_2);
        otp3 = findViewById(R.id.input_otp_3);
        otp4 = findViewById(R.id.input_otp_4);
        otp5 = findViewById(R.id.input_otp_5);
        otp6 = findViewById(R.id.input_otp_6);
        txt2 = findViewById(R.id.txt2);
        VerifyBtn = findViewById(R.id.VerifyBtn);
        number = value.substring(value.length()-10);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try{



                    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                        {

                            signInWithPhoneAuthCredential(phoneAuthCredential);

                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(getApplicationContext(), "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                            super.onCodeSent(s, forceResendingToken);

                            Log.d("OnCodeSent : ", s);

                            mVerificationId = s;
                            forceResendingToken = token;

                            Toast.makeText(getApplicationContext(), "Verification code sent.", Toast.LENGTH_SHORT).show();


                        }
                    };


                    try {
                        startPhoneNumberVerification(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            resendVerificationCode(value, forceResendingToken);
                            Toast.makeText(getApplicationContext(), "Resending verification code.", Toast.LENGTH_SHORT).show();
                        }
                    });


                    moveOtpPosition();




                    VerifyBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {






                            if (TextUtils.isEmpty(otp1.getText().toString().trim())) {

                                otp1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);


                            }
                            else if(TextUtils.isEmpty(otp2.getText().toString().trim())) {


                                otp1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                            }
                            else if(TextUtils.isEmpty(otp3.getText().toString().trim())) {


                                otp3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                            }
                            else if(TextUtils.isEmpty(otp4.getText().toString().trim())) {


                                otp4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                            }
                            else if(TextUtils.isEmpty(otp5.getText().toString().trim())) {


                                otp5.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                            }
                            else if(TextUtils.isEmpty(otp6.getText().toString().trim())) {


                                otp6.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                            }
                            else
                            {


                                String completeOtp = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() +
                                        otp4.getText().toString() + otp5.getText().toString() + otp6.getText().toString();

                                try {
                                    verifyPhoneNumberWithCode(mVerificationId, completeOtp);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(OtpActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }


                        }
                    });

                }catch(Exception e){

                    Toast.makeText(OtpActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }



            }
        };
        thread = new Thread(runnable);
        thread.start();


    }

    private void startPhoneNumberVerification(String phone)
    {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this).setCallbacks(mCallbacks).build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void moveOtpPosition()
    {

        otp1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty())
                {
                    otp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length()>0)
                {
                    otp2.requestFocus();
                }

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            int previous_length = 0;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previous_length = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(previous_length>charSequence.length())
                {
                    otp1.requestFocus();
                }

                else if(!charSequence.toString().trim().isEmpty())
                {
                    otp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length()>0)
                {
                    otp3.requestFocus();
                }

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            int previous_length = 0;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previous_length = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(previous_length>charSequence.length())
                {
                    otp2.requestFocus();
                }
                else if(!charSequence.toString().trim().isEmpty())
                {
                    otp4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length()>0)
                {
                    otp4.requestFocus();
                }
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            int previous_length = 0;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previous_length = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(previous_length>charSequence.length())
                {
                    otp3.requestFocus();
                }
                else if(!charSequence.toString().trim().isEmpty())
                {
                    otp5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.length()>0)
                {
                    otp5.requestFocus();
                }
            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            int previous_length = 0;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previous_length = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(previous_length>charSequence.length())
                {
                    otp4.requestFocus();
                }
                else if(!charSequence.toString().trim().isEmpty())
                {
                    otp6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>0)
                {
                    otp6.requestFocus();
                }
                else if(editable.length()==0)
                {
                    otp4.requestFocus();
                }
            }
        });

        otp6.addTextChangedListener(new TextWatcher() {
            int previous_length = 0;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                previous_length = charSequence.length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(previous_length>charSequence.length())
                {
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==0)
                {
                    otp5.requestFocus();
                }
            }
        });

    }

    private void resendVerificationCode(String phone, PhoneAuthProvider.ForceResendingToken token)
    {

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this).setCallbacks(mCallbacks).
                        setForceResendingToken(token).build();

        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    private void verifyPhoneNumberWithCode(String verificationId, String code)
    {


        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);



    }





    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {

        firebaseAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                updateUi(user);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Toast Error : ",e.getMessage());
                Log.i("Toast Error : ",e.getMessage());
            }
        });




    }


    private void  updateUi(FirebaseUser user)
    {
        String nameTemp= value.substring(value.length()-3);


        HashMap<String, Object> map = new HashMap<>();
        map.put("name","user"+nameTemp);
        map.put("number",number);
        map.put("uid",user.getUid());


        query = firestore.collection("Users").whereEqualTo("number", number);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {

                if(!value.isEmpty())
                {

                    Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                    intent.putExtra("frag",2);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    firestore.collection("Users").document(user.getUid()).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {


                            Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                            intent.putExtra("frag",2);
                            startActivity(intent);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
        });







    }


}