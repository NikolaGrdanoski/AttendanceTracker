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

public class CoursesClassesAdapter extends RecyclerView.Adapter<CoursesClassesAdapter.ViewHolder> {
    Context context;
    ArrayList<Courses> coursesCArrayList;

    String username;

    public CoursesClassesAdapter(Context context, ArrayList<Courses> coursesCArrayList, String username) {
        this.context = context;
        this.coursesCArrayList = coursesCArrayList;
        this.username = username;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView ctxtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ctxtname = (TextView)itemView.findViewById(R.id.ctxtname);

            ctxtname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();

            ctxtname = (TextView)itemView.findViewById(R.id.ctxtname);


            Intent intentC = new Intent(context, StudentEvents.class);
            intentC.putExtra("course", coursesCArrayList.get(p).getName());
            intentC.putExtra("aUsername", username);
            context.startActivity(intentC);
        }
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_courses_classes, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesClassesAdapter.ViewHolder holder, int position) {
        Courses courses = coursesCArrayList.get(position);

        holder.ctxtname.setText(courses.getName());
    }




    public int getItemCount() {
        return coursesCArrayList.size();
    }
}
