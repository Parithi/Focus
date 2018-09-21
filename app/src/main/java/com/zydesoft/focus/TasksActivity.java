package com.zydesoft.focus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zydesoft.focus.fragments.AppListFragment;

import java.util.ArrayList;
import java.util.Date;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TasksActivity extends Activity {

    private TextView backButton;
    private RecyclerView incompleteTasksView;
    private RecyclerView completedTasksView;

    private ArrayList<Task> completedTasks = new ArrayList<>();
    private ArrayList<Task> incompleteTasks = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));
        incompleteTasks.add(new Task(false,"Drink Tea",null,null));


        completedTasks.add(new Task(false,"Drink Tea",null,null));
        completedTasks.add(new Task(false,"Drink Tea",null,null));
        completedTasks.add(new Task(false,"Drink Tea",null,null));
        completedTasks.add(new Task(false,"Drink Tea",null,null));
        completedTasks.add(new Task(false,"Drink Tea",null,null));
        completedTasks.add(new Task(false,"Drink Tea",null,null));


        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        incompleteTasksView = findViewById(R.id.incomplete_tasks);
        incompleteTasksView.setHasFixedSize(true);
        incompleteTasksView.setItemViewCacheSize(20);
        incompleteTasksView.setDrawingCacheEnabled(true);
        incompleteTasksView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        incompleteTasksView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        completedTasksView = findViewById(R.id.completed_tasks);
        completedTasksView.setHasFixedSize(true);
        completedTasksView.setItemViewCacheSize(20);
        completedTasksView.setDrawingCacheEnabled(true);
        completedTasksView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        completedTasksView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        completedTasksView.setAdapter(new CompletedTasksAdapter());
        incompleteTasksView.setAdapter(new IncompleteTasksAdapter());

    }

    public class CompletedTasksAdapter extends RecyclerView.Adapter<CompletedTasksAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public View parentView;
            public ViewHolder(View v) {
                super(v);
                parentView = v;
                mTextView = v.findViewById(R.id.task_title);
            }
        }

        public CompletedTasksAdapter() {
                notifyDataSetChanged();
        }

        @Override
        public CompletedTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_task, parent, false);
            CompletedTasksAdapter.ViewHolder vh = new CompletedTasksAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull CompletedTasksAdapter.ViewHolder holder, final int position) {
            holder.mTextView.setText(completedTasks.get(position).getTitle());
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public int getItemCount() {
            return completedTasks.size();
        }
    }

    public class IncompleteTasksAdapter extends RecyclerView.Adapter<IncompleteTasksAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public View parentView;
            public ViewHolder(View v) {
                super(v);
                parentView = v;
                mTextView = v.findViewById(R.id.task_title);
            }
        }

        public IncompleteTasksAdapter() {
            notifyDataSetChanged();
        }

        @Override
        public IncompleteTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.incomplete_task, parent, false);
            IncompleteTasksAdapter.ViewHolder vh = new IncompleteTasksAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull IncompleteTasksAdapter.ViewHolder holder, final int position) {
            holder.mTextView.setText(incompleteTasks.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return incompleteTasks.size();
        }
    }

    class Task{
        boolean completed;
        String title;
        Date startTime;
        Date endTime;

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public Task(boolean completed, String title, Date startTime, Date endTime) {
            this.completed = completed;
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}
