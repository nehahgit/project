package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class user_view_profile extends AppCompatActivity implements View.OnClickListener {
    TextView tv_name,tv_email,tv_phone,tv_address,tv_city,tv_district,tv_pin;
    Button btn_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_profile);
        tv_name=findViewById(R.id.textView25);
        tv_email=findViewById(R.id.textView26);
        tv_phone=findViewById(R.id.textView27);
        tv_address=findViewById(R.id.textView28);
        tv_city=findViewById(R.id.textView29);
        tv_district=findViewById(R.id.textView30);
        tv_pin=findViewById(R.id.textView31);
        btn_edit=findViewById(R.id.button11);
        btn_edit.setOnClickListener(this);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/and_user_profile/";



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

//                                JSONObject object = jsonObject.getJSONObject("data");

                                tv_name.setText(jsonObject.getString("name"));
                                tv_email.setText(jsonObject.getString("email"));
                                tv_phone.setText(jsonObject.getString("phone"));
                                tv_address.setText(jsonObject.getString("address"));
                                tv_city.setText(jsonObject.getString("city"));
                                tv_district.setText(jsonObject.getString("district"));
                                tv_pin.setText(jsonObject.getString("pin"));




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

                String id=sh.getString("lid","");
                params.put("lid",id);
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
        Intent i=new Intent(getApplicationContext(),edit_profile.class);
        startActivity(i);

    }
}