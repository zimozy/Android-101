package com.example.listviewvideo.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listviewvideo.R;
import com.example.listviewvideo.m_Model.Spacecraft;

import java.util.ArrayList;

/**
 * Created by tim on 11/13/17.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Spacecraft> spacecrafts;

    public CustomAdapter(Context context, ArrayList<Spacecraft> spacecrafts) {
        this.context = context;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.model, parent, false);
        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.nameTxt);
        TextView protpTxt = (TextView) convertView.findViewById(R.id.propellantTxt);
        TextView descTxt = (TextView) convertView.findViewById(R.id.descTxt);

        final Spacecraft spacecraft = (Spacecraft) this.getItem(position);

        nameTxt.setText(spacecraft.getName());
        protpTxt.setText(spacecraft.getPropellant());
        descTxt.setText(spacecraft.getDescription());

        //oniteclick

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, spacecraft.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
