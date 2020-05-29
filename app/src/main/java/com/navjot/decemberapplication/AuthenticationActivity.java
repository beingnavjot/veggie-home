package com.navjot.decemberapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.navjot.decemberapplication.Model.CountryData;

//import java.util.Objects;

public class AuthenticationActivity extends AppCompatActivity {

    Spinner spinner;
    private EditText editTextnumber,editTextname;
    Button btncontinue;
    SharedPreferences sp1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
       // FirebaseApp.initializeApp(this);

        sp1= getSharedPreferences("SharedPref1", MODE_APPEND); // 0 - for private mode

      //  Toast.makeText(this,"TEST",Toast.LENGTH_LONG);
        getSupportActionBar().hide();
        //Objects.requireNonNull(getSupportActionBar()).hide();

        spinner =findViewById(R.id.spinnerCountry);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editTextnumber =findViewById(R.id.editTextMobile);
   //     editTextname= findViewById(R.id.editTextName);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number=editTextnumber.getText().toString();  //.trim;

                if(number.isEmpty()|| number.length() < 10)
                {
                    editTextnumber.setError("Enter valid Mobile Number");
                    editTextnumber.requestFocus();
                    return;
                }
                String phoneNumber ="+"+ code + number;


                //store value of sp1
                SharedPreferences.Editor editor = sp1.edit();
                editor.putString("phoneno1",phoneNumber);
                editor.apply();




                Intent intent1 = new Intent(AuthenticationActivity.this,OtpActivity.class);
                intent1.putExtra("phone",phoneNumber);
                startActivity(intent1);


            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){  //if user is already logged in ,, do not start the authentication activity and start app from MainActivity

            Intent intent=new Intent(this,MainPageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
            startActivity(intent);

        }
    }
}