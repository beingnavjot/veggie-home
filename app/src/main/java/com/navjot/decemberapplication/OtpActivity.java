package com.navjot.decemberapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

//coded by navjot singh
// reference from       https://www.youtube.com/watch?v=JZ8hwzBKsMM
public class OtpActivity extends AppCompatActivity {

    private String verificationId;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        getSupportActionBar().hide();       mAuth= FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.progressbar);
        editText = findViewById(R.id.editTextCode);

         String phonenumber = getIntent().getStringExtra("phone");
        Toast.makeText(OtpActivity.this, "No: "+phonenumber, Toast.LENGTH_SHORT).show();
    //    String username = getIntent().getStringExtra("name");

        sendVerificationCode(phonenumber);
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code =editText.getText().toString().trim();

                if(code.isEmpty() || code.length()<6){

                    editText.setError("Enter valid code...");
                    editText.requestFocus();
                    return;
                }

                //  progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });

    }

    private  void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){




                            Intent intent=new Intent(OtpActivity.this,EnterUsernameandAddressActivity.class);
                            String phonenumber = getIntent().getStringExtra("phone");

                            intent.putExtra("phone2",phonenumber);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
                            startActivity(intent);
                        }else{
                            Toast.makeText(OtpActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


    private  void sendVerificationCode(String phnumber){
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(

                phnumber,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



        //oncodesent will be called when the code is sent
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                editText.setText(code);
                //progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {   //In case the  auto detection did not work

            Toast.makeText(OtpActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };


}