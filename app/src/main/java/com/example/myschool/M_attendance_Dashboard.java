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

public class M_attendance_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mattendance_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void openActivityPP(View view) {
        Intent intent = new Intent(this, AttendancePP.class);
        intent.putExtra("sclass","PP");
        startActivity(intent);

    }

    public void classone(View view) {
        Intent intent = new Intent(this, AttendancePP.class);
        intent.putExtra("sclass","ONE");
        startActivity(intent);
    }

    public void classtwo(View view) {
        Intent intent = new Intent(this, AttendancePP.class);
        intent.putExtra("sclass","TWO");
        startActivity(intent);
    }

    public void classthree(View view) {
        Intent intent = new Intent(this, AttendancePP.class);
        intent.putExtra("sclass","THREE");
        startActivity(intent);
    }

    public void classfour(View view) {
        Intent intent = new Intent(this, AttendancePP.class);
        intent.putExtra("sclass","FOUR");
        startActivity(intent);
    }
}