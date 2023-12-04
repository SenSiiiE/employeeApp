package com.example.emminor2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.emminor2.FragmentContainer.DataCollectionFragment;
import com.example.emminor2.FragmentContainer.DataPreparationFragment;
import com.example.emminor2.FragmentContainer.DeploymentFragment;
import com.example.emminor2.FragmentContainer.HomeFragment;
import com.example.emminor2.FragmentContainer.PerformanceFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   drawerLayout= findViewById(R.id.drawerlayout);
   navigationView = findViewById(R.id.navigationView);
   toolbar = findViewById(R.id.toolbar);
   
   setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar
        , R.string.OpenDrawer,
                R.string.CloseDrawer
        );
        
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new HomeFragment());
        
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             
             int id = item.getItemId();
             
             if(id==R.id.Discription){
                 loadFragment(new HomeFragment());
             } else if (id==R.id.DataCollection) {
                 loadFragment(new DataCollectionFragment());
             } else if (id==R.id.DataPreparation) {
                 loadFragment(new DataPreparationFragment());
             } else if (id==R.id.Performance) {
                 loadFragment(new PerformanceFragment());
             } else if (id==R.id.Deployment) {
                 loadFragment(new DeploymentFragment());
             }

drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
   
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {
//        FragmentManager fm = getSupportFragmentManager();
//
//        @SuppressLint("CommitTransaction") FragmentTransaction ft = fm.beginTransaction();
//
//        ft.add(R.id.container,fragment);
//        ft.commit();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();



    }
}