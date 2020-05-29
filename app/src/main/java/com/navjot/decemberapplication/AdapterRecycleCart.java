package com.navjot.decemberapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navjot.decemberapplication.Model.CartVegetables;

import java.util.ArrayList;

public class AdapterRecycleCart extends RecyclerView.Adapter<AdapterRecycleCart.Holder> {


    LayoutInflater inflater;
    Context context;
    ArrayList<CartVegetables> cartVegetablesArrayList;
     int totalPrice=0;

    public AdapterRecycleCart(Context context, ArrayList<CartVegetables> cartVegetablesArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        //  this.images=images;
        this.cartVegetablesArrayList = cartVegetablesArrayList;
    }

    @NonNull
    @Override
    public AdapterRecycleCart.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.custom_cartitem, parent, false);
        Holder h = new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleCart.Holder holder, int position) {
        CartVegetables ob = cartVegetablesArrayList.get(position);
        holder.tvname.setText(ob.getVname());
        holder.tvprice.setText( ob.getVprice());
        holder.tvtprice.setText( ob.getVtprice());
        holder.tvquantity.setText(ob.getVquantity());




        totalPrice =totalPrice+Integer.parseInt(ob.getVtprice());

      //  Toast.makeText(context, "Total Price: Rs."+totalPrice, Toast.LENGTH_LONG).show();

      SharedPreferences spPrice= context.getSharedPreferences("SharedPrefPrice", 0); // 0 - for private mode
        //store value of sp
        SharedPreferences.Editor editor = spPrice.edit();
        editor.putInt("tp",totalPrice);
        editor.apply();
    }

    @Override
    public int getItemCount() {
        //   return 0;
        return cartVegetablesArrayList.size();
    }




    class Holder extends RecyclerView.ViewHolder {
        TextView tvname,tvprice,tvquantity,tvtprice;


        // Context context;

        public Holder(@NonNull final View itemView) {
            super(itemView);
            //  this.context = context;
            tvname = itemView.findViewById(R.id.textViewVegname);
            tvprice = itemView.findViewById(R.id.textViewVegprice);
            tvquantity=itemView.findViewById(R.id.textViewVegquantity);
            tvtprice=itemView.findViewById(R.id.tprice);


//
//       itemView.setOnLongClickListener(new View.OnLongClickListener() {
//           @Override
//           public boolean onLongClick(View v) {
//
//               final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//               builder.setMessage("Are You Sure to delete this item from cart ?");
//               builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
//                   @Override
//                   public void onClick(DialogInterface dialogInterface, int i) {
//
//                       int position= getAdapterPosition();
//
////                       Cursor data= db.getItemID(id);
////                int itemID= -1;
////                while (data.moveToNext())
////                {
////                    itemID=data.getInt(0);
////                }
////                if (itemID >-1)
////                {
//                    cartVegetablesArrayList.remove(position);
//                    db.deleteItem(position);
//                    notifyItemRemoved(position);
//               // }
//
//                   }
//               }).setNegativeButton("Cancel", null);
//
//               AlertDialog dialog = builder.create();
//               dialog.show();
//
//               return true;
//           }
//       });

        }
    }


}






















//
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
////
//                Cursor data= db.getItemID(position);
//                int itemID= -1;
//                while (data.moveToNext())
//                {
//                    itemID=data.getInt(0);
//                }
//                if (itemID >-1)
//                {
//                    db.deleteItem(position);
//                    cartVegetablesArrayList.remove(position);
//                    notifyItemRemoved(position);
//                }
//             //   db.deleteItem(name);
//              //  cartVegetablesArrayList.remove(position);
//                notifyItemRemoved(position);
//
//
//            }
//
//
//        });
//        helper.attachToRecyclerView(recyclerView);