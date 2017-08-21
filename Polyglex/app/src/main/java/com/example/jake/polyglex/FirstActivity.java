package com.example.jake.polyglex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This class Activity is the first activity in the app.
 * This app will be skipped if the used has already logged in.
 */
public class FirstActivity extends AppCompatActivity {

    //private UserList users; // We won't be using this anymore
    private static final String TAG = "FirstActivity";

    private FirebaseAuth mAuth;
    //DATABASE
    FirebaseDatabase database;

    //Get data for the listener (retrieve data from database)
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = mRootRef.child("users");

    /**
     * ON CREATE
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //AUTHENTICATION
        mAuth = FirebaseAuth.getInstance();
        //DATABASE
        database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Users");


        final EditText mEmail; //This editText can also be a username. For now it's called mEmail
        final EditText mPassword;

        /**
         * Thi is is SignUpButton
         */
        Button mSignUpButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_up = new Intent(FirstActivity.this, SignUp.class);
                startActivity(sign_up);
            }
        });

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);


        /**
         * This is the LoginButton - This should check if the right username is provided.
         */
        Button mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String login = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                //final String email;

                /**
                 * Check if login is a username or a password
                 */
                //It's a username
                if (!login.contains("@")) {
                    myRef.child(login.toLowerCase()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Username does not exist
                            if (dataSnapshot.getValue() == null) {
                                Toast.makeText(FirstActivity.this, R.string.auth_failed8,
                                        Toast.LENGTH_SHORT).show();
                            }
                            //Username does exist
                            else {
                                final String email = dataSnapshot.child("email").getValue(String.class);

                                /**
                                 * Add from username ---  email now
                                 */
                                if (email.isEmpty() || password.isEmpty()) {
                                    Toast.makeText(FirstActivity.this, R.string.auth_failed3,
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                } else {

                                    mAuth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(FirstActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(FirstActivity.this, "Logged in!",
                                                                Toast.LENGTH_SHORT).show();
                                                        Intent home = new Intent(FirstActivity.this, HomeActivity.class);
                                                        startActivity(home);
                                                    }
                                                    // If sign in fails, display a message to the user. If sign in succeeds
                                                    // the auth state listener will be notified and logic to handle the
                                                    // signed in user can be handled in the listener.
                                                    else if (!task.isSuccessful()) {
                                                        Log.w(TAG, "signInWithEmail:failed", task.getException());
                                                        Toast.makeText(FirstActivity.this, R.string.auth_failed,
                                                                Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                //Its an email
                else {
                    if (login.isEmpty() || password.isEmpty()) {
                        Toast.makeText(FirstActivity.this, R.string.auth_failed3,
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else {

                        mAuth.signInWithEmailAndPassword(login, password)
                                .addOnCompleteListener(FirstActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                        if (task.isSuccessful()) {
                                            Toast.makeText(FirstActivity.this, "Logged in!",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent home = new Intent(FirstActivity.this, HomeActivity.class);
                                            startActivity(home);
                                        }
                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        else if (!task.isSuccessful()) {
                                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                                            Toast.makeText(FirstActivity.this, R.string.auth_failed,
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    }
                }

            }
        });
    }


}