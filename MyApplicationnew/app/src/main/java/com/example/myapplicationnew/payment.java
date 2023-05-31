package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class payment extends AppCompatActivity implements View.OnClickListener , RadioGroup.OnCheckedChangeListener {

    EditText ed_holder,ed_acctnumber,ed_ifsc,ed_pin,amount,ed_edate;
    Button bpay;
    RadioButton rb_paytm,rb_gpay,rb_card;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ed_holder=findViewById(R.id.editTextTextPersonName21);
        ed_acctnumber=findViewById(R.id.editTextTextPersonName22);
//        ed_ifsc=findViewById(R.id.editTextTextPersonName22);
        ed_pin=findViewById(R.id.editTextTextPersonName23);
        amount=findViewById(R.id.editTextTextPersonName25);
        ed_edate=findViewById(R.id.editTextTextPersonName24);
        rb_paytm=findViewById(R.id.radioButton);
        rb_gpay=findViewById(R.id.radioButton2);
        rb_card=findViewById(R.id.radioButton3);
        rg=findViewById(R.id.radioGroup);
        bpay=findViewById(R.id.button23);

        bpay.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        amount.setText(sh.getString("amt",""));




    }

    @Override
    public void onClick(View v) {

        final String number=ed_acctnumber.getText().toString();
        final String name=ed_holder.getText().toString();
        final String cvv=ed_pin.getText().toString();
//        final String ifsc=ed_ifsc.getText().toString();
        final String edate=ed_edate.getText().toString();


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String url = sh.getString("uid", "");
        String hu = sh.getString("ip", "");

        url = "http://" + hu + ":8000/myapp/and_payment/";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                Toast.makeText(payment.this, "Payment Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),home.class));

                            }

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

                params.put("lid",sh.getString("lid",""));
                params.put("req",sh.getString("mreqid",""));
                params.put("accnt",number);
                params.put("hname",name);
                params.put("cvvv",cvv);
//                params.put("ifsc",ifsc);
                params.put("edate",edate);
                params.put("amt",sh.getString("amt",""));
                params.put("ptype",sh.getString("ptype",""));

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
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if(checkedId==R.id.radioButton){



            ed_holder.setVisibility(View.INVISIBLE);
            ed_acctnumber.setVisibility(View.INVISIBLE);
            ed_pin.setVisibility(View.INVISIBLE);
            ed_edate.setVisibility(View.INVISIBLE);




        }
        else if(checkedId==R.id.radioButton2){



            ed_holder.setVisibility(View.INVISIBLE);
            ed_acctnumber.setVisibility(View.INVISIBLE);
            ed_pin.setVisibility(View.INVISIBLE);
            ed_edate.setVisibility(View.INVISIBLE);





        }
        else {




            ed_holder.setVisibility(View.VISIBLE);
            ed_acctnumber.setVisibility(View.VISIBLE);
            ed_pin.setVisibility(View.VISIBLE);
            ed_edate.setVisibility(View.VISIBLE);




        }




    }
}