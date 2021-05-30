package com.example.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * The CalendarAdapter class provides adapters for CalendarUnits to be used in the MainActivity.
 * */

public class CalendarAdapter extends BaseAdapter {

    private List<CalendarUnit> list;
    private Context context;

    public CalendarAdapter(List<CalendarUnit> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CalendarUnit getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Initialise the calendar view, with units coloured according to their date and month.
     * @return a modified view of a calendar
     **/

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView;
        if (view == null) {
            textView = new TextView(context);
            textView.setPadding(5, 5, 5, 5);
            view = textView;
        } else {
            textView = (TextView) view;
        }

        CalendarUnit Unit = getItem(position);

        textView.setText(Unit.getDay() + "");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(Typeface.DEFAULT_BOLD);

        //Shade the calendar with different colours based on their date and month.
        if (Unit.isCurrentDay()) {
            textView.setBackgroundColor(Color.parseColor("#fd5f00"));
            textView.setTextColor(Color.WHITE);
        } else if (Unit.isCurrentMonth()) {
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(Color.BLACK);
        } else {
            textView.setBackgroundColor(Color.parseColor("#aaaaaa"));
            textView.setTextColor(Color.BLACK);
        }
        return textView;
    }
}
