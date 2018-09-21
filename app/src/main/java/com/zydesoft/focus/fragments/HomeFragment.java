package com.zydesoft.focus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zydesoft.focus.R;
import com.zydesoft.focus.SettingsActivity;
import com.zydesoft.focus.ShoppingListActivity;
import com.zydesoft.focus.TasksActivity;

public class HomeFragment extends Fragment{

    private static final String DEBUG_TAG = "HomeFragment";
    private ScrollView parentLayout;
    private TextView tasksButton;
    private TextView shoppingListButton;
    private TextView settingsButton;

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View rootView = view;
        parentLayout = rootView.findViewById(R.id.parent_layout);
        tasksButton = rootView.findViewById(R.id.tasks_button);

        tasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TasksActivity.class));
            }
        });

        shoppingListButton = view.findViewById(R.id.shopping_list_button);

        shoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShoppingListActivity.class));
            }
        });

        shoppingListButton = view.findViewById(R.id.settings_button);
        shoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });
    }
}
