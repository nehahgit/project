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

public class custom_view_category extends BaseAdapter {
    String[]id,category_name;

    public Context context;

    public custom_view_category(Context appcontext,String[]id,String[]category_name)
    {
        this.context=appcontext;
        this.id=id;
        this.category_name=category_name;


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
            gridView=inflator.inflate(R.layout.custom_view_shop_category,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvrev=(TextView)gridView.findViewById(R.id.textView50);
        Button btn_material=(Button)gridView.findViewById(R.id.button19);
        Button btn_instrument=(Button)gridView.findViewById(R.id.button20);

        tvrev.setTextColor(Color.BLACK);

        tvrev.setText(category_name[i]);
//        tvttime.setText(time2[i]);

        btn_material.setTag(id[i]);

        btn_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("cid", id[i]);
                ed.commit();


                Intent ii=new Intent(context,view_material.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);


            }
        });
    btn_instrument.setTag(id[i]);
        btn_instrument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("cid", v.getTag().toString());
                ed.commit();


                Intent ii=new Intent(context,view_instruments.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);


            }
        });
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

//        String url="http://" + ip + ":5000/static/game/"+image[i]+".jpg";


//        Picasso.with(context).load(url). into(im);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);
        if(category_name[i].equalsIgnoreCase("Dance")){


            btn_material.setVisibility(View.VISIBLE);
            btn_instrument.setVisibility(View.INVISIBLE);

        }
        else{


            btn_material.setVisibility(View.INVISIBLE);
            btn_instrument.setVisibility(View.VISIBLE);



        }






        return gridView;
    }
}
