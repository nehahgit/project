package com.example.myapplicationnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_view_insti_gallery extends BaseAdapter {
    String[]id,image1,image2,image3,INSTITUTE_id;
    public Context context;

    public custom_view_insti_gallery(Context appcontext,String[]id,String[]image1,String[]image2,String[]image3,String[]INSTITUTE_id)
    {
        this.context=appcontext;
        this.id=id;
        this.image1=image1;
        this.image2=image2;
        this.image3=image3;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_view_insti_gallery,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        ImageView im1=(ImageView) gridView.findViewById(R.id.imageView2);
        ImageView im2=(ImageView) gridView.findViewById(R.id.imageView3);
        ImageView im3=(ImageView) gridView.findViewById(R.id.imageView4);

//        im1.setText(name[i]);
//        im2.setText(city[i]);
//        im3.setText(phone[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url1="http://" + ip + ":8000"+image1[i];
        String url2="http://" + ip + ":8000"+image2[i];
        String url3="http://" + ip + ":8000"+image3[i];


        Picasso.with(context).load(url1).into(im1);
        Picasso.with(context).load(url2).into(im2);
        Picasso.with(context).load(url3).into(im3);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
