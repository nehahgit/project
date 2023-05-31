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

public class custom_view_material_request_status extends BaseAdapter {
    String[]id,date,status,MATERIAL_id,USER_id,rent_amount,from_date,to_date,total_amount;
    public Context context;
    public custom_view_material_request_status(Context appcontext,String[]id,String[]date,String[]status,String[]MATERIAL_id,String[]USER_id,String[]rent_amount,String[]from_date,String[]to_date,String[]total_amount)
    {
        this.context=appcontext;
        this.id=id;
        this.date=date;
        this.status=status;
        this.MATERIAL_id=MATERIAL_id;
        this.USER_id=USER_id;
        this.rent_amount=rent_amount;
        this.from_date=from_date;
        this.to_date=to_date;
        this.total_amount=total_amount;




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
            gridView=inflator.inflate(R.layout.custom_view_maretial_request_status,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView80);
        TextView tvmat=(TextView)gridView.findViewById(R.id.textView81);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);
        TextView fdate=(TextView)gridView.findViewById(R.id.textView83);
        TextView tdate=(TextView)gridView.findViewById(R.id.textView93);
        TextView tamount=(TextView)gridView.findViewById(R.id.textView96);
        TextView tvstatus=(TextView)gridView.findViewById(R.id.textView82);


        tvdate.setTextColor(Color.BLACK);
        tvmat.setTextColor(Color.BLACK);
        fdate.setTextColor(Color.BLACK);
        tdate.setLinkTextColor(Color.BLACK);
        tamount.setLinkTextColor(Color.BLACK);
        tvstatus.setTextColor(Color.BLACK);


        tvdate.setText(date[i]);
        tvmat.setText(MATERIAL_id[i]);
        fdate.setText(from_date[i]);
        tdate.setText(to_date[i]);
        tamount.setText(total_amount[i]);
        tvstatus.setText(status[i]);




        tvdate.setText(date[i]);
        tvmat.setText(MATERIAL_id[i]);
        fdate.setText(from_date[i]);
        tdate.setText(to_date[i]);
        tamount.setText(total_amount[i]);
        tvstatus.setText(status[i]);






        Button payment=(Button) gridView.findViewById(R.id.button24);
//
        payment.setTag(i);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int ik=Integer.parseInt(view.getTag().toString());
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("mreqid", id[ik]);
                ed.putString("amt", rent_amount[ik]);
                ed.putString("ptype", "matr");
                ed.commit();
                Intent i=new Intent(context,payment.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });



        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

//        String url="http://" + ip + ":5000/static/game/"+image[i]+".jpg";


//        Picasso.with(context).load(url). into(im);
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}
