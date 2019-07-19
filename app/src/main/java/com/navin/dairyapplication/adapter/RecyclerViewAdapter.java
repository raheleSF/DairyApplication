package com.navin.dairyapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.navin.dairyapplication.R;
import com.navin.dairyapplication.models.Task;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DiaryViewHolder> {

    List<Task> taskList;
    LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Task> tasks) {
        taskList = tasks;
        inflater = LayoutInflater.from(context);


    }
    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview,null);

        return  new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {

        Task task = taskList.get(position);
        holder.txt_title.setText(task.getTitle());
        holder.txt_date.setText(task.getDate());
        holder.txt_time.setText(task.getTime());



    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView txt_title;
        AppCompatTextView txt_time;
        AppCompatTextView txt_date;

        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title =itemView.findViewById(R.id.txt_title);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_time = itemView.findViewById(R.id.txt_time);


        }
    }

}
