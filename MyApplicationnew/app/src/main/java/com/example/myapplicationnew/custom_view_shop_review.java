package com.example.myapplicationnew;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_shop_review  extends BaseAdapter{
    String[] id, review, SHOP_id, USER_id, date;
    public Context context;

    public custom_view_shop_review(Context appcontext, String[] id, String[] review, String[] SHOP_id, String[] USER_id, String[] date) {
        this.context = appcontext;
        this.id = id;
        this.review = review;
        this.SHOP_id = SHOP_id;
        this.USER_id = USER_id;
        this.date = date;


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
            gridView=inflator.inflate(R.layout.custom_view_shop_review,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView71);
        TextView tvrev=(TextView)gridView.findViewById(R.id.textView73);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);
//        TextView tvdate=(TextView)gridView.findViewById(R.id.textView22);
//        TextView tvftime=(TextView)gridView.findViewById(R.id.textView23);
//        TextView tvttime=(TextView)gridView.findViewById(R.id.textView32);


//        tvbname.setTextColor(Color.BLACK);
//        tvseat.setTextColor(Color.BLACK);
        tvdate.setTextColor(Color.BLACK);
        tvrev.setTextColor(Color.BLACK);
//        tvttime.setTextColor(Color.BLACK);


//        tvbname.setText(batch_name[i]);
//        tvseat.setText(no_of_seats[i]);
        tvdate.setText(date[i]);
        tvrev.setText(review[i]);
//        tvttime.setText(time2[i]);


        return gridView;
    }
}