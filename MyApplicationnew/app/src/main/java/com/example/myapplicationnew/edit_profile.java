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

public class edit_profile extends AppCompatActivity implements View.OnClickListener {
    EditText ed_name,ed_email,ed_phone,ed_address,ed_city,ed_district,ed_pin;
    Button btn_edit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ed_name=findViewById(R.id.editTextTextPersonName3);
        ed_email=findViewById(R.id.editTextTextPersonName13);
        ed_phone=findViewById(R.id.editTextTextPersonName14);
        ed_address=findViewById(R.id.editTextTextPersonName15);
        ed_city=findViewById(R.id.editTextTextPersonName16);
        ed_district=findViewById(R.id.editTextTextPersonName17);
        ed_pin=findViewById(R.id.editTextTextPersonName18);
        btn_edit=findViewById(R.id.button2);
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

                                ed_name.setText(jsonObject.getString("name"));
                                ed_email.setText(jsonObject.getString("email"));
                                ed_phone.setText(jsonObject.getString("phone"));
                                ed_address.setText(jsonObject.getString("address"));
                                ed_city.setText(jsonObject.getString("city"));
                                ed_district.setText(jsonObject.getString("district"));
                                ed_pin.setText(jsonObject.getString("pin"));




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
        String name=ed_name.getText().toString();
        String email=ed_email.getText().toString();
        String phone=ed_phone.getText().toString();
        String address=ed_address.getText().toString();
        String city=ed_city.getText().toString();
        String district=ed_district.getText().toString();
        String pin=ed_pin.getText().toString();

        if (name.length()==0){
            ed_name.setError("please enter the name");
        }
        if (email.length()==0){
            ed_email.setError("please enter the email");
        }
        if (phone.length()==0){
            ed_phone.setError("please enter the phone number");
        }
        if (address.length()==0){
            ed_address.setError("please enter the address");
        }
        if (city.length()==0){
            ed_city.setError("please enter the city");
        }
        if (district.length()==0){
            ed_district.setError("please enter the district");
        }else{


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("lid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/and_edit_profile/";



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

                                    Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getApplicationContext(),user_view_profile.class);
                                    startActivity(i);



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
                    params.put("name",name);
                    params.put("email",email);
                    params.put("phone",phone);
                    params.put("address",address);
                    params.put("city",city);
                    params.put("district",district);
                    params.put("pin",pin);
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
}