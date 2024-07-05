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

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {
    Context context;

    ArrayList<Surveys> surveysClassRatingArrayList;

    ArrayList<Surveys> surveysTeacherRatingArrayList;

    ArrayList<Surveys> surveysMaterialsRatingArrayList;

    String event;

    public SurveyAdapter(Context context, ArrayList<Surveys> surveysClassRatingArrayList, ArrayList<Surveys> surveysTeacherRatingArrayList, ArrayList<Surveys> surveysMaterialsRatingArrayList, String event) {
        this.context = context;
        this.surveysClassRatingArrayList = surveysClassRatingArrayList;
        this.surveysTeacherRatingArrayList = surveysTeacherRatingArrayList;
        this.surveysMaterialsRatingArrayList = surveysMaterialsRatingArrayList;
        this.event = event;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView surveyClassRating;

        public TextView surveyTeacherRating;

        public TextView surveyMaterialsRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            surveyClassRating = (TextView)itemView.findViewById(R.id.idSurveyClassRating);

            surveyTeacherRating = (TextView)itemView.findViewById(R.id.idSurveyTeacherRating);

            surveyMaterialsRating = (TextView)itemView.findViewById(R.id.idSurveyMaterialsRating);
        }

        @Override
        public void onClick(View v) {
            int p = getAdapterPosition();
        }
    }

    @NonNull
    public SurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_survey_layout, viewGroup, false);

        return new SurveyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyAdapter.ViewHolder holder, int position) {
        Surveys surveys = surveysClassRatingArrayList.get(position);
        Surveys surveys1 = surveysTeacherRatingArrayList.get(position);
        Surveys surveys2 = surveysMaterialsRatingArrayList.get(position);

        holder.surveyClassRating.setText(surveys.getName());
        holder.surveyTeacherRating.setText(surveys1.getName());
        holder.surveyMaterialsRating.setText(surveys2.getName());
    }




    public int getItemCount() {

        return surveysClassRatingArrayList.size();
    }
}
