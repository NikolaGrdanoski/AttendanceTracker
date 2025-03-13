package com.example.attendancetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    Context context;
    ArrayList<Events> coursesCArrayList;

    String username;

    String course;

    String event;

    public AttendanceAdapter(Context context, ArrayList<Events> coursesCArrayList, String username, String course, String event) {
        this.context = context;
        this.coursesCArrayList = coursesCArrayList;
        this.username = username;
        this.course = course;
        this.event = event;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView atxtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            atxtname = (TextView)itemView.findViewById(R.id.atxtname);

            atxtname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();

            atxtname = (TextView)itemView.findViewById(R.id.atxtname);
        }
    }

    @NonNull
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_attendance_layout, viewGroup, false);

        return new AttendanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.ViewHolder holder, int position) {
        Events events = coursesCArrayList.get(position);

        holder.atxtname.setText(events.getName());
    }




    public int getItemCount() {
        return coursesCArrayList.size();
    }
}
