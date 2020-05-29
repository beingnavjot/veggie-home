package com.navjot.decemberapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ProceedActivity extends AppCompatActivity {

    TextView total;
    DBHelper db;
    EditText deliveryName,deliveryPhoneno,deliveryAddr,deliveryTown,deliveryCity;
    Button selectPayMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);
        total=findViewById(R.id.total);
        db=new DBHelper(ProceedActivity.this);
        SharedPreferences spPrice= getSharedPreferences("SharedPrefPrice", 0); // 0 - for private mode

        int price=spPrice.getInt("tp",0);
        total.setText("Rs."+price);

        getSupportActionBar().setTitle("Delivery details");

        deliveryName=findViewById(R.id.etDeliveryName);
        deliveryPhoneno=findViewById(R.id.etDeliveryPhoneno);
        deliveryAddr=findViewById(R.id.etDeliveryAddress);
        deliveryTown=findViewById(R.id.etDeliveryTown);
        deliveryCity=findViewById(R.id.etDeliveryCity);


        selectPayMethod=findViewById(R.id.btnselectPayMethod);
        selectPayMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(deliveryName.getText().toString().equals(""))
                {
                    deliveryName.setError("Enter name");
                }
                else  if(deliveryPhoneno.getText().toString().equals(""))
                {
                    deliveryPhoneno.setError("Enter Phone no");
                }
                else if(deliveryAddr.getText().toString().equals(""))
                {
                    deliveryAddr.setError("Enter address");
                }
                else  if(deliveryTown.getText().toString().equals(""))
                {
                    deliveryTown.setError("Enter town or locality");
                }
                else  if(deliveryCity.getText().toString().equals(""))
                {
                    deliveryCity.setError("Enter city");
                }


                else
                {

                    SharedPreferences spDeliveryDetails = getSharedPreferences("SharedPrefDeliveryDetalis", 0); // 0 - for private mode
                    //store value of sp
                    SharedPreferences.Editor editor = spDeliveryDetails.edit();
                    editor.putString("delName", deliveryName.getText().toString());
                    editor.putString("delPhoneno", deliveryPhoneno.getText().toString());
                    editor.putString("delAddr", deliveryAddr.getText().toString());
                    editor.putString("delTown", deliveryTown.getText().toString());
                    editor.putString("delCity", deliveryCity.getText().toString());
                    editor.apply();


                    DBHelper db = new DBHelper(ProceedActivity.this);



                    Intent intent = new Intent(getApplicationContext(), PaymentMethodActivity.class);
                    startActivity(intent);
                }
            }
        });





    }





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK)  return super.onKeyDown(keyCode, event);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked;

                SharedPreferences settings = getSharedPreferences("SharedPrefPrice", 0);
                settings.edit().remove("tp").apply();
//                        SharedPreferences.Editor editor = settings.edit();
//                        int i=0;
//                        editor.putInt("tp",i);
//                        editor.apply();
                db.cleancart();

                Intent intent= new Intent(getApplicationContext(),MainPageActivity.class);
                startActivity(intent);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to cancel the order ?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();





        return super.onKeyDown(keyCode, event);
    }
}
