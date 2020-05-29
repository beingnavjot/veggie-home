package com.navjot.decemberapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.navjot.decemberapplication.Model.customer;

public class EnterUsernameandAddressActivity extends AppCompatActivity {

    EditText editTextname;
    Button btnnext;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    SharedPreferences sp1,sp2;

    private FirebaseFirestore firebaseFirestore;

   // String phonenumber = getIntent().getStringExtra("phone2");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_usernameand_address);
        getSupportActionBar().hide();
        editTextname = findViewById(R.id.editTextname);

        btnnext = findViewById(R.id.btnNext);

        mAuth= FirebaseAuth.getInstance();

        firebaseFirestore = firebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        sp2= getSharedPreferences("SharedPref2", MODE_APPEND); // 0 - for private mode
        sp1= getSharedPreferences("SharedPref1", MODE_APPEND); // 0 - for private mode


        //   String phonenumber = getIntent().getStringExtra("phone2");


//        btnnext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                addProfileData();
//
//                Toast.makeText(EnterUsernameandAddressActivity.this,"Profile updated in firebase",Toast.LENGTH_LONG).show();
//            }
//        });


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               addProfileData();
               Toast.makeText(EnterUsernameandAddressActivity.this,"Loading...",Toast.LENGTH_LONG).show();
            }
        });
    }







    public void addProfileData()
    {

        Toast.makeText(EnterUsernameandAddressActivity.this,"addProfiledata() called",Toast.LENGTH_LONG).show();
        String username = editTextname.getText().toString().trim();


        if(!TextUtils.isEmpty(username)  ){

            //String id =databaseReference.push().getKey();
           // String phonenumber= "9999988888";

            String phonenumber=sp1.getString("phoneno1","");
            customer customer= new customer(username,phonenumber);

            //
            // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
               String userID = String.valueOf(mAuth.getCurrentUser());
            //store value of sp1
            SharedPreferences.Editor editor = sp2.edit();
            editor.putString("UID",userID);
            editor.apply();


            firebaseFirestore.collection("users").document(userID).set(customer)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isComplete()){
                              //  Toast.makeText(EnterUsernameandAddressActivity.this, "func called  and data saved", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(EnterUsernameandAddressActivity.this,MainPageActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
                                startActivity(intent);
                            }else {
                                Toast.makeText(EnterUsernameandAddressActivity.this, "error.. func not called      ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


            //   Toast.makeText(ProfileActivity.this,"addProfiledata() COMPLETED ",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(EnterUsernameandAddressActivity.this,"Please Enter username",Toast.LENGTH_LONG).show();
        }
    }






}



