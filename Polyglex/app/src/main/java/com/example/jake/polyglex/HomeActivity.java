package com.example.jake.polyglex;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import user.*;
import lexicon.*;
import word.*;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    //Authentication
    private static final String TAG = "HomeActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //DATA
    //FirebaseDatabase database;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("condition");








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //AUTHENTICATION
        mAuth = FirebaseAuth.getInstance();

        //DATABASE
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("message");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    //If logged in already. It goes directly to MainActivity.
                    //setContentView(R.layout.activity_home);
                }
                else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent home = new Intent(HomeActivity.this, FirstActivity.class);
                    startActivity(home);
                }
                // ...
            }
        };

        //Set layout
        // We are not using this for now.
        setContentView(R.layout.activity_home);
        //Firebase stuff


        Button mLogOut = (Button) findViewById(R.id.log_out_button);
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mAuth.signOut();
                Intent logOut = new Intent(HomeActivity.this, FirstActivity.class);
                startActivity(logOut);
            }
        });


        /**
         * English Button - Testing Data -- The following is a test for datastructure in the cloud
         */

        Button mEnglish = (Button) findViewById(R.id.english_button);
        mEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User test1 = new User("test", "test", "test@test.com", "2345");
                test1.addLexicon("English");
                test1.getLexicon("English").addWord("dog");
                test1.getLexicon("English").addWord("fucker");
                String[] words = test1.getLexicon("English").getOrthographyArray();
                Toast.makeText(HomeActivity.this, test1.getLexicon("English").displayWords(), Toast.LENGTH_SHORT).show();

            }
        });

        /**
         * Spanish Button
         */
        Button mSpanish = (Button) findViewById(R.id.spanish_button);
        mSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "spanish", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.container, new SpanishWordFragment()).commit();
            }
        });
    }

    public void refreshNow (){
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }

    //This is added for authentication. I still don't know what it does exactly--- Xavier
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);



    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
