package com.zydesoft.focus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShoppingListActivity extends Activity {

    private TextView backButton;
    private RecyclerView shoppingList;
    private ArrayList<ShoppingListItem> shoppingListItems = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));
        shoppingListItems.add(new ShoppingListItem(false,"Tea"));

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shoppingList = findViewById(R.id.incomplete_tasks);
        shoppingList.setHasFixedSize(true);
        shoppingList.setItemViewCacheSize(20);
        shoppingList.setDrawingCacheEnabled(true);
        shoppingList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        shoppingList.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        shoppingList.setAdapter(new ShoppingListAdapter());

    }

    public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public View parentView;
            public ViewHolder(View v) {
                super(v);
                parentView = v;
                mTextView = v.findViewById(R.id.task_title);
            }
        }

        public ShoppingListAdapter() {
                notifyDataSetChanged();
        }

        @Override
        public ShoppingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.incomplete_task, parent, false);
            ShoppingListAdapter.ViewHolder vh = new ShoppingListAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ShoppingListAdapter.ViewHolder holder, final int position) {
            holder.mTextView.setText(shoppingListItems.get(position).getName());
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public int getItemCount() {
            return shoppingListItems.size();
        }
    }

    class ShoppingListItem {
        boolean completed;
        String name;

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ShoppingListItem(boolean completed, String name) {
            this.completed = completed;
            this.name = name;
        }
    }

}
