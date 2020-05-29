package com.navjot.decemberapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.navjot.decemberapplication.DBHelper;
import com.navjot.decemberapplication.Model.Vegetable;
import com.navjot.decemberapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeFragment extends Fragment  {

   // ArrayList<Vegetable> list,arrayListFiltered,filterlist;
  //  ArrayAdapter<String > adapter;
  //  SearchView searchView;
    List<Vegetable> list;
    EditText etsearchView;
    RecyclerView recyclerView;
    FirestoreRecyclerOptions<Vegetable> options;
    FirestoreRecyclerAdapter<Vegetable,MyViewHolder> adapter;
    FirebaseFirestore firebaseFirestore;
    DBHelper db;
  //  Query query=  firebaseFirestore.collection("vegetables").orderBy("name");


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       View root = inflater.inflate(R.layout.fragment_home, container, false);

      // searchView = root.findViewById(R.id.searchView);
       // etsearchView=root.findViewById(R.id.etsearchView);

        recyclerView=root.findViewById(R.id.VegetableRecyclerView);
        db= new DBHelper(getActivity());
        firebaseFirestore= FirebaseFirestore.getInstance();

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getData();


/*
      etsearchView.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

          }

          @Override
          public void afterTextChanged(Editable s) {

              if (s.toString() != null)
              {
                  getData(s.toString());
              }
              else
              {
                  getData("");
              }

          }
      });
*/
    }


    private void getData() {


        Query query= firebaseFirestore.collection("vegetables").orderBy("name");
        options= new FirestoreRecyclerOptions.Builder<Vegetable>().setQuery(query,Vegetable.class).build();

        adapter= new FirestoreRecyclerAdapter<Vegetable, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Vegetable model) {

                holder.textViewName.setText(model.getName());
                holder.textViewPrice.setText(""+model.getPrice());
                Log.e("Price",""+model.getPrice());
                Picasso.get().load(model.getUrl()).fit().centerCrop().into(holder.imageView);

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item,parent,false);
                return new MyViewHolder(view);
            }
        };


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {

       private ImageView imageView;
       private TextView textViewName,textViewPrice;
       public CheckBox cartcheckbox;
       final ElegantNumberButton elegantNumberButton;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.custom_itemImage);
            textViewName=itemView.findViewById(R.id.vegetableName);
            textViewPrice=itemView.findViewById(R.id.vegetablePrice);
            cartcheckbox=itemView.findViewById(R.id.cartcheckbox);
            elegantNumberButton=itemView.findViewById(R.id.elegantbtn);


            elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                @Override
                public void onClick(View view) {
                  final  String quantity = elegantNumberButton.getNumber();
                    Toast.makeText(getActivity(), ""+quantity, Toast.LENGTH_SHORT).show();

                }
            });


            cartcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (cartcheckbox.isChecked())
                    {
                        final String name= textViewName.getText().toString();
                        final String price= textViewPrice.getText().toString();
                        String qty=elegantNumberButton.getNumber();
                        int pp=Integer.parseInt(price);
                        int qq=Integer.parseInt(qty);
                        int pq=pp * qq;
                        String tprice= String.valueOf(pq);

                        String kg="kg "; //testing
                        String space=" "; //testing


                        boolean b = db.InsertData(name, price,qty,tprice,kg,space);
                        if (b == true)
                        {
                            Toast.makeText(getActivity(), "added to cart, name"+name+" ,price Rs."+price+" ,qty-"+qty+ ",tp Rs."+tprice, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "error in adding to cart", Toast.LENGTH_SHORT).show();

                        }

                    }

                }
            });




        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}