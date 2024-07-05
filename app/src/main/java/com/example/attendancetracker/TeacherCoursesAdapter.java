package com.example.attendancetracker;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TeacherCoursesAdapter extends RecyclerView.Adapter<TeacherCoursesAdapter.ViewHolder> {
    Context context;
    ArrayList<Courses> coursesArrayList;

    String username;

    public TeacherCoursesAdapter(Context context, ArrayList<Courses> coursesArrayList, String username) {
        this.context = context;
        this.coursesArrayList = coursesArrayList;
        this.username = username;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ttxtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ttxtname = (TextView)itemView.findViewById(R.id.ttxtname);

            ttxtname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();

            Intent intentD = new Intent(context, TeacherEnroll.class);
            intentD.putExtra("course", coursesArrayList.get(p).getName());
            intentD.putExtra("tUsername", username);
            context.startActivity(intentD);
        }
    }

    @NonNull
    @Override
    public TeacherCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teacher_single_item_layout, viewGroup, false);

        return new TeacherCoursesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherCoursesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Courses courses = coursesArrayList.get(position);

        holder.ttxtname.setText(courses.getName());
    }

    @Override
    public int getItemCount() {
        return coursesArrayList.size();
    }
}
