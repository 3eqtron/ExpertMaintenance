package com.example.projettp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="gemprojet.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    SQLiteDatabase db;
    private static final String DATABASE_PATH = "/data/data/com.example.gemprojet/databases";
    private final String clients_TABLE="clients";
    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        createDb();
    }
    public SqlHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context context1) {
        super (context, name, factory, version);
        this.context = context1;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void createDb(){
        boolean dbExist = checkDbExist();

        if(!dbExist){
            this.getReadableDatabase();
            copyDatabase();
        }
    }
    private boolean checkDbExist(){
        SQLiteDatabase sqLiteDatabase = null;

        try{
            String path = DATABASE_PATH + DATABASE_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception ex){
        }

        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
            return true;
        }

        return false;
    }
    private void copyDatabase(){
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);

            String outFileName = DATABASE_PATH + DATABASE_NAME;

            OutputStream outputStream = new FileOutputStream (outFileName);

            byte[] b = new byte[1024];
            int length;

            while ((length = inputStream.read(b)) > 0){
                outputStream.write(b, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private SQLiteDatabase openDatabase(){
        String path = DATABASE_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }
    public boolean checkDATA(int id,String nom,String adresse,String tel,String fax,String email,String contact,String telcontact,
                             int valsync){
        String[] columns = {"client"};
        db = openDatabase();
        String selection = "id=? , nom = ?,adresse=?,tel=?,fax=?,email=?,contact=?,valsync=? ";
        String[] selectionArgs = {nom,adresse,tel,fax,email,contact};
        Cursor cursor = db.query(clients_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();


        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }

    }

}
