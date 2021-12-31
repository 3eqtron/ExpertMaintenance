package com.example.projettp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<Task> {
    // private String[]nom;
    // private String[]adresse;
    // private Activity context;
    // LayoutInflater inflter;
    public MyListAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
//       this.nom=nom;
        //     this.adresse=adresse;
        //    this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View lsvElementView = convertView;

        if (lsvElementView == null) {
            lsvElementView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);

        }
        Task tsk = getItem(position);

        TextView interventionlis = (TextView) lsvElementView.findViewById(R.id.intervention_list);
        interventionlis.setText(tsk.getTitre());
TextView site=(TextView)lsvElementView.findViewById(R.id.societe_list);
site.setText("La societe de:"+tsk.getAdresse());
        TextView heures = (TextView) lsvElementView.findViewById(R.id.heures_list);
        heures.setText(tsk.getHeuredebutplan() + "-" + tsk.getHeurefinplan());
        final CheckBox checkBox=(CheckBox)lsvElementView.findViewById(R.id.checkBox);



        return lsvElementView;

    }

}
