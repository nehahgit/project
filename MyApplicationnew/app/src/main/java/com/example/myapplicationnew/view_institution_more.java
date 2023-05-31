package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class view_institution_more extends AppCompatActivity implements View.OnClickListener {
    TextView tv_manager,tv_email,tv_district,tv_pin,tv_website;
    Button btn_review,btn_view_review;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_institution_more);
        tv_manager=findViewById(R.id.textView9);
        tv_email=findViewById(R.id.textView10);
        tv_district=findViewById(R.id.textView11);
        tv_pin=findViewById(R.id.textView12);
        tv_website=findViewById(R.id.textView13);
        btn_review=findViewById(R.id.button8);
        btn_view_review=findViewById(R.id.button10);
        btn_review.setOnClickListener(this);
        btn_view_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent ii=new Intent(getApplicationContext(),view_insti_reviews.class);
                startActivity(ii);

            }
        });

        tv_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii=new Intent(Intent.ACTION_VIEW, Uri.parse(v.getTag().toString()));
                startActivity(iii);
            }
        });


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/and_view_insti_more/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equalsIgnoreCase("ok")) {

                                JSONObject object = jsonObject.getJSONObject("data");

                                tv_manager.setText(object.getString("manager"));
                                tv_email.setText(object.getString("email"));
//                                tv_phone.setText(jsonObject.getString("phone"));
//                                tv_address.setText(jsonObject.getString("address"));
//                                tv_city.setText(jsonObject.getString("city"));
                                tv_district.setText(object.getString("district"));
                                tv_pin.setText(object.getString("pin"));
                                tv_website.setText(object.getString("website"));
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

                String id=sh.getString("iid","");
                params.put("iid",id);
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

    @Override
    public void onClick(View v) {


        Intent i=new Intent(getApplicationContext(),sent_insti_review.class);
        startActivity(i);
    }
}