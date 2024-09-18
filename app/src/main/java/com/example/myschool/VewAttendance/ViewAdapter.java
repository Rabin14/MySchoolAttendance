package com.example.myschool.VewAttendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myschool.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ViewAdapter extends FirebaseRecyclerAdapter<AttendanceModel,ViewAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ViewAdapter(@NonNull FirebaseRecyclerOptions<AttendanceModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull AttendanceModel model) {
        holder.sname.setText(model.getE_Name());
        holder.sroll.setText(model.getD_Roll());
        holder.sclass.setText(model.getC_Class());
        holder.sstatus.setText(model.getF_Status());




    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_attendance, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView sname, sroll, sstatus,sclass;

        CardView card_view;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            sname = itemView.findViewById(R.id.sname);
            sroll = itemView.findViewById(R.id.sroll);
            sstatus = itemView.findViewById(R.id.sstatus);
            sclass = itemView.findViewById(R.id.sclass);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}