package com.example.firebaselogin;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

/**
 * Created by tim on 2/27/18.
 */

public class FirebaseApplication extends Application{

    private static final String TAG = FirebaseApplication.class.getSimpleName();

    public FirebaseAuth firebaseAuth;

    public FirebaseAuth.AuthStateListener mAuthStateListener;

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth = FirebaseAuth.getInstance();
    }

    public String getFirebaseUserAuthenticatedId() {
        return
                (firebaseAuth.getCurrentUser() != null)
                ? firebaseAuth.getCurrentUser().getUid()
                : null;
    }

    public void checkUserLogin(final Context context) {
        if (firebaseAuth.getCurrentUser() !=) {
            Intent profileIntent = new Intent(context, ProfileActivity.class);
            context.startActivity(profileIntent);
        }
    }

    public void isUserCurrentlyLogin(final Context context) {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Intent profileIntent = new Intent (context, ProfileAcitivyt.class);
                    context.startActivity(profileIntent);
                }
            }
        };
    }

    public void createNewUser(Context context, String email, String password, final TextView errorMessage) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            errorMessage.setText("Failed to login. Invalid user.");
                        }
                    }
                });
    }

    public void loginAUser(final Context context, String email, String password, final TextView errorMesage) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWIthEmail", task.getException());
                            errorMesage.setText("Failed to login");
                        } else {
                            Helper.displayMessageToast(context, "User has been login");
                            context.startActivity(new Intent(context, PorfileActivity.class));
                        }
                    }
                })
    }
}
