package com.example.jake.polyglex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import storeLocalData.User;


public class EnglishWordFragment extends Fragment {

    public EnglishWordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_englishword, container, false);
        // Inflate the layout for this fragment

        User test1 = new User("test", "test", "test@test.com", "2345");
        test1.addLexicon("English");
        test1.getLexicon("English").addWord("hello");
        test1.getLexicon("English").addWord("pineapple");
        test1.getLexicon("English").addWord("where");
        test1.getLexicon("English").addWord("friend");
        String[] englishWords = test1.getLexicon("English").getOrthographyArray();


        ListView lv = (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> liva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, englishWords);
        lv.setAdapter(liva);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_englishword);

        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ((Center) getActivity()).refreshNow();
                        Toast.makeText(getContext(), "Refresh Layout working", Toast.LENGTH_LONG).show();
                    }
                }
        );

        return view;
    }
}