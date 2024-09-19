package com.example.myschool.Students;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myschool.ModelClass;
import com.example.myschool.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainAdapter extends FirebaseRecyclerAdapter<StudentModel, MainAdapter.myViewHolder> {
    private static final int SMS_PERMISSION_REQUEST_CODE = 123;
    private static final String PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS; // Named constant for permission
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<StudentModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull StudentModel model) {

        holder.sname.setText(model.getNAME());
        holder.sroll.setText(model.getROLL());
        holder.sclass.setText(model.getS_CLASS());
        holder.smobile.setText(model.getMOBILE());
        holder.sid.setText(model.getID_NO());


        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////////////


                /////////////

                final  DialogPlus dialogPlus = DialogPlus.newDialog(v.getContext())
                        .setContentHolder(new ViewHolder(R.layout.popup_attendance))
                        .setGravity(Gravity.CENTER)
                        .setCancelable(true)
                        .create();
                View view = dialogPlus.getHolderView();
                TextView sname = view.findViewById(R.id.sname);
                TextView sclass = view.findViewById(R.id.sclass);
                TextView sroll = view.findViewById(R.id.sroll);
                TextView sdate = view.findViewById(R.id.sdate);
                TextView stime = view.findViewById(R.id.stime);
                TextView smobile = view.findViewById(R.id.smobile);
                TextView satten = view.findViewById(R.id.satten);

                sname.setText(model.getNAME());
                sroll.setText(model.getROLL());
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                sdate.setText(date_n);
                String time_n = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
                stime.setText(time_n);
                smobile.setText(model.getMOBILE());
                sclass.setText(model.getS_CLASS());

                Button sbutton = view.findViewById(R.id.dialog_button);
                sbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //////////////////////////////////////////////////////////////////////////////////////////////

                        String attendanceDate = sdate.getText().toString();
                        // Generate a unique key based on the roll number
                        String rollNumber = sroll.getText().toString();
                        int number = Integer.parseInt(rollNumber);
                        String uniqueKey = String.format("%03d", number);


                        DatabaseReference attendanceRef = FirebaseDatabase.getInstance().getReference().child("Attendance"+model.getS_CLASS())
                                .child(attendanceDate)
                                .child(uniqueKey);

                        attendanceRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {// Removed the check if (!snapshot.exists())
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("A_Date", attendanceDate);
                                map.put("B_Time", stime.getText().toString());
                                map.put("C_Class", sclass.getText().toString());
                                map.put("D_Roll", rollNumber);
                                map.put("E_Name", sname.getText().toString());
                                map.put("F_Status", satten.getText().toString());
                                String attendanceStatus = satten.getText().toString();
                                attendanceRef.setValue(map) // Directly overwrite the data
                                        .addOnSuccessListener(unused -> {
                                            Toast.makeText(view.getContext(), "Attendance Updated!", Toast.LENGTH_SHORT).show(); //
                                            dialogPlus.dismiss();
                                            //////////////////
                                            android.app.AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
                                            alertbox.setMessage("Attendance Successfully Added!");
                                            alertbox.setTitle(" Roll:" + model.getROLL());
                                            alertbox.setPositiveButton("OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface arg0, int arg1) {
                                                        }
                                                    });
                                            alertbox.show();
                                            //////////////
                                            /*
                                    // Check and request SMS permission
                                            // Check and request SMS permission (using the Activity context)
                                            Activity activity = (Activity) view.getContext();
                                            if (ContextCompat.checkSelfPermission(activity, PERMISSION_SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                                                sendSms(model.getMOBILE(), constructSmsMessage(model, attendanceDate, attendanceStatus));
                                            } else {
                                                ActivityCompat.requestPermissions(activity, new String[]{PERMISSION_SEND_SMS}, SMS_PERMISSION_REQUEST_CODE);
                                            }

*/
                                            /////////////////////////////

                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(view.getContext(), "Error! Data Not Added!", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        //////////////////////////////////////////////////////////////////////////////////////////////
                    }
                });

                dialogPlus.show();

            }

            private void sendSms(String phoneNumber, String message) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    //  Toast.makeText(itemView.getContext(), "SMS sent successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    //   Toast.makeText(itemView.getContext(), "Error sending SMS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            private String constructSmsMessage(StudentModel model, String attendanceDate, String attendanceStatus) {
                // Customize this to your desired SMS format
                String message = "Dear Parent/Guardian,\n\n";
                message += "Attendance update for: " + model.getNAME() + "\n";
                message += "Class: PP\n";
                message += "Roll No: " + model.getROLL() + "\n";
                message += "Date: " + attendanceDate + "\n";
                message += "Status: " + attendanceStatus + "\n";
                message += "Regards,\n[Sadarpur FP School]";


                return message;
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_students, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView sname, sroll ,smobile,sclass,sid;

        CardView card_view;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            sname = itemView.findViewById(R.id.sname);
            sroll = itemView.findViewById(R.id.sroll);
            smobile = itemView.findViewById(R.id.smobile);
            sclass = itemView.findViewById(R.id.sclass);
            sid = itemView.findViewById(R.id.sid);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }

}