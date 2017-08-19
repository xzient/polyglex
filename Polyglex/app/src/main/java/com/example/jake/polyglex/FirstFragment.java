package com.example.jake.polyglex;


import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import user.*;
import lexicon.*;
import word.*;
import instance.*;
import userList.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment



        String[] firstStrings = {
                "me",
                "you",
                "Sunset",
                "MidMorning",
                "Good Morning",
                "Breakfast",
                "MidMorning",
                "Lunch",
                "Afternoon",
                "Sunset",
                "Supper Time",
                "Lunch",
                "Afternoon",
                "Supper Time",
                "Lovely Night",
                "Chilly Dreams"
        };



        ListView lv = (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> liva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, firstStrings);
        lv.setAdapter(liva);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_first);

        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ((HomeActivity) getActivity()).refreshNow();
                        Toast.makeText(getContext(), "Refresh Layout working", Toast.LENGTH_LONG).show();
                    }
                }
        );

        return view;
    }
}