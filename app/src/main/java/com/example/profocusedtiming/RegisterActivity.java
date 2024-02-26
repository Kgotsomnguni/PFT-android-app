package com.example.profocusedtiming;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText edName, edSurname, edUsername, edEmail, edPassword, edConfirmPassword;
    Button btn;
    TextView tv;

    // Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName = findViewById(R.id.editTextRegName);
        edSurname = findViewById(R.id.editTextRegSurname);
        edEmail = findViewById(R.id.editTextRegEmail);
        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirmPassword = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        // Firebase initialization
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://profocusedtiming-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("Users");

        tv.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        btn.setOnClickListener(view -> {
            String firstname = edName.getText().toString();
            String surname = edSurname.getText().toString();
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            String ConPassword = edConfirmPassword.getText().toString();
            String Email = edEmail.getText().toString();

            if (username.length() == 0 || password.length() == 0 || firstname.length() == 0 ||
                    surname.length() == 0 || ConPassword.length() == 0 || Email.length() == 0) {
                Toast.makeText(getApplicationContext(), "Fields cannot be empty. Please fill all required fields to continue.", Toast.LENGTH_SHORT).show();
            } else if (password.compareTo(ConPassword) == 0) {
                if (IsValid(password)) {
                    mAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    String userId = mAuth.getCurrentUser().getUid();
                                    User user = new User(firstname, surname, username, Email);

                                    myRef.child(userId).setValue(user).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User has been created successfully!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Failed to register user! Please try again.", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Username already exists! Please try again.", Toast.LENGTH_LONG).show();
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, including a letter, a digit, and a special character", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean IsValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
    }
}
