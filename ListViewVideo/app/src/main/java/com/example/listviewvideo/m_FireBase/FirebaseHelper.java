package com.example.listviewvideo.m_FireBase;

import com.example.listviewvideo.m_Model.Spacecraft;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by tim on 11/13/17.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

    // Pass db reference
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    // write if not null
    public Boolean save(Spacecraft spacecraft) {
        if (spacecraft == null) { saved = false; }
        else {
            try {
                db.child("Spacecraft").push().setValue(spacecraft);
                saved = true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }

        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        spacecrafts.clear();

        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            Spacecraft spacecraft = ds.getValue(Spacecraft.class);
            spacecrafts.add(spacecraft);
        }
    }

    //retrieve data
     public ArrayList<Spacecraft> retrieve() {
         db.addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 fetchData(dataSnapshot);
             }

             @Override
             public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                 fetchData(dataSnapshot);
             }

             @Override
             public void onChildRemoved(DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
         return spacecrafts;
     }

}

/*
def save
    pars spacecraft type Spacecraft
    return boolean named trueorfalse, db named db, string named value
    code
        if spacecraft is null
            set saved to false
        else
            try
                db
                    child 'Spacecraft'
                    push
                    setValue spacecraft
                set saved to true
            catch database exception e
                e print stack trace
                set saved to false
def name, type string, getter if equals 0 print 'hello'
def
    name
    type string
    gett
        if
            statement
                this equals 0
            then
                print 'hello'


set
    if
    type function
    pars
        statement
            type code
            returns boolean
        do
            type code
        else
            optional
            type code
        code
            eval statement
            jump do, else
*/