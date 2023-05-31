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

public class custom_view_institution extends BaseAdapter {
    String[]id,manager,name,email,city,district,pin,phone,image,status,LOGIN_id,website;
    public Context context;

    public custom_view_institution(Context appcontext,String[]id,String[]manager,String[]name,String[]email,String[]city,String[]district,String[]pin,String[]phone,String[]image,String[]status,String[]LOGIN_id,String[]website)
    {
        this.context=appcontext;
        this.id=id;
        this.manager=manager;
        this.name=name;
        this.email=email;
        this.city=city;
        this.district=district;
        this.pin=pin;
        this.phone=phone;
        this.image=image;
        this.status=status;
        this.LOGIN_id=LOGIN_id;
        this.website=website;



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
            gridView=inflator.inflate(R.layout.custom_view_institution,null);

        }
        else
        {
            gridView=(View)convertView;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.textView);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView);
        TextView tvcity=(TextView)gridView.findViewById(R.id.textView2);
        TextView tvphone=(TextView)gridView.findViewById(R.id.textView3);
        Button btn_more=(Button)gridView.findViewById(R.id.button3);

        tvname.setTag(website[i]);
        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii=new Intent(Intent.ACTION_VIEW, Uri.parse(v.getTag().toString()));
                iii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(iii);
            }
        });


        btn_more.setTag(id[i]);
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("iid", v.getTag().toString());
                ed.commit();
                Intent ii=new Intent(context,view_institution_more.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);
            }
        });

        Button btn_request=(Button)gridView.findViewById(R.id.button5);
        btn_request.setTag(id[i]);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                final String maclis=sh.getString("mac_list","");
                String uid=sh.getString("uid","");
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/and_insti_request/";



                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(context, "Request Sended", Toast.LENGTH_SHORT).show();

                                           Intent i = new Intent(context,view_insti_request_status.class);
                                           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                           context.startActivity(i);
                                        }

                                    // }
                                    else {
                                        Toast.makeText(context, "Not found", Toast.LENGTH_LONG).show();
                                    }

                                }    catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();

                        String uid=sh.getString("lid","");
                        params.put("lid",uid);
                        params.put("i_lid",id[i]);
//                params.put("mac",maclis);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);




            }
        });

        Button btn_course=(Button)gridView.findViewById(R.id.button4);
        btn_course.setTag(LOGIN_id[i]);
        btn_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("i_id", id[i]);
                ed.commit();

                Intent ii=new Intent(context,view_course.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);
            }
        });

        Button btn_gallery=(Button)gridView.findViewById(R.id.button6);
        btn_gallery.setTag(LOGIN_id[i]);
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("i_id", id[i]);
                ed.commit();


                Intent ii=new Intent(context,view_insti_gallery.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);

            }
        });



        tvname.setTextColor(Color.BLACK);
        tvcity.setTextColor(Color.BLACK);
        tvphone.setTextColor(Color.BLACK);


        tvname.setText(name[i]);
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
