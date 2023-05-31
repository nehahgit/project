package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_shop extends AppCompatActivity {
    ListView lv;
    String[]id,shop_name,city,district,pin,phone,email,image,shop_web,status,LOGIN_id,owner_name,category;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop);
        lv=findViewById(R.id.shop);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/and_view_shop/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                id=new String[js.length()];
                                shop_name=new String[js.length()];
                                email=new String[js.length()];
                                city=new String[js.length()];
                                district=new String[js.length()];
                                pin=new String[js.length()];
                                phone=new String[js.length()];
                                image=new String[js.length()];
                                shop_web=new String[js.length()];
                                status=new String[js.length()];
                                LOGIN_id=new String[js.length()];
                                owner_name=new String[js.length()];
                                category=new String[js.length()];


//
//                                JSONArray js1= jsonObj.getJSONArray("rating");
//                                rating=new String[js1.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    id[i]=u.getString("s_id");
                                    shop_name[i]=u.getString("name");
                                    email[i]=u.getString("email");
                                    city[i]=u.getString("city");
                                    district[i]=u.getString("district");
                                    pin[i]=u.getString("pin");
                                    phone[i]=u.getString("phone");
                                    image[i]=u.getString("image");
                                    shop_web[i]=u.getString("website");
                                    status[i]=u.getString("status");
                                    LOGIN_id[i]=u.getString("LOGIN_id");
                                    owner_name[i]=u.getString("manager");
                                    category[i]=u.getString("category");



                                }
//                                for(int i=0;i<js1.length();i++)
//                                {
//                                    JSONObject u=js1.getJSONObject(i);
//                                    rating[i]=u.getString("rating");
//
//                                }


                                // ArrayAdapter<String> adpt=new ArrayAd2apter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,name);
//                                        gv.setAdapter(new Custom_view_visited_game(getApplicationContext(),name,gamecode));
                                lv.setAdapter(new custom_view_shop(getApplicationContext(),id,shop_name,owner_name,email,city,district,pin,phone,image,status,LOGIN_id,shop_web,category));
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("sid","");
                params.put("sid",id);
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
}