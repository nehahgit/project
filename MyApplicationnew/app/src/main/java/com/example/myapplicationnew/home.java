package com.example.myapplicationnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationnew.databinding.ActivityHomeBinding;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    
    CardView cprofile,cmatstatus,cinstitution,cinstrument;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.bringToFront();


        cprofile=findViewById(R.id.cardView);
        cmatstatus=findViewById(R.id.cardView2);
        cinstitution=findViewById(R.id.cardView3);
        cinstrument=findViewById(R.id.cardView4);

        cprofile.setOnClickListener(this);
        cmatstatus.setOnClickListener(this);
        cinstitution.setOnClickListener(this);
        cinstrument.setOnClickListener(this);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id= item.getItemId();


        if(R.id.nav_home==id){
            Intent in=new Intent(getApplicationContext(),home.class);
            startActivity(in);
        }
        else if(R.id.profile==id){
            Intent in=new Intent(getApplicationContext(),user_view_profile.class);
            startActivity(in);
        }
        else if (R.id.institute==id){
            Intent in=new Intent(getApplicationContext(),view_institution.class);
            startActivity(in);
        }
        else if (R.id.inst_req==id){
            Intent in=new Intent(getApplicationContext(),view_insti_request_status.class);
            startActivity(in);
        }
        else if (R.id.shop==id){
            Intent in=new Intent(getApplicationContext(),view_shop.class);
            startActivity(in);
        }
        else if (R.id.view_materialrequest==id){
            Intent in=new Intent(getApplicationContext(),view_material_request_status.class);
            startActivity(in);
        }
        else if (R.id.view_instrumentrequest==id){
            Intent in=new Intent(getApplicationContext(),view_instrument_request_status.class);
            startActivity(in);
        }
        else if (R.id.sendapprating==id){
            Intent in=new Intent(getApplicationContext(),send_app_review.class);
            startActivity(in);
        }
        else if (R.id.viewapprating==id){
            Intent in=new Intent(getApplicationContext(),view_app_rating.class);
            startActivity(in);
        }
        else if (R.id.logout==id){
            Intent in=new Intent(getApplicationContext(),login.class);
            startActivity(in);
        }





        return false;
    }

    @Override
    public void onClick(View v) {
        if(v==cprofile){
            Intent i=new Intent(getApplicationContext(),user_view_profile.class);
            startActivity(i);
        }
        if(v==cmatstatus){
            Intent i=new Intent(getApplicationContext(),view_material_request_status.class);
            startActivity(i);
        }
        if(v==cinstitution){
            Intent i=new Intent(getApplicationContext(),view_institution.class);
            startActivity(i);
        }
        if(v==cinstrument){
            Intent i=new Intent(getApplicationContext(),view_instrument_request_status.class);
            startActivity(i);
        }
    }
}