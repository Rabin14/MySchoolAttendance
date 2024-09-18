package com.example.myschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myschool.Students.AttendancePP;
import com.example.myschool.VewAttendance.AttendanceView;

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
        Intent intent = new Intent(this, AttendanceView.class);
        intent.putExtra("sclass","PP");
        startActivity(intent);
    }
}