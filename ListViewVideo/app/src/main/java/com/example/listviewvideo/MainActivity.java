package com.example.listviewvideo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewvideo.m_FireBase.FirebaseHelper;
import com.example.listviewvideo.m_Model.Spacecraft;
import com.example.listviewvideo.m_UI.CustomAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseHelper firebaseHelper;
    CustomAdapter customAdapter;
    ListView listView;
    EditText nameEditText, propellantEditText, descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.lv);

        //initialize firebase db
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseHelper = new FirebaseHelper(databaseReference);

        //adapter
        customAdapter = new CustomAdapter(this, firebaseHelper.retrieve());
        listView.setAdapter(customAdapter);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });
    }

    private void displayInputDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Save to Firebase");
        dialog.setContentView(R.layout.input_dialog);

        nameEditText = dialog.findViewById(R.id.nameEditText);
        propellantEditText = dialog.findViewById(R.id.propellantEditText);
        descriptionEditText = dialog.findViewById(R.id.descEditText);
        final Button saveButton = dialog.findViewById(R.id.saveBtn);

        //save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data
                String name = nameEditText.getText().toString();
                String propellant = propellantEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                Spacecraft spacecraft = new Spacecraft();

                //set data
                spacecraft.setName(name);
                spacecraft.setPropellant(propellant);
                spacecraft.setDescription(description);

                //simple validation
                if (name != null && name.length() > 0) {
                    //then save
                    if (firebaseHelper.save(spacecraft)) {
                        //if saved clear edittext
                        nameEditText.setText("");
                        propellantEditText.setText("");
                        descriptionEditText.setText("");

                        customAdapter = new CustomAdapter(MainActivity.this, firebaseHelper.retrieve());
                        listView.setAdapter(customAdapter);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Make sure to type a name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}
