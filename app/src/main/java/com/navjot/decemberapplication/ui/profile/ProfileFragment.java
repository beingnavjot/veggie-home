package com.navjot.decemberapplication.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.navjot.decemberapplication.AuthenticationActivity;
import com.navjot.decemberapplication.Model.customer;
import com.navjot.decemberapplication.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;
    EditText TVfetchedName;
    TextView TVfetchedNumber;
    private FirebaseAuth mAuth;
    Button btnOK,logout;
    SharedPreferences sp2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        sp2= getActivity().getSharedPreferences("SharedPref2",0); // 0 - for private mode



        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        // mAuth= FirebaseAuth.getInstance();
        TVfetchedName =view.findViewById(R.id.textViewfetchedName);
        TVfetchedNumber = view.findViewById(R.id.textViewfetchedNumber);
        btnOK=view.findViewById(R.id.btnOK);
        logout=view.findViewById(R.id.logout);



//        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setVisibility(View.GONE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are You Sure to Logout ?");
                builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), AuthenticationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                }).setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });


        Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();

        showDataFromFirebase();


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                updateprofiledata();
            }


            private void updateprofiledata() {

                Toast.makeText(getActivity(),"addProfiledata() called",Toast.LENGTH_LONG).show();
                String username = TVfetchedName.getText().toString().trim();
                String usernumber = TVfetchedNumber.getText().toString().trim();


                if(!TextUtils.isEmpty(username)  ){

                    //String id =databaseReference.push().getKey();
                    // String phonenumber= "9999988888";


                    customer customer= new customer(username,usernumber);

                    //
                    // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = String.valueOf(mAuth.getCurrentUser());
                    //store value of sp1
                    SharedPreferences.Editor editor = sp2.edit();
                    editor.putString("UID",userID);
                    editor.apply();


                    firebaseFirestore.collection("users").document(userID).set(customer)
                            .addOnCompleteListener( getActivity(), new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isComplete()){
                                        Toast.makeText(getActivity(), "user profile updated", Toast.LENGTH_SHORT).show();

                                        //   Intent intent=new Intent(getActivity(),MainPageActivity.class);
                                        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
                                        //  startActivity(intent);
                                    }else {
                                        Toast.makeText(getActivity(), "error.. func not called      ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                    //   Toast.makeText(ProfileActivity.this,"addProfiledata() COMPLETED ",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getActivity(),"Please Enter username",Toast.LENGTH_LONG).show();
                }
            }

        });



        return view;
    }









    public void showDataFromFirebase(){

        //  customer customer= new customer(username,phonenumber);

        String userID=sp2.getString("UID","");

        firebaseFirestore.collection("users").document(userID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()){

                            DocumentSnapshot documentSnapshot= task.getResult();


                            String userName = documentSnapshot.getString("name");

                            String userMobileNo = documentSnapshot.getString("phonenumber");


                            Toast.makeText(getActivity(), "Name is "+userName+" Phoneno: "+userMobileNo, Toast.LENGTH_LONG).show();


                            TVfetchedName.setText(userName);
                              TVfetchedNumber.setText(userMobileNo);



                        }else{
                            Toast.makeText(getActivity(),"Error occured while fetching name and phoneno...",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }





}