package com.example.projettp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.VISIBLE;

public class Intervention extends AppCompatActivity {
    String URL = "http://192.168.43.156/tp2021/insert.php";

    Button date;
    ListView list;
    ImageButton btnrefrech,btndelete,btnadd;
    String ms = "";
    ProgressBar progressBar;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervention);
        list = findViewById(R.id.Listview);
      progressBar=findViewById(R.id.progressBar_cyclic);

        date = findViewById(R.id.date);
        toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        btnrefrech =findViewById(R.id.btnrefrech);

        toolbar.setTitle("Intervention");
        setSupportActionBar(toolbar);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog picker = new DatePickerDialog(Intervention.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        btnrefrech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bg_select bgs = new bg_select(getApplicationContext());

                bgs.execute();

            }
        });



    }

    public class bg_select extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog dialog;

        public bg_select(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new AlertDialog.Builder(Intervention.this).create();
            dialog.setTitle("Affichage Liste Interventions...");
            progressBar.setVisibility(VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            String connstr = "http://192.168.43.156/tp2021/insert.php";
            URL url = null;
            try {
                url = new URL(connstr);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setDoInput(true);
                //http.setDoOutput(true);

                InputStream ips = http.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
                String ligne = "";
                while ((ligne = reader.readLine()) != null) {
                    result += ligne;
                }
                reader.close();
                ips.close();
                http.disconnect();
                return result;
            } catch (IOException e) {
                result = e.getMessage();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) {
                ArrayList list1 = parse(s);


                //remplir le listView
                /***
                 * Map : projection
                 * String (le premier élément : clé)
                 * String (le deuxième élément : valeur)
                 */

                final List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                final ArrayList<Task> tasks = new ArrayList<>();
                try {
                    JSONArray jtaskArray = new JSONArray(s);
                    for (int i = 0; i < jtaskArray.length(); i++) {
                        Map<String, String> datum = new HashMap<String, String>(2);

                        //task.add(datum);
                        tasks.add(new Task(jtaskArray.optJSONObject(i).optString("titre"),jtaskArray.optJSONObject(i).optInt("id"),jtaskArray.optJSONObject(i).optString("heuredebutplan"),jtaskArray.optJSONObject(i).optString("heurefinplan"),1,jtaskArray.optJSONObject(i).optString("adresse")));
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final MyListAdapter adapter =new MyListAdapter(getApplicationContext(),tasks);
                list.setAdapter(adapter);
                list.setItemsCanFocus(false);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent lst=new Intent(Intervention.this,SelectedList.class);
                        lst.putExtra("position",position+1);
                        startActivity(lst);
                    }
                });

                list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {

                        final int item = i;

                        new AlertDialog.Builder(Intervention.this)
                                .setIcon(android.R.drawable.ic_delete)
                                .setTitle("Vous etes sure ?")
                                .setMessage("supprimer cette Intervention")
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        tasks.remove(item);
                                        adapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Non",null)
                                .show();
                        return true;
                    }
                });
            }


        }

        private ArrayList<Task> parse(final String json) {
            ms = "";


            final ArrayList<Task> task = new ArrayList<>();
            try {
                final JSONArray jtaskArray = new JSONArray(json);

                for (int i = 0; i < jtaskArray.length(); i++) {

                    task.add(new Task("titre",1,"heuredebutplan","heurefinplan",1,"adresse"));
                    ms = ms + "titre " + jtaskArray.optJSONObject(i).optString("titre") + " | " +
                            "heuredebutplan " + jtaskArray.optJSONObject(i).optString("heuredebutplan")+
                            "-"+"heurefinplan " + jtaskArray.optJSONObject(i).optString("heurefinplan" )
                            + jtaskArray.optJSONObject(i).optString("adresse" ) +"\n";
                }
                return task;


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}