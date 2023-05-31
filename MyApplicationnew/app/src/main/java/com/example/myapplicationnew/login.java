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
import android.widget.TextView;
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

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText ed_username,ed_password;
    Button btn_login;
    TextView tv_signup;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_temp);
        ed_username=findViewById(R.id.editTextTextPersonName);
        ed_password=findViewById(R.id.editTextTextPersonName2);
        btn_login=findViewById(R.id.button);
        tv_signup=findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),user_signup.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View v) {
        String username = ed_username.getText().toString();
        String password = ed_password.getText().toString();
        if (username.length() == 0) {
            ed_username.setError("please enter your username");
        } else if (password.length() == 0) {
            ed_password.setError("Please enter the password");
        } else {




            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/and_user_login/";



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

                                    SharedPreferences.Editor ed=sh.edit();
                                    ed.putString("lid",jsonObj.getString("lid"));
                                    ed.putString("type",jsonObj.getString("type"));
                                    ed.commit();

                                    if(jsonObj.getString("type").equalsIgnoreCase("user")){
                                        Intent i = new Intent(getApplicationContext(),home.class);
                                        startActivity(i);
                                    }

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
                    params.put("username",username);
                    params.put("password",password);
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