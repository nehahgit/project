package com.example.myapplicationnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_insti_review extends BaseAdapter {
    String[]id,review,INSTITUTE_id,USER_id,date,uname;
    public Context context;

    public custom_view_insti_review(Context appcontext,String[]id,String[]review,String[]INSTITUTE_id,String[]USER_id,String[]date,String[]uname)
    {
        this.context=appcontext;
        this.id=id;
        this.review=review;
        this.INSTITUTE_id=INSTITUTE_id;
        this.USER_id=USER_id;
        this.date=date;
        this.uname=uname;





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
            gridView=inflator.inflate(R.layout.custom_view_insti_reviews,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView59);
        TextView tvrev=(TextView)gridView.findViewById(R.id.textView60);
        TextView tvuser=(TextView)gridView.findViewById(R.id.textView78);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);
//        TextView tvdate=(TextView)gridView.findViewById(R.id.textView22);
//        TextView tvftime=(TextView)gridView.findViewById(R.id.textView23);
//        TextView tvttime=(TextView)gridView.findViewById(R.id.textView32);


//        tvbname.setTextColor(Color.BLACK);
//        tvseat.setTextColor(Color.BLACK);
        tvdate.setTextColor(Color.BLACK);
        tvrev.setTextColor(Color.BLACK);
        tvuser.setTextColor(Color.BLACK);
//        tvttime.setTextColor(Color.BLACK);


//        tvbname.setText(batch_name[i]);
//        tvseat.setText(no_of_seats[i]);
        tvdate.setText(date[i]);
        tvrev.setText(review[i]);
        tvuser.setText(uname[i]);
//        tvttime.setText(time2[i]);


        return gridView;
    }
}
