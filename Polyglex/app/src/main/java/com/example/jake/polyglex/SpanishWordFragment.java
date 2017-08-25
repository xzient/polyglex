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


public class SpanishWordFragment extends Fragment {

    public SpanishWordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spanishword, container, false);
        // Inflate the layout for this fragment

        User test1 = new User("test", "test", "test@test.com", "2345");
        test1.addLexicon("Spanish");
        test1.getLexicon("Spanish").addWord("perro");
        test1.getLexicon("Spanish").addWord("Islandia");
        test1.getLexicon("Spanish").addWord("joputa");
        test1.getLexicon("Spanish").addWord("política");
        test1.getLexicon("Spanish").addWord("amigo");
        test1.getLexicon("Spanish").addWord("coche");
        test1.getLexicon("Spanish").addWord("amistad");
        test1.getLexicon("Spanish").addWord("variedad");
        test1.getLexicon("Spanish").addWord("enemistad");
        test1.getLexicon("Spanish").addWord("circunstancia");
        test1.getLexicon("Spanish").addWord("atrevimiento");
        test1.getLexicon("Spanish").addWord("lingüísticamente");
        test1.getLexicon("Spanish").addWord("arrecho");
        test1.getLexicon("Spanish").addWord("apasionadamente");
        test1.getLexicon("Spanish").addWord("monitor");
        test1.getLexicon("Spanish").addWord("libertinaje");
        test1.getLexicon("Spanish").addWord("baloncesto");
        test1.getLexicon("Spanish").addWord("bollera");
        String[] spanishWords = test1.getLexicon("Spanish").getOrthographyArray();
        //This method is not working try the other way
        //String[] spanishWords = {"un", "dos", "tres"};



        ListView lv = (ListView) view.findViewById(R.id.listView);

        ArrayAdapter<String> liva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, spanishWords);
        lv.setAdapter(liva);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_spanishword);

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