package com.navjot.decemberapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.navjot.decemberapplication.Model.CartVegetables;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRecycleCart adapterRecycleCart;
    DBHelper db;
    Menu menu;
  //  ArrayList<CartVegetables> cartVegetablesArrayList;
    Button proceed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle("Cart");

        db=new DBHelper(CartActivity.this);
        recyclerView=findViewById(R.id.CartRecyclerView);
       // SharedPreferences spPrice= getSharedPreferences("SharedPrefPrice", 0); // 0 - for private mode   //

       // final String price=spPrice.getString("tp","");

        proceed=findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (adapterRecycleCart.cartVegetablesArrayList.isEmpty())
                {
                    Toast.makeText(CartActivity.this, "cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), ProceedActivity.class);//
                    //   i.putExtra("ttpp",price);//
                    startActivity(i);
                }
            }
        });

        adapterRecycleCart= new AdapterRecycleCart(CartActivity.this,additemsToCartArrayList());


        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        recyclerView.setAdapter(adapterRecycleCart);



    }




    ArrayList<CartVegetables> additemsToCartArrayList() {
        Cursor cs = db.getData();
        ArrayList<CartVegetables> showCartitems = new ArrayList<>();

        if (cs != null) {

            if (cs.moveToFirst()) {
                do {
                    CartVegetables ob =new CartVegetables();
                    String name = cs.getString(cs.getColumnIndex("itemName"));
                    String price = cs.getString(cs.getColumnIndex("itemPrice"));
                    String tprice = cs.getString(cs.getColumnIndex("itemTPrice"));
                    String  quantity = cs.getString(cs.getColumnIndex("itemQty"));
                  //  String  id = cs.getString(cs.getColumnIndex("id"));

                    ob.setVname(name);
                    ob.setVprice(price);
                    ob.setVtprice(tprice);
                    ob.setVquantity(quantity);
                    showCartitems.add(ob);
                } while (cs.moveToNext());
            }
            cs.close();
        }
        return showCartitems;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_clear_cart, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // return super.onOptionsItemSelected(item);
        db.cleancart();
        adapterRecycleCart.notifyDataSetChanged();


        SharedPreferences settings = getSharedPreferences("SharedPrefPrice", 0);
        settings.edit().remove("tp").apply();
//        SharedPreferences.Editor editor = settings.edit();
//        int j=0;
//        editor.putInt("tp",j);
//        editor.apply();

        Intent i= new Intent(getApplicationContext(), MainPageActivity.class);
        startActivity(i);
        Toast.makeText(this, "cart cleared", Toast.LENGTH_SHORT).show();

        return true;
    }



}

























//
//        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//
//              //  CartVegetables ob =new CartVegetables();
//               int position= viewHolder.getAdapterPosition();
////                TextView tvname;
////                tvname = findViewById(R.id.textViewVegname);
////                String name=tvname.getText().toString();
////                Cursor data= db.getItemID(name);
////                int itemID= -1;
////                while (data.moveToNext())
////                {
////                    itemID=data.getInt(0);
////                }
////                if (itemID >-1)
////                {
////                    db.deleteItem(position);
////                    cartVegetablesArrayList.remove(position);
////                    adapterRecycleCart.notifyItemRemoved(position);
////                }
//             //   db.deleteItem(name);
//                cartVegetablesArrayList.remove(position);
//                adapterRecycleCart.notifyItemRemoved(position);
//
//
//            }
//
//
//        });
//        helper.attachToRecyclerView(recyclerView);