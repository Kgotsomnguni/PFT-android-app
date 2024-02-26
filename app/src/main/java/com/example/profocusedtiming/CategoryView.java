package com.example.profocusedtiming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {
    private FloatingActionButton newCategoryAdd;
    private TextView txtNoCat;

    private ProgressBar progressBar2;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String userId;
    private FirebaseAuth mAuth;
    private ArrayList<Category> categories;
    private ArrayList<String> categoriesId;
    private RecyclerView rvCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance("https://profocusedtiming-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("Category/"+userId);
        //
        userId = mAuth.getCurrentUser().getUid();
        categories = new ArrayList<>();
        categoriesId = new ArrayList<>();
        progressBar2 = findViewById(R.id.progressBar2);

        newCategoryAdd = findViewById(R.id.newCategory);

        txtNoCat =  findViewById(R.id.txtNoCategory);
        rvCategories = findViewById(R.id.recyclerViewCategoryItems);

        //
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                categories.clear();
                categoriesId.clear();

                for (DataSnapshot pulledCategories : snapshot.getChildren()) {
                    categoriesId.add(pulledCategories.getKey());
                    categories.add(pulledCategories.getValue(Category.class));
                }
                //Toast.makeText(MainActivity.this, "Size: "+categories.size(), Toast.LENGTH_LONG).show();

                progressBar2.setVisibility(View.GONE);

                if(categories.size() == 0)
                    txtNoCat.setVisibility(View.VISIBLE);
                else
                    txtNoCat.setVisibility(View.GONE);

                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CategoryView.this, "An Error Occurred: "+error.getMessage(), Toast.LENGTH_LONG).show();
                progressBar2.setVisibility(View.GONE);
            }
        });
        newCategoryAdd.setOnClickListener(v -> startActivity(new Intent(CategoryView.this, createCategory.class)));
    }
    private void setAdapter() {
        RecyclerAdapterCategories adapterForUserClients = new RecyclerAdapterCategories(categories, categoriesId);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvCategories.setLayoutManager(layoutManager);
        rvCategories.setItemAnimator(new DefaultItemAnimator());
        rvCategories.setAdapter(adapterForUserClients);

    }

}