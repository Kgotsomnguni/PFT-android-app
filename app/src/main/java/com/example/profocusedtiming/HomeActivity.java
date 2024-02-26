package com.example.profocusedtiming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth= FirebaseAuth.getInstance();

        userId = mAuth.getCurrentUser().getUid();

        //SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        //String username = sharedPreferences.getString("username","").toString();
        // Toast.makeText(getApplicationContext(),"welcome"+ username,Toast.LENGTH_SHORT).show();
        TextView Logout = findViewById(R.id.textViewExit);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.clear();
                //editor.apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));

            }

        });
        Button createCategory = findViewById(R.id.btnCreateCategory);
        createCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,createCategory.class));
            }
        });


        Button createTimesheet = findViewById(R.id.btnCreateTimesheet);
        createTimesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,Creattimesheet.class));
            }
        });

        Button Projects = findViewById(R.id.btnProjects);
        Projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,projects.class));

            }
        });

        Button Goals = findViewById(R.id.btnGoals);
        Goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,Goals.class));

            }
        });

        Button ViewTimesheets = findViewById(R.id.btnViewTimesheets);
        Goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,viewTimesheets.class));

            }
        });

        Button Reports = findViewById(R.id.btnReports);
        Goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,Reports.class));

            }
        });







    }
}