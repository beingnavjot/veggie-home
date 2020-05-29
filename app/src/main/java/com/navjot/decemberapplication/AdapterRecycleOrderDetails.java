package com.navjot.decemberapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navjot.decemberapplication.Model.OrderDetails;

import java.util.ArrayList;

public class AdapterRecycleOrderDetails  extends RecyclerView.Adapter<AdapterRecycleOrderDetails.Holder>{


    LayoutInflater inflater;
    Context context;
   // ArrayList<OrderedVegetables> orderedVegetablesArrayList;
   ArrayList<OrderDetails> orderDetailsArrayList;
    DBHelper db;



    public AdapterRecycleOrderDetails(Context context, ArrayList<OrderDetails> orderDetailsArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        //  this.images=images;
        this.orderDetailsArrayList = orderDetailsArrayList;
    }



    @NonNull
    @Override
    public AdapterRecycleOrderDetails.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.custom_myorders_details, parent, false);
        Holder h = new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleOrderDetails.Holder holder, int position) {
        OrderDetails ob = orderDetailsArrayList.get(position);

        holder.tvprice.setText( ob.getOrderTprice());
        holder.tvdate.setText(ob.getOrderDate());
        holder.tvtime.setText( ob.getOrderTime());
        holder.tvordereditems.setText(ob.getOrderedItems());
        holder.tvid.setText(ob.getOrderId());

    }

    @Override
    public int getItemCount() {
        return orderDetailsArrayList.size();
    }



    class Holder extends RecyclerView.ViewHolder {
        TextView tvprice,tvdate,tvtime,tvordereditems,tvid;


        // Context context;

        public Holder(@NonNull final View itemView) {
            super(itemView);
            //  this.context = context;
            tvprice = itemView.findViewById(R.id.tvprice);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvtime=itemView.findViewById(R.id.tvtime);
            tvordereditems=itemView.findViewById(R.id.tvOrderedItems);
            tvid=itemView.findViewById(R.id.tvid);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Toast.makeText(context, "Date= "+tvdate.getText().toString()+" OrderedItems= "+tvordereditems.getText().toString(), Toast.LENGTH_SHORT).show();
                    return  true;
                }
            });


        }
    }


}
