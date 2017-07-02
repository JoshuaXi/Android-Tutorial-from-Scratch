package com.example.lion.ch031_flagsquizapp.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lion.ch031_flagsquizapp.Model.Ranking;
import com.example.lion.ch031_flagsquizapp.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lion on 7/2/2017.
 */

public class CustomAdapter extends BaseAdapter{

    private Context context;
    private List<Ranking> lstRanking;

    public CustomAdapter(Context context, List<Ranking> lstRanking) {
        this.context = context;
        this.lstRanking = lstRanking;
    }

    @Override
    public int getCount() {
        return lstRanking.size();
    }

    @Override
    public Object getItem(int position) {
        return lstRanking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, null);

        ImageView imgTop = (ImageView) view.findViewById(R.id.imgTop);
        TextView txtTop = (TextView) view.findViewById(R.id.txtTop);

        if(position==0) // top1
            imgTop.setImageResource(R.drawable.gold_medal);
        else if(position==1)
            imgTop.setImageResource(R.drawable.silver_medal);
        else
            imgTop.setImageResource(R.drawable.bronze_medal);
        txtTop.setText(String.valueOf(lstRanking.get(position).getScore()));
        return view;

    }
}
