package com.example.attendancetracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherCoursesClassesAdapter extends RecyclerView.Adapter<TeacherCoursesClassesAdapter.ViewHolder> {
    Context context;
    ArrayList<Courses> coursesCArrayList;

    String username;

    public TeacherCoursesClassesAdapter(Context context, ArrayList<Courses> coursesCArrayList, String username) {
        this.context = context;
        this.coursesCArrayList = coursesCArrayList;
        this.username = username;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tctxtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tctxtname = (TextView)itemView.findViewById(R.id.tctxtname);

            tctxtname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();

            tctxtname = (TextView)itemView.findViewById(R.id.tctxtname);

            Intent intentC = new Intent(context, Calendar.class);
            intentC.putExtra("aCourse", coursesCArrayList.get(p).getName());
            intentC.putExtra("tUsername", username);
            context.startActivity(intentC);
        }
    }

    @NonNull
    public TeacherCoursesClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teacher_courses_classes_layout, viewGroup, false);

        return new TeacherCoursesClassesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherCoursesClassesAdapter.ViewHolder holder, int position) {
        Courses courses = coursesCArrayList.get(position);
        holder.tctxtname.setText(courses.getName());
    }




    public int getItemCount() {
        return coursesCArrayList.size();
    }
}
