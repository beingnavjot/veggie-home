package com.navjot.decemberapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.andremion.counterfab.CounterFab;
import com.google.android.material.navigation.NavigationView;

public class MainPageActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    CounterFab fab;
//    FirebaseFirestore firebaseFirestore;
//    DatabaseReference databaseReference;
//    TextView tvfetchedName,tvfetchedPhoneno;

    @Override
    protected void onResume() {
        super.onResume();
       // fab.setCount(new DBHelper(this).getCountCart());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainPageActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });


   //     fab.setCount(new DBHelper(this).getCountCart());
//        firebaseFirestore = FirebaseFirestore.getInstance();
//
//
//
//        databaseReference= FirebaseDatabase.getInstance().getReference("users");
//
//        // mAuth= FirebaseAuth.getInstance();
//        tvfetchedName =findViewById(R.id.fetchedName);
//       tvfetchedPhoneno = findViewById(R.id.fetchedPhoneno);
//
//        showDataFromFirebase();
//


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_orders,
                R.id.nav_TnC, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }












    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_page, menu);
//        return true;
//    }

/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // return super.onOptionsItemSelected(item);



           final AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setMessage("Are You Sure to Logout ?");
           builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {


                   FirebaseAuth.getInstance().signOut();
                   Intent intent = new Intent(MainPageActivity.this, AuthenticationActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
                   finish();

               }
           }).setNegativeButton("Cancel", null);

           AlertDialog dialog = builder.create();
           dialog.show();


       // return super.onOptionsItemSelected(item);
        return  true;

    }*/
}
