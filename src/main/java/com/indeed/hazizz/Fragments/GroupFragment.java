package com.indeed.hazizz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.indeed.hazizz.R;

import java.util.ArrayList;

public class GroupFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group, container, false);

       // ListView mListView = (ListView) getView().findViewById(R.id.listView);

        ListView name = (ListView) v.findViewById(R.id.listView);

        Group group1 = new Group("groupName");

        ArrayList<Group> groupList = new ArrayList<>();
        groupList.add(group1);

        GroupListAdapter adapter = new GroupListAdapter(this, R.layout.adapter_view_layout, groupList);

        Log.e("hey", "im here lol");


       // return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_group, container, false);

    }




}
