package com.example.projettp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static android.app.Activity.RESULT_OK;

public class tab2 extends Fragment {
    private static int RESULT_LOAD_IMAGE = 1;
    private TextView textView;
    private Uri fileUri;
    private String filePath;
ImageView img;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        Button buttonLoadImage = (Button) view.findViewById(R.id.buttonLoadPicture);
        textView = view.findViewById(R.id.textview);
        img=view.findViewById(R.id.img);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, RESULT_LOAD_IMAGE);
            }
        });
return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

                if ((resultCode == -1)&&(RESULT_LOAD_IMAGE==1)) {
                    fileUri = data.getData();
                    filePath = fileUri.getPath();
                    textView.setText(filePath);
                    Bitmap myBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                    img.setImageBitmap(myBitmap);
                    Toast.makeText(getContext(),"image Selectioné...",Toast.LENGTH_SHORT).show();
                }



        }

}
