package com.example.myschool;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myschool.Students.AttendancePP;
import com.example.myschool.VewAttendance.AttendanceCountClass;
import com.example.myschool.VewAttendance.AttendanceView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainDashboard extends AppCompatActivity {
TextView ddate,vview,ttotal;
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ddate = findViewById(R.id.ddate);
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        ddate.setText("Date: "+date_n);
        vview = findViewById(R.id.vview);
        ttotal = findViewById(R.id.ttotal);
        loadtotalview();
    }

    public void manualattendance(View view) {
        startActivity(new Intent(this, M_attendance_Dashboard.class));
    }

    public void qrscan(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }

    public void viewAttendance(View view) {
        select_class_popup();
    }
    public void select_class_popup() {
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setGravity(Gravity.CENTER)
                .setContentHolder(new ViewHolder(R.layout.popup_selectclass)) // Use the custom layout
                .setCancelable(true)  // Allow dismissing by touching outside
                .setExpanded(true)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        // Handle button clicks inside the dialog
                        if (view.getId() == R.id.dialog_button) {
                            // Access the spinner from the dialog's content holder
                            View dialogView = dialog.getHolderView();
                            Spinner classSpinner = dialogView.findViewById(R.id.spinner);

                            // Get the selected class from the spinner
                            String selectedClass = classSpinner.getSelectedItem().toString();
                            Intent intent = new Intent(MainDashboard.this, AttendanceView.class);
                            intent.putExtra("sclass", selectedClass);
                            startActivity(intent);

                            dialog.dismiss(); // Close dialog when OK button is clicked
                        }
                    }
                })
                .create();

        // Set up the spinner outside of the click listener
        View dialogView = dialog.getHolderView();
        Spinner classSpinner = dialogView.findViewById(R.id.spinner);

        // Set up the spinner
        String[] classlist = getResources().getStringArray(R.array.s_class);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);

        dialog.show(); // Show the dialog
    }
    public void loadtotalview(){


        // Reference to the Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("1G7udEQy5eRTbAD0ryCHZEF-JVvDeNyKwT1sBOhe3MxE")
                .child("COUNT_ATTENDANCE")
                .child("0");

        // Attach a ValueEventListener to the database reference
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the student data from the snapshot
                AttendanceCountClass student = dataSnapshot.getValue(AttendanceCountClass.class);

                // Check if student data is null
                if (student != null) {
                    vview.setText("PP:"+student.getPP()+",  "+"ONE:"+student.getONE()+",  "+"TWO:"+student.getTWO()+",  "+"THREE:"+student.getTHREE()+",  "+"FOUR:"+student.getFOUR());
                    ttotal.setText("Total Attendance:"+student.getTOTAL());

                } else {
                    // If the student is null, notify the user
                    Toast.makeText(MainDashboard.this, "data not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log the error and show a Toast to notify the user
                Log.e("MainActivity", "Failed to load data: " + databaseError.getMessage());

            }
        });

        }




}


