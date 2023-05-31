package com.example.myapplicationnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_batch extends BaseAdapter {
    String[]id,batch_name,no_of_seats,date,time1,time2,COURSE_id,INSTITUTE_id,course_name;
    public Context context;
    public custom_view_batch(Context appcontext,String[]id,String[]batch_name,String[]no_of_seats,String[]date,String[]time1,String[]time2,String[]COURSE_id,String[]INSTITUTE_id)
    {
        this.context=appcontext;
        this.id=id;
        this.batch_name=batch_name;
        this.no_of_seats=no_of_seats;
        this.date=date;
        this.time1=time1;
        this.time2=time2;
        this.COURSE_id=COURSE_id;
        this.INSTITUTE_id=INSTITUTE_id;
        this.course_name=course_name;




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
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_view_batch,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvbname=(TextView)gridView.findViewById(R.id.textView20);
        TextView tvseat=(TextView)gridView.findViewById(R.id.textView21);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView22);
        TextView tvftime=(TextView)gridView.findViewById(R.id.textView23);
        TextView tvttime=(TextView)gridView.findViewById(R.id.textView32);


        tvbname.setTextColor(Color.BLACK);
        tvseat.setTextColor(Color.BLACK);
        tvdate.setTextColor(Color.BLACK);
        tvftime.setTextColor(Color.BLACK);
        tvttime.setTextColor(Color.BLACK);


        tvbname.setText(batch_name[i]);
        tvseat.setText(no_of_seats[i]);
        tvdate.setText(date[i]);
        tvftime.setText(time1[i]);
        tvttime.setText(time2[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

//        String url="http://" + ip + ":5000/static/game/"+image[i]+".jpg";


//        Picasso.with(context).load(url). into(im);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
