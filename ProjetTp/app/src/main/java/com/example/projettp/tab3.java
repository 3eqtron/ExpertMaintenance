package com.example.projettp;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class tab3 extends Fragment {
    Button btnsave, btnclear;
    SignaturePad signaturePad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        btnclear = view.findViewById(R.id.btnclear);
        btnsave = view.findViewById(R.id.btnsave);
        signaturePad = view.findViewById(R.id.t);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                btnsave.setEnabled(true);
                btnclear.setEnabled(true);
            }

            @Override
            public void onClear() {
                btnsave.setEnabled(false);
                btnclear.setEnabled(false);

            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bbicon;

                bbicon=signaturePad.getSignatureBitmap();
//ByteArrayOutputStream baosicon = new ByteArrayOutputStream();
//bbicon.compress(Bitmap.CompressFormat.PNG,0, baosicon);
//bicon=baosicon.toByteArray();

                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                OutputStream outStream = null;
                Integer counter = 0;
                File file = new File(extStorageDirectory, "Siganture"+counter+".jpg");
                try {

                    outStream = new FileOutputStream(file);
                    bbicon.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                } catch(Exception e) {

                }
                Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
                Toast.makeText(getContext(), "clear", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }




}


