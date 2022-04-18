package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ascunderea titlului din screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        logoutButton = findViewById(R.id.logoutButton);
        //initializare firebase auth
        mAuth = FirebaseAuth.getInstance();

        logoutButton.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView);
        //btnNav.setOnNavigationItemSelectedListener(navListener);
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;
            switch (item.getItemId()){
                case R.id.miHome:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.miList:
                    selectedFragment=new ListFragment();
                    break;
            }

            //Begin transaction
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container)
        }
    }*/
    //verificare: daca userul e signed in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }


}