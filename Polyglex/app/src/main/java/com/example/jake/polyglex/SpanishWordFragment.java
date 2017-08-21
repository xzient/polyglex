package com.example.jake.polyglex;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jake.polyglex.dummy.DummyContent;
import com.example.jake.polyglex.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;
import word.*;
import user.*;



public class SpanishWordFragment extends Fragment {

    public SpanishWordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spanishword, container, false);
        // Inflate the layout for this fragment

        String[] spanishWords = {
                "palabra",
                "persona"
        };



        ListView lv = (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> liva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, spanishWords);
        lv.setAdapter(liva);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_spanishword);

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