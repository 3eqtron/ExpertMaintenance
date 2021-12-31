package com.example.projettp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class tab1 extends Fragment {
RequestQueue rq;
Button send;
    TextView heures, temptravailler, commentaires, heureseff, tempstravaillereff, commentaireseff, clientnom, societeclient, clienttelf,clientEmail;
private static final String URL = "http://192.168.43.156/tp2021/fragmentDetails.php";
Bundle bundle;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        temptravailler = view.findViewById(R.id.tempstravailler);
        commentaires = view.findViewById(R.id.commentaires);
        heureseff = view.findViewById(R.id.heureseff);
        tempstravaillereff = view.findViewById(R.id.tempstravaillereff);
        commentaireseff = view.findViewById(R.id.commentaireseff);
        clientnom = view.findViewById(R.id.clientnom);
        societeclient = view.findViewById(R.id.societeclient);
        clientEmail=view.findViewById(R.id.clientemail);
        clienttelf = view.findViewById(R.id.clienttelf);
        send=view.findViewById(R.id.send);
        final String rapport="rapport de client";

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_TEXT,"value"+rapport);
                startActivity(intent);
            }
        });


sendJsonrequest();



        return view;


    }

public void sendJsonrequest(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array=new JSONArray(response);
                            for (int i=0;i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);
                                String nom=object.getString("nom");
                                String tel=object.getString("tel");
                                String email=object.getString("email");
                                String adresse=object.getString("adresse");
                                String datedebut=object.getString("datedebut");
                                String datefin=object.getString("datefin");
                                clientEmail.setText("Email :"+email);
                                clientnom.setText("Nom:"+nom);
                                clienttelf.setText("Telephone:"+tel);
                                societeclient.setText("Adresse:"+adresse);
                                tempstravaillereff.setText(datedebut+"--"+datefin);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"erreur",Toast.LENGTH_SHORT).show();
            }
        });
Volley.newRequestQueue(getContext()).add(stringRequest);
}



}
