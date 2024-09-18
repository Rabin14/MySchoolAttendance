package com.example.myschool.VewAttendance;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myschool.R;
import com.example.myschool.Students.MainAdapter;
import com.example.myschool.Students.StudentModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AttendanceView extends AppCompatActivity {
    private RecyclerView recyclerView;
    ViewAdapter adapter; // Create Object of the Adapter class
    private String ssclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_attendance_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ssclass = (Objects.requireNonNull(getIntent().getExtras()).getString("sclass"));

        recyclerView = findViewById(R.id.recyclernew);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        FirebaseRecyclerOptions<AttendanceModel> options =
                new FirebaseRecyclerOptions.Builder<AttendanceModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                        .child("AttendancePP")
                                        .child(currentDate)

                                , AttendanceModel.class)
                        .build();


        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new ViewAdapter(options);

        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        //


    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {

        super.onStart();
        adapter.startListening();

    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }

}