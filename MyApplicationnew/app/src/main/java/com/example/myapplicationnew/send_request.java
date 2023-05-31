package com.example.myapplicationnew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class send_request extends AppCompatActivity implements View.OnClickListener {

    EditText ed_fdate,ed_tdate;
    Button btn_sub;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        ed_fdate=findViewById(R.id.editTextTextPersonName26);
        ed_tdate=findViewById(R.id.editTextTextPersonName27);
        btn_sub=findViewById(R.id.button27);
        btn_sub.setOnClickListener(this);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };DatePickerDialog.OnDateSetListener date2 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel2();
            }
        };
        ed_fdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(send_request.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });ed_tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(send_request.this,date2,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        String fdate=ed_fdate.getText().toString();
        String tdate=ed_tdate.getText().toString();

        if (fdate.length()==0){
            ed_fdate.setError("Please enter a valid Date");
        }
        else if (tdate.length()==0){
            ed_tdate.setError("Please enter a Valid Date");
        }
        else{

            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":8000/myapp/and_send_instrument_request/";



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
                                    Toast.makeText(getApplicationContext(), "Request Sended", Toast.LENGTH_SHORT).show();

                                    Intent ii = new Intent(getApplicationContext(),home.class);
//                                    ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    getApplicationContext().startActivity(ii);
                                    startActivity(ii);
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

                    String uid=sh.getString("lid","");

                    params.put("mid",sh.getString("iid",""));
                    params.put("amt",sh.getString("amt",""));
                    params.put("lid",sh.getString("lid",""));
                    params.put("fdate",fdate);
                    params.put("tdate",tdate);

//                    params.put("rentamount",v.getTag().toString());
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

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ed_fdate.setText(dateFormat.format(myCalendar.getTime()));
    } private void updateLabel2(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ed_tdate.setText(dateFormat.format(myCalendar.getTime()));
    }
}