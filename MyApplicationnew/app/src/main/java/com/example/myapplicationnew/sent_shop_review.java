package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sent_shop_review extends AppCompatActivity implements View.OnClickListener {
    EditText ed_review;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_shop_review);
        ed_review = findViewById(R.id.editTextTextPersonName20);
        btn = findViewById(R.id.button15);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String review = ed_review.getText().toString();

        if (review.length() == 0) {
            ed_review.setError("Please type here");
        } else {


            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis = sh.getString("mac_list", "");
            String uid = sh.getString("uid", "");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/and_sent_shop_review/";


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
                                    Toast.makeText(getApplicationContext(), "Review submitted", Toast.LENGTH_SHORT).show();

//                                    SharedPreferences.Editor ed=sh.edit();
//                                    ed.putString("lid",jsonObj.getString("lid"));
//                                    ed.putString("type",jsonObj.getString("type"));
//                                    ed.commit();

                                    Intent i = new Intent(getApplicationContext(), view_shop_review.class);
                                    startActivity(i);


                                }


                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
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

                    String id = sh.getString("lid", "");
                    String sid = sh.getString("sid", "");
                    params.put("lid", id);
                    params.put("review", review);
                    params.put("sid", sid);

//                params.put("mac",maclis);

                    return params;
                }
            };


            int MY_SOCKET_TIMEOUT_MS = 100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);


        }
    }}
