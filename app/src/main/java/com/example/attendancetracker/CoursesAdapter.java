package com.example.attendancetracker;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    Context context;
    ArrayList<Courses> coursesArrayList;

    String username;

    public CoursesAdapter(Context context, ArrayList<Courses> coursesArrayList, String username) {
        this.context = context;
        this.coursesArrayList = coursesArrayList;
        this.username = username;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = (TextView)itemView.findViewById(R.id.txtname);

            txtname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();

            Intent intentC = new Intent(context, Enroll.class);
            intentC.putExtra("course", coursesArrayList.get(p).getName());
            intentC.putExtra("Username", username);
            context.startActivity(intentC);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Courses courses = coursesArrayList.get(position);

        holder.txtname.setText(courses.getName());
    }

    @Override
    public int getItemCount() {
        return coursesArrayList.size();
    }



}
