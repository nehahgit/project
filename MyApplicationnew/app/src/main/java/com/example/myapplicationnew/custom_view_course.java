package com.example.myapplicationnew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_view_course extends BaseAdapter {
    String[]id,course_name,course_fee,course_duration,class_duration,available_seats,INSTITUTE_id;
    public Context context;
    public custom_view_course(Context appcontext,String[]id,String[]course_name,String[]course_fee,String[]course_duration,String[]class_duration,String[]available_seats,String[]INSTITUTE_id)
    {
        this.context=appcontext;
        this.id=id;
        this.course_name=course_name;
        this.course_fee=course_fee;
        this.course_duration=course_duration;
        this.class_duration=class_duration;
        this.available_seats=available_seats;
        this.INSTITUTE_id=INSTITUTE_id;




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
            gridView=inflator.inflate(R.layout.custom_view_course,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvcname=(TextView)gridView.findViewById(R.id.textView37);
        TextView tvcfee=(TextView)gridView.findViewById(R.id.textView38);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);
        TextView tvduration=(TextView)gridView.findViewById(R.id.textView39);
        TextView tvcduration=(TextView)gridView.findViewById(R.id.textView40);
        TextView tvseat=(TextView)gridView.findViewById(R.id.textView41);
        Button btn_batch=(Button)gridView.findViewById(R.id.button9);
        btn_batch.setTag(id[i]);
        btn_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("c_id", id[i]);
                ed.commit();

                Intent ii=new Intent(context,view_batch.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);
            }
        });


        tvcname.setTextColor(Color.BLACK);
        tvcfee.setTextColor(Color.BLACK);
        tvduration.setTextColor(Color.BLACK);
        tvcduration.setTextColor(Color.BLACK);
        tvseat.setTextColor(Color.BLACK);


        tvcname.setText(course_name[i]);
        tvcfee.setText(course_fee[i]);
        tvduration.setText(course_duration[i]);
        tvcduration.setText(class_duration[i]);
        tvseat.setText(available_seats[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

//        String url="http://" + ip + ":5000/static/game/"+image[i]+".jpg";


//        Picasso.with(context).load(url). into(im);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
