package com.example.myapplicationnew;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_apprating extends BaseAdapter {
    String[]id,rating,date,USER_id;
    public Context context;
    public custom_view_apprating(Context appcontext, String[] id, String[] rating, String[] date,String[] USER_id) {
        this.context = appcontext;
        this.id = id;
        this.rating = rating;
        this.date = date;
        this.USER_id = USER_id;


    }

    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if(view==null)
        {
            gridView=inflator.inflate(R.layout.custom_view_apprating,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView66);
        TextView rat=(TextView)gridView.findViewById(R.id.textView76);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);

        tvdate.setTextColor(Color.BLACK);
        rat.setTextColor(Color.BLACK);
        tvdate.setText(date[i]);
        rat.setText(rating[i]);



        return gridView;
    }
}
