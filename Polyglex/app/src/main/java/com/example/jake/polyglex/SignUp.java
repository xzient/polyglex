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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import user.User;

public class SignUp extends AppCompatActivity {


    private FirebaseAuth mAuth;



    //DATABASE
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRef;

    private static final String TAG = "SignUpActivity";

    /**
     * ON CREATE
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Authentication
        mAuth = FirebaseAuth.getInstance();

        //DATABASE
        mRef = mRootRef;
        //database = FirebaseDatabase.getInstance();
        //mRef = database.getReference("Users");


        /**
         * EditText
         */
        //final EditText mUsername = (EditText)findViewById(R.id.newUsername); // Not used now
        final EditText mEmail = (EditText) findViewById(R.id.newEmail);
        final EditText mPassword = (EditText) findViewById(R.id.newPassword);
        final EditText mUsername = (EditText) findViewById(R.id.newUsername);


        /**
         * Cancel Button
         */
        Button mCancelButton = (Button) findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(SignUp.this, FirstActivity.class);

                startActivity(cancel);
            }
        });

        /**
         * Submit Button
         */
        Button mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * 8/11/2017 For now this method uses the local singleton UserList
             * @param view
             */
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString(); //Password does not need trimming. So it checks if there are extra spaces after.
                final String username = mUsername.getText().toString().trim();

                //Check valid username
                Pattern userPattern = Pattern.compile("^\\w{3,}$");
                Matcher userMatcher = userPattern.matcher(username);

                Pattern passwordPattern = Pattern.compile("^[!@#%\\^$*?\\-,.\\w]{6,}$");
                Matcher passwordMatcher = passwordPattern.matcher(password);


                /**
                 * Constraints to SingUp
                 */
                if(email.isEmpty() || password.isEmpty() || username.isEmpty() || !(userMatcher.matches()) || !(passwordMatcher.matches())) {
                    if(email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                        Toast.makeText(SignUp.this, R.string.auth_failed3,
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(!(passwordMatcher.matches())) {
                        Toast.makeText(SignUp.this, R.string.auth_failed5, Toast.LENGTH_SHORT).show();
                    }
                    else if(!(userMatcher.matches())) {
                        Toast.makeText(SignUp.this, R.string.auth_failed4,
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignUp.this, R.string.auth_failed3,
                                Toast.LENGTH_SHORT).show();
                    }

                    return;
                }

                /**
                 * Add to fireBase
                 */
                else {
                    /**
                     *
                     */
                    mRef.child(username.toLowerCase()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null) {
                                //user exists, error
                                Toast.makeText(SignUp.this, R.string.auth_failed6,
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                            /**
                             * New User is added
                             */
                            else {
                                mAuth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                                // If sign in fails, display a message to the user. If sign in succeeds
                                                // the auth state listener will be notified and logic to handle the
                                                // signed in user can be handled in the listener.
                                                if (!task.isSuccessful()) {
                                                    Toast.makeText(SignUp.this, R.string.auth_failed2,
                                                            Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Log.d("TEST2",  mRef.child(task.getResult().getUser().getUid()).toString());
                                                    Toast.makeText(SignUp.this, "New user added!", Toast.LENGTH_SHORT).show();


                                                    mRef.child("users").child(mAuth.getCurrentUser().getUid()).setValue(new User(username, password, email.toLowerCase(), "NoHayOtro"));

                                                    mRef.child("usernames").child(username.toLowerCase()).setValue(new User(username, password, email.toLowerCase(), "NoHayOtro"));


                                                    //Sign out user so they enter their data
                                                    mAuth.signOut();
                                                    Intent goToFirst = new Intent(SignUp.this, FirstActivity.class);
                                                    startActivity(goToFirst);
                                                }

                                                // ...
                                            }
                                        });
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }
        });
    }
}