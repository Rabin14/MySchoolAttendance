package com.example.myschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myschool.Students.AttendancePP;
import com.example.myschool.VewAttendance.AttendanceView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class MainDashboard extends AppCompatActivity {

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



}