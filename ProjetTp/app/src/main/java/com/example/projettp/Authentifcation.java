package com.example.projettp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Authentifcation extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentifcation);



        final DrawerLayout drawerLayout=findViewById(R.id.drawerLayout);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView=findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId ();
                if (id==R.id.intervention){
                    Intent intent=new Intent (Authentifcation.this,Intervention.class);
                    startActivity (intent);

                }
                if(id==R.id.logout){
                    Intent ilogout=new Intent(Authentifcation.this,MainActivity.class);
                    Toast.makeText(getApplicationContext(),"Deconnexion...",Toast.LENGTH_SHORT).show();
                    startActivity(ilogout);
                }
                if(id==R.id.adresse){
                    Intent iadresse=new Intent(Authentifcation.this,AdresseActivity.class);
                    Toast.makeText(getApplicationContext(),"Localisation...",Toast.LENGTH_SHORT).show();
                    startActivity(iadresse);
                }


                return true;
            }
        });
    }


}