package com.mis.smakash.fotonapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SixHourActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    public static String URL_SERVICE_SIXHOUR = "http://mis.digital:7779/genericservice/api/v0/login/";
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_hour);

        Intent selProIntent = getIntent();
        userId = selProIntent.getStringExtra("UserId");
        getSixHourService(userId);
    }

    private void getSixHourService(final String userId) {
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_SERVICE_SIXHOUR, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    //boolean error = jObj.getBoolean("error");
                    String status = jObj.getString("StatusCode");
                    String msg = jObj.getString("StatusMessage");
                    System.out.println("REEE" + response);

                    if (status.equals("200")) {

//                        SharedPreferences sp = getSharedPreferences("MotorService", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor ed = sp.edit();
//                        ed.putString("UserId", email);
//                        //ed.putString("Mobile",password);
//                        ed.commit();
//
//                        Intent jorori_intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(jorori_intent);
//                        finish();


                    } else {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                        builder.setMessage("আপনার UserId অথবা Password ভুল হয়েছে। আবার চেষ্টা করুন।")
//                                .setCancelable(false)
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                    }
//                                });
//                        AlertDialog alert = builder.create();
//                        alert.show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("UserId", userId);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
