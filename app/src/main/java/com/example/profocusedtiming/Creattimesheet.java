package com.example.profocusedtiming;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Creattimesheet extends AppCompatActivity {
    Button btnDate;
    TextView tv2;
    TextView StartTime;
    TextView EndTime;
    Button btnEndTime;
    Button btnStartTime;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private ImageView imageView2;
    private Button buttonAddphoto;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private boolean tookItemImage = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_entries);
        imageView2 = findViewById(R.id.imageView);
        buttonAddphoto = findViewById(R.id.buttonAddphoto);
        // New code
        Button cancel = findViewById(R.id.buttonCancelEntry);
        btnDate = findViewById(R.id.buttonSelectDate);
        tv2 = findViewById(R.id.ShowDate);
        StartTime = findViewById(R.id.ShowStarttime);
        EndTime = findViewById(R.id.ShowEndtime);
        btnEndTime = findViewById(R.id.buttonEndTime);
        btnStartTime = findViewById(R.id.buttonStartTime);
        Button CreateEntry = findViewById(R.id.buttonCreateEntry);
        buttonAddphoto = findViewById(R.id.buttonAddphoto);
        imageView2 = findViewById(R.id.Photoview2);
        // End of new code

        // CreateEntry button click listener
        CreateEntry.setOnClickListener(view -> {
            // Implement your logic here
        });

        // btnDate button click listener
        btnDate.setOnClickListener(view -> OpenDateDialog());

        // cancel button click listener
        cancel.setOnClickListener(view -> startActivity(new Intent(Creattimesheet.this, HomeActivity.class)));

        // btnStartTime button click listener
        btnStartTime.setOnClickListener(view -> OpenTimeDialog());

        // btnEndTime button click listener
        btnEndTime.setOnClickListener(view -> EndTimeDialog());

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        imageView2.setImageBitmap(imageBitmap);
                        tookItemImage = true;
                    }
                });

        buttonAddphoto.setOnClickListener(view -> checkCameraPermission());
    }


    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(Creattimesheet.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Creattimesheet.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(getApplicationContext(), "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            cameraLauncher.launch(takePictureIntent);
        }
    }

    public void OpenDateDialog() {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                R.style.DialogTheme,
                (datePicker, year, month, day) ->
                        tv2.setText(String.valueOf(year) + " . " + String.valueOf(month + 1) + " . " + String.valueOf(day)),
                2023,
                0,
                1
        );
        dialog.show();
    }

    public void OpenTimeDialog() {
        TimePickerDialog Timedialog = new TimePickerDialog(

                this,
                R.style.DialogTheme,
                (timePicker, Hours, minutes) -> StartTime.setText(String.valueOf(Hours) + ":" + String.valueOf(minutes)),
                15,
                0,
                true
        );
        Timedialog.show();
    }

    public void EndTimeDialog() {
        TimePickerDialog Enddialog = new TimePickerDialog(
                this,
                R.style.DialogTheme,
                (timePicker, Hours, minutes) -> {
                    EndTime.setText(String.valueOf(Hours) + ":" + String.valueOf(minutes));
                    EndTime.getText().toString();
                },
                15,
                0,
                true
        );
        Enddialog.show();
    }
}
