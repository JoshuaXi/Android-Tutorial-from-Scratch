package com.example.lion.customspinner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lion on 6/27/2017.
 */

public class SpinnerAdapter extends BaseAdapter {
    //TODO
    private List<String> lstData;
    private Activity activity;
    private LayoutInflater inflater;

    public SpinnerAdapter(List<String> lstData, Activity activity) {
        this.lstData = lstData;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null)
            view = inflater.inflate(R.layout.spinner_item, null);

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(lstData.get(position));
        return view;
    }

    //TODO


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        LinearLayout ll = (LinearLayout) view;
        TextView tv = (TextView) ll.findViewById(R.id.textView);
        tv.setGravity(Gravity.LEFT);
        tv.setTextColor(Color.parseColor("#333639"));
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return view;
    }
}
