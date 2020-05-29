package com.navjot.decemberapplication.ui.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.navjot.decemberapplication.AdapterRecycleOrderDetails;
import com.navjot.decemberapplication.DBHelper;
import com.navjot.decemberapplication.Model.OrderDetails;
import com.navjot.decemberapplication.R;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterRecycleOrderDetails adapterRecycleOrderDetails;
    DBHelper db;
    Menu menu;
  //  ArrayList<OrderDetails> orderDetailsArrayList;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        db=new DBHelper(getActivity());
        recyclerView=view.findViewById(R.id.OrderDetailsRecyclerView);


        adapterRecycleOrderDetails= new AdapterRecycleOrderDetails(getActivity(),additemsToorderDetailsArrayList());


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterRecycleOrderDetails);


        return view;
    }

    private ArrayList<OrderDetails> additemsToorderDetailsArrayList() {

        Cursor cs = db.getOrderDetails();
        ArrayList<OrderDetails> showOrderDetails = new ArrayList<>();


        if (cs != null) {

            if (cs.moveToFirst()) {
                do {
                    OrderDetails ob =new OrderDetails();
                    String price = cs.getString(cs.getColumnIndex("orderTotalPrice"));
                    String date = cs.getString(cs.getColumnIndex("orderDate"));
                    String time = cs.getString(cs.getColumnIndex("orderTime"));
                    String orderedItems = cs.getString(cs.getColumnIndex("orderedItems"));
                    String orderId = cs.getString(cs.getColumnIndex("orderID"));



                    ob.setOrderTprice(price);
                    ob.setOrderDate(date);
                    ob.setOrderTime(time);
                    ob.setOrderedItems(orderedItems);
                    ob.setOrderId(orderId);


                    showOrderDetails.add(ob);
                } while (cs.moveToNext());
            }
            cs.close();
        }
        return showOrderDetails;

    }
}