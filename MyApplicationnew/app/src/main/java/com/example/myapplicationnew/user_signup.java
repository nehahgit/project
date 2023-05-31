package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user_signup extends AppCompatActivity implements View.OnClickListener {
    EditText ed_name,ed_email,ed_phone,ed_address,ed_city,ed_district,ed_pin,ed_password,ed_confirm;
    Button btn_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        ed_name=findViewById(R.id.editTextTextPersonName4);
        ed_email=findViewById(R.id.editTextTextPersonName5);
        ed_phone=findViewById(R.id.editTextTextPersonName6);
        ed_address=findViewById(R.id.editTextTextPersonName7);
        ed_city=findViewById(R.id.editTextTextPersonName8);
        ed_district=findViewById(R.id.editTextTextPersonName9);
        ed_pin=findViewById(R.id.editTextTextPersonName10);
        ed_password=findViewById(R.id.editTextTextPersonName11);
        ed_confirm=findViewById(R.id.editTextTextPersonName12);
        btn_signup=findViewById(R.id.button7);
        btn_signup.setOnClickListener(this);


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
        String password=ed_password.getText().toString();
        String confirm=ed_confirm.getText().toString();

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
        }
        if (password.length()==0){
            ed_password.setError("please enter the password");
        }
        if (confirm.length()==0){
            ed_confirm.setError("please enter the confirm password");
        }
        else{



            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/and_usersignup_post/";



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

                                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getApplicationContext(),login.class);
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

                    String id=sh.getString("uid","");
                    params.put("uid",id);
                    params.put("name",name);
                    params.put("email",email);
                    params.put("phone",phone);
                    params.put("address",address);
                    params.put("city",city);
                    params.put("district",district);
                    params.put("pin",pin);
                    params.put("password",password);
                    params.put("confirm",confirm);
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