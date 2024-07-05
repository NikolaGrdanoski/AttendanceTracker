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

public class StudentEventsAdapter extends RecyclerView.Adapter<StudentEventsAdapter.ViewHolder> {
    Context context;
    ArrayList<Events> coursesCArrayList;

    String username;

    String course;

    public StudentEventsAdapter(Context context, ArrayList<Events> coursesCArrayList, String username, String course) {
        this.context = context;
        this.coursesCArrayList = coursesCArrayList;
        this.username = username;
        this.course = course;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView etxtname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            etxtname = (TextView)itemView.findViewById(R.id.etxtname);

            etxtname.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();

            etxtname = (TextView)itemView.findViewById(R.id.etxtname);

            Intent intentE = new Intent(context, EventJoin.class);
            intentE.putExtra("course", course);
            intentE.putExtra("tUsername", username);
            intentE.putExtra("event", coursesCArrayList.get(p).getName());
            context.startActivity(intentE);
        }
    }

    @NonNull
    public StudentEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_event_layout, viewGroup, false);

        return new StudentEventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentEventsAdapter.ViewHolder holder, int position) {
        Events events = coursesCArrayList.get(position);

        holder.etxtname.setText(events.getName());
    }




    public int getItemCount() {
        return coursesCArrayList.size();
    }
}
