package com.zydesoft.focus.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zydesoft.focus.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppListFragment extends Fragment{

    RecyclerView appsList;
    LinearLayoutManager layoutManager;
    AppListAdapter appListAdapter;
    private PackageManager manager;
    private ArrayList<AppDetail> appListArray = new ArrayList();

    public static AppListFragment newInstance() {
        AppListFragment f = new AppListFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_apps_list,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View rootView = view;
        appsList = rootView.findViewById(R.id.app_list_recyclerview);
        appsList.setHasFixedSize(true);
        appsList.setItemViewCacheSize(20);
        appsList.setDrawingCacheEnabled(true);
        appsList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        layoutManager = new LinearLayoutManager(getActivity());
        appsList.setLayoutManager(layoutManager);
        manager = getActivity().getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = manager.queryIntentActivities( mainIntent, 0);
        for (int i = 0; i < pkgAppsList.size(); i++) {
            appListArray.add(new AppDetail(pkgAppsList.get(i).loadLabel(getActivity().getPackageManager()).toString(),pkgAppsList.get(i).activityInfo.packageName));
        }

        Collections.sort(appListArray, new AppComparator());

        appListAdapter = new AppListAdapter();
        appsList.setAdapter(appListAdapter);
    }

    public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public View parentView;
            public ViewHolder(View v) {
                super(v);
                parentView = v;
                mTextView = v.findViewById(R.id.app_name);
            }
        }

        public AppListAdapter() {

        }

        @Override
        public AppListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_list_row, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.mTextView.setText(appListArray.get(position).getName());
            holder.parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getActivity()!=null){
                        Intent i = manager.getLaunchIntentForPackage(appListArray.get(position).packageName);
                        getActivity().startActivity(i);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return appListArray.size();
        }
    }

    public class AppDetail{
        String name;
        String packageName;

        public AppDetail(String name, String packageName) {
            this.name = name;
            this.packageName = packageName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
    }

    class AppComparator implements Comparator<AppDetail> {

        // Sorting in ascending order of name
        public int compare(AppDetail a, AppDetail b) {
            return a.getName().compareTo(b.getName());
        }
    }
}
