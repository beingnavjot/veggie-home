package com.navjot.decemberapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.navjot.decemberapplication.Model.FinalOrder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class ConfirmOrderActivity extends AppCompatActivity {

    TextView TVname,TVphoneno,TVaddr,TVtown,TVcity,TVmethod,TVtotalprice;
    Button placeOrder;
     String ii;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);


        TVname=findViewById(R.id.tvname);
        TVphoneno=findViewById(R.id.tvphone);
        TVaddr=findViewById(R.id.tvaddr);
        TVtown=findViewById(R.id.tvtown);
        TVcity=findViewById(R.id.tvcity);
        TVmethod=findViewById(R.id.tvmethod);
        TVtotalprice=findViewById(R.id.tvTotalPrice);
        placeOrder=findViewById(R.id.placeOrder);

        firebaseFirestore = firebaseFirestore.getInstance();

        getSupportActionBar().setTitle("Confirm Order");
        Intent intent = getIntent();
       final String method = intent.getStringExtra("method");

        SharedPreferences spDeliveryDetails= getSharedPreferences("SharedPrefDeliveryDetalis", 0); // 0 - for private mode

       final String name=spDeliveryDetails.getString("delName","");
        final   String phoneno=spDeliveryDetails.getString("delPhoneno","");
        final  String addr=spDeliveryDetails.getString("delAddr","");
        final String town=spDeliveryDetails.getString("delTown","");
        final  String city=spDeliveryDetails.getString("delCity","");

        SharedPreferences spPrice= getSharedPreferences("SharedPrefPrice", 0); // 0 - for private mode
          int price=spPrice.getInt("tp",0);
         final String p=String.valueOf(price);





        TVname.setText("Name: "+name);
        TVphoneno.setText("Ph: "+phoneno);
        TVaddr.setText(addr);
        TVtown.setText(town);
        TVcity.setText(city);
        TVmethod.setText(method);
        TVtotalprice.setText("Rs."+price);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ConfirmOrderActivity.this, "order placed", Toast.LENGTH_SHORT).show();
                placeorder();
                //here , make a function to upload orderDetails  to the firebase
               //  uploadOrderDetailstoFirebase();

            }



            private void placeorder()
            {
                final String saveCurrentDate, saveCurrentTime;

                java.util.Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
                saveCurrentDate = currentDate.format(calendar.getTime());
                final String date = saveCurrentDate.toString();

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calendar.getTime());
                final  String time = saveCurrentTime.toString();


                DBHelper db = new DBHelper(ConfirmOrderActivity.this);

              SharedPreferences  sp1= getSharedPreferences("SharedPref1", 0); // 0 - for private mode
                String registeredPhoneno=sp1.getString("phoneno1","").substring(3,9);

                final int min = 100;
                final int max = 900;
                final int random = new Random().nextInt((max - min) + 1) + min;
                String randomm=String.valueOf(random);


                final String orderID = registeredPhoneno + randomm;

////////////////////////////////////////////////    Uploading  data in TABLE2   //////////////////////////////////////////////////

                boolean b = db.InsertOrderDetails(p, date, time,orderID);
                if (b == true) {
                    //   Toast.makeText(getApplicationContext(), "added to orderDB, price" +p+ " ,Date" +date+ " ,time-" +time, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error in adding to orderDB", Toast.LENGTH_SHORT).show();


                }



                db.transferCartData(orderID);


                ///////////////////////////// Uploading  finalorder data to firebase ///////////////////////////////////////////


                Cursor cs = db.getOrderedItemsForUploading(orderID);


                if (cs != null) {

                    if (cs.moveToFirst()) {

                        final String items = cs.getString(cs.getColumnIndex("orderedItems"));


                        FinalOrder finalOrder = new FinalOrder(orderID, p, method, name, phoneno, addr, town, city, date, time, items);



                        firebaseFirestore.collection("orders").document().set(finalOrder)
                                .addOnCompleteListener( new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isComplete()) {
                                            Toast.makeText(ConfirmOrderActivity.this, "Order placed", Toast.LENGTH_SHORT).show();

//                                            Intent intent = new Intent(ConfirmOrderActivity.this, MainPageActivity.class);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
//                                            startActivity(intent);
                                        } else {
                                             Toast.makeText(ConfirmOrderActivity.this, "order not placed ..error occured  ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                    }
                    cs.close();
                }



                ///////////////////////////




                db.cleancart();
               // Toast.makeText(ConfirmOrderActivity.this, "order Placed", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(getApplicationContext(),MainPageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the previous activity
                startActivity(intent);
                finish();


            }

        });



    }




}
