package com.example.projettp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button connexion;
    EditText nom;
    EditText password;
    SqlDataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        nom=findViewById (R.id.nom);
        password=findViewById (R.id.password);
        connexion=findViewById (R.id.connexion);
        databaseHelper=new SqlDataBaseHelper (MainActivity.this);
        connexion.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                boolean isExist = databaseHelper.checkUserExist(nom.getText().toString(), password.getText().toString());

                if(isExist){
                    Intent i=new Intent (MainActivity.this,Authentifcation.class);
                    startActivity (i);
                    Toast.makeText (MainActivity.this,"succes",Toast.LENGTH_SHORT).show ();
                } else {
                    password.setText(null);
                    Toast.makeText(MainActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}











