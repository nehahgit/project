package com.example.myapplicationnew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class custom_view_shop extends BaseAdapter {
    String[]id,shop_name,owner_name,email,city,district,pin,phone,image,status,LOGIN_id,shop_web,category;
    public Context context;

    public custom_view_shop(Context appcontext,String[]id,String[]shop_name,String[]owner_name,String[]email,String[]city,String[]district,String[]pin,String[]phone,String[]image,String[]status,String[]LOGIN_id,String[]shop_web,String[]category)
    {
        this.context=appcontext;
        this.id=id;
        this.shop_name=shop_name;
        this.city=city;
        this.district=district;
        this.pin=pin;
        this.phone=phone;
        this.email=email;
        this.image=image;
        this.shop_web=shop_web;
        this.status=status;
        this.LOGIN_id=LOGIN_id;
        this.owner_name=owner_name;
        this.category=category;



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
            gridView=inflator.inflate(R.layout.custom_view_shop,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.textView42);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView5);
        TextView tvowner=(TextView)gridView.findViewById(R.id.textView45);
        TextView tvcity=(TextView)gridView.findViewById(R.id.textView44);
        TextView tvphone=(TextView)gridView.findViewById(R.id.textView46);

        tvname.setTag(shop_web[i]);
        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sl=new Intent(Intent.ACTION_VIEW, Uri.parse(v.getTag().toString()));
                sl.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(sl);
            }
        });

        Button btn_cat=(Button)gridView.findViewById(R.id.button13);
        btn_cat.setTag(i);
        btn_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ik=Integer.parseInt(v.getTag().toString());


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sid", LOGIN_id[ik]);
                ed.putString("category", category[ik]);
                ed.commit();

                Intent ii=new Intent(context,view_shop_category.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);
            }
        });

        Button btn_gallery=(Button)gridView.findViewById(R.id.button14);
        btn_gallery.setTag(LOGIN_id[i]);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("i_lid", id[i]);
                ed.commit();

                Intent ii=new Intent(context,view_shop_gallery.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);


            }
        });
        Button btn_more=(Button)gridView.findViewById(R.id.button12);
        btn_more.setTag(LOGIN_id[i]);
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("sid", LOGIN_id[i]);
                ed.commit();
                Intent ii=new Intent(context,view_more_shop.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);

            }
        });


        tvname.setTextColor(Color.BLACK);
        tvowner.setTextColor(Color.BLACK);
        tvcity.setTextColor(Color.BLACK);
        tvphone.setTextColor(Color.BLACK);


        tvname.setText(shop_name[i]);
        tvowner.setText(owner_name[i]);
        tvcity.setText(city[i]);
        tvphone.setText(phone[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000"+image[i];

//        Picasso.with(context).load(url). into(im);
        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
