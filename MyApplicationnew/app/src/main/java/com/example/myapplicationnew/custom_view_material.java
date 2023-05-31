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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_view_material extends BaseAdapter {
    String[]id,name,rent_amount,description,image,CATEGORY_id,SHOP_id;
    public Context context;

    public custom_view_material(Context appcontext,String[]id,String[]name,String[]rent_amount,String[]description,String[]image,String[]CATEGORY_id,String[]SHOP_id)
    {
        this.context=appcontext;
        this.id=id;
        this.name=name;
        this.rent_amount=rent_amount;
        this.description=description;
        this.image=image;
        this.CATEGORY_id=CATEGORY_id;
        this.SHOP_id=SHOP_id;




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
            gridView=inflator.inflate(R.layout.custom_view_material,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.textView58);
        TextView tvrent=(TextView)gridView.findViewById(R.id.textView61);
        TextView tvdes=(TextView)gridView.findViewById(R.id.textView62);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView6);

        Button btn_request=(Button)gridView.findViewById(R.id.button21);
        btn_request.setTag(i);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ik=Integer.parseInt(v.getTag().toString());
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("iid",id[ik]);
                ed.putString("amt",rent_amount[ik]);
                ed.commit();
                Intent ii=new Intent(context,material_send_req.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);
            }
        });

        tvname.setTextColor(Color.BLACK);
        tvrent.setTextColor(Color.BLACK);
        tvdes.setTextColor(Color.BLACK);
//        tvrev.setTextColor(Color.BLACK);

        tvname.setText(name[i]);
        tvrent.setText(rent_amount[i]);
        tvdes.setText(description[i]);
//        tvttime.setText(time2[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000"+image[i];


//        Picasso.with(context).load(url). into(im);
        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
