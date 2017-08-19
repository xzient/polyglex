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


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        // Inflate the layout for this fragment
        String[] secondStrings = {
                "gas",
                "cook",
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

        ListView lv = (ListView) view.findViewById(R.id.listView2);

        ArrayAdapter<String> lva = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, secondStrings);
        lv.setAdapter(lva);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fragment_second);

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