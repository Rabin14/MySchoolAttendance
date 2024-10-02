package com.example.myschool.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myschool.MainDashboard;
import com.example.myschool.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Login extends AppCompatActivity {
EditText editText,editText2;
TextView username,password;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        // Get the value of shared preference back
        SharedPreferences getShared = getSharedPreferences("demo", MODE_PRIVATE);
        String value = getShared.getString("str1","Username");
        String value2 = getShared.getString("str2","Password");
        editText.setText(value);
        editText2.setText(value2);
        GetUserDetailsFirebase();

    }
    private void SaveLoginDetails() {

        String msg = editText.getText().toString();
        String msg2 = editText2.getText().toString();

        SharedPreferences shrd = getSharedPreferences("demo", MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();

        editor.putString("str1", msg);
        editor.putString("str2", msg2);
        editor.apply();

    }

    public void slideUp(View view) {
        String user_id1 = editText.getText().toString().trim();
        String user_password1 = editText2.getText().toString().trim();

        String fuser = username.getText().toString().trim();
        String fpassword = password.getText().toString().trim();


        if (TextUtils.isEmpty(user_id1)) {
            editText.setError("User Id is Required.");
            return;
        }

        if (TextUtils.isEmpty(user_password1)) {
            editText2.setError("Password is Required.");
            return;
        }


        if (user_id1.matches(fuser) && (user_password1.matches(fpassword))){
            SaveLoginDetails();
            Intent intent = new Intent(Login.this, MainDashboard.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(Login.this, "Please Enter Valid User Id and Password ! ", Toast.LENGTH_SHORT).show();

        }

    }
    public void GetUserDetailsFirebase() {
        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("Login").document("User");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username.setText(documentSnapshot.getString("UserName"));
                password.setText(documentSnapshot.getString("UserPassword"));


            }
        });
    }
}