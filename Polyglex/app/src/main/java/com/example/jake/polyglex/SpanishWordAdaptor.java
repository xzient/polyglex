package com.example.jake.polyglex;

/**
 * Created by Jake on 8/21/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;
import java.util.List;

public class SpanishWordAdaptor extends ArrayAdapter {
    public Context context;

    public SpanishWordAdaptor(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }

    /**
     * Holder for list items
     */
    private class ViewHolder{
        TextView titleText;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        SpanishWord word = (SpanishWord) getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        viewToUse = mInflater.inflate(R.layout.spanishword_list, null);

        holder = new ViewHolder();
        holder.titleText = (TextView)viewToUse.findViewById(R.id.spanishWordTextView);
        viewToUse.setTag(holder);

        holder.titleText.setText(word.getSpanishWordTitle());
        return viewToUse;
    }


}
