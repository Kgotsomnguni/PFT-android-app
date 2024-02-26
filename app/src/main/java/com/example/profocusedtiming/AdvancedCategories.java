package com.example.profocusedtiming;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdvancedCategories extends AppCompatActivity {

    private DatabaseReference myRef;

    private Button AddCategory;
    private EditText Category_Name;
    private SeekBar MinSeek;
    private SeekBar MaxSeek;
    private TextView minHrs;
    private TextView maxHrs;
    private Button Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_categories);

        try {
            AddCategory = findViewById(R.id.CategoryCreate);
            Category_Name = findViewById(R.id.editTextCategoryName);
            MinSeek = findViewById(R.id.minbar);
            MaxSeek = findViewById(R.id.maxbar);
            minHrs = findViewById(R.id.mintext);
            maxHrs = findViewById(R.id.maxtext);
            Cancel = findViewById(R.id.CategoryCancel);

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String userId = mAuth.getCurrentUser().getUid();

            FirebaseDatabase database = FirebaseDatabase.getInstance("https://profocusedtiming-default-rtdb.europe-west1.firebasedatabase.app/");
            myRef = database.getReference("Category/" + userId);

            MinSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    minHrs.setText(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            MaxSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    maxHrs.setText(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            Cancel.setOnClickListener(view -> startActivity(new Intent(AdvancedCategories.this, HomeActivity.class)));

            AddCategory.setOnClickListener(v -> {
                String CatName = Category_Name.getText().toString().trim();
                String MIN = minHrs.getText().toString();
                String MAX = maxHrs.getText().toString();

                if (CatName.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a name for the category", Toast.LENGTH_SHORT).show();
                } else {
                    CreateCat(CatName, Integer.parseInt(MIN), Integer.parseInt(MAX), 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CreateCat(String name, int min, int max, int numberOfItems) {
        Category category = new Category(name, min, max, numberOfItems);
        String id = myRef.push().getKey();
        myRef.child(id).setValue(category);
        Toast.makeText(this, "Category Created", Toast.LENGTH_SHORT).show();
        finish();
    }
}
