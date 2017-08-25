package com.example.jake.polyglex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Center extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Authentication
    private static final String TAG = "Center";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //DATA
    //FirebaseDatabase database;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRef = mRootRef.child("users");
    String mUsername;
    TextView mUsernameDisplay;
    NavigationView navigationView;

    //Language array
    String[] fullItems;
    boolean[] checkedItems;
    String selection;
    int langNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //AUTHENTICATION
        mAuth = FirebaseAuth.getInstance();


        //DATABASE
            //Retrieve data for Navigator Header
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Authenticate User
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());


                    /**
                     * This part allows the code to get usename.
                     */
                    mRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.getValue() != null) {
                                mUsername = dataSnapshot.child("username").getValue(String.class);
                                //Toast.makeText(Center.this, mUsername, oast.LENGTH_SHORT).show();//Toast to check user
                                View headerView = navigationView.getHeaderView(0);
                                mUsernameDisplay = (TextView) headerView.findViewById(R.id.username_display);
                                mUsernameDisplay.setText(mUsername);
                            }
                            else {
                                mUsername = "NULL";
                                Toast.makeText(Center.this, "No data found",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                    //***************************************************//

                    //If logged in already. It goes directly to Center.
                    //setContentView(R.layout.activity_center);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent home = new Intent(Center.this, FirstActivity.class);
                    startActivity(home);
                }
                // ...
            }
        };

       // TextView mTextView = (TextView) findViewById(R.id.username_display);
       // mTextView.setText("Jake");

        //Set Layout

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setItemIconTintList(null);


        //Language array
        fullItems = getResources().getStringArray(R.array.languages);
        checkedItems = new boolean[fullItems.length];
    }//End onCreate
    /***********************************************************************/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /***********************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.center, menu);
        return true;
    }
    /***********************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /************************************************************************
    /**
     * This method works for the display of current languages used
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.english_lexicon) {

            EnglishWordFragment frag = new EnglishWordFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, frag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.french_lexicon) {
            Toast.makeText(this, "French Lexicon", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.german_lexicon) {
            Toast.makeText(this, "German Lexicon", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.japanese_lexicon) {
            Toast.makeText(this, "Japanese Lexicon", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.portuguese_lexicon) {
            Toast.makeText(this, "Portuguese Lexicon", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.spanish_lexicon) {

            SpanishWordFragment frag = new SpanishWordFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, frag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.add_language) {

            final ArrayList<String> mOffItems = new ArrayList<>();

            for (int i = 0; i < fullItems.length; i++) {
                if (!checkedItems[i]) {
                    mOffItems.add(fullItems[i]);
                }
            }
            final String[] offItems = mOffItems.toArray(new String[mOffItems.size()]);

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Center.this);
            mBuilder.setTitle("Select Language");
            mBuilder.setSingleChoiceItems(offItems, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selection = (String) offItems[which];
                    langNum = which;
                }
            });

            mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int index = Arrays.asList(fullItems).indexOf(selection);
                    checkedItems[index] = true;
                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    if (checkedItems[Arrays.asList(fullItems).indexOf("English")]) {
                        navigationView.getMenu().findItem(R.id.english_lexicon).setVisible(true);
                    }

                    if (checkedItems[Arrays.asList(fullItems).indexOf("French")]) {
                        navigationView.getMenu().findItem(R.id.french_lexicon).setVisible(true);
                    }

                    if (checkedItems[Arrays.asList(fullItems).indexOf("German")]) {
                        navigationView.getMenu().findItem(R.id.german_lexicon).setVisible(true);
                    }

                    if (checkedItems[Arrays.asList(fullItems).indexOf("Japanese")]) {
                        navigationView.getMenu().findItem(R.id.japanese_lexicon).setVisible(true);
                    }

                    if (checkedItems[Arrays.asList(fullItems).indexOf("Portuguese")]) {
                        navigationView.getMenu().findItem(R.id.portuguese_lexicon).setVisible(true);
                    }

                    if (checkedItems[Arrays.asList(fullItems).indexOf("Spanish")]) {
                        navigationView.getMenu().findItem(R.id.spanish_lexicon).setVisible(true);
                    }

                    Toast.makeText(Center.this, "Lexicon created!", Toast.LENGTH_SHORT).show();
                }
            });

            mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();

        } else if (id == R.id.action_settings) {

        } else if (id == R.id.logout) {
            mAuth.signOut();
            Intent logOut = new Intent(Center.this, FirstActivity.class);
            startActivity(logOut);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /***********************************************************************/
    public void refreshNow() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    /***********************************************************************/
    //This is added for authentication. I still don't know what it does exactly--- Xavier
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    /***********************************************************************/
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    /***********************************************************************/
}
/***********************************************************************/