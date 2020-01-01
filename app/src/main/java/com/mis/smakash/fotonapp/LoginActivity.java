package com.mis.smakash.fotonapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mis.smakash.utils.SessionManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    public static String URL_LOGIN = "http://116.68.205.71:4003/fotonservice/api/v0/login/";
    private EditText txtUsername, txtPassword;
    private Button btnSignin;
    private ProgressDialog pDialog;
    private SessionManager session;
    //private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference mGetReference = mDatabase.getReference("url");
//    public static String URL_LOGIN = "";
    public Boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignin = (Button) findViewById(R.id.btnSignin);

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            //User is already logged in. Take him to main activity

            String UserId = session.getUserId();

            Bundle bundle = new Bundle();
            bundle.putString("UserId",UserId);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
            Log.d("session","loggedin");
        }



        btnSignin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String email = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (email.equalsIgnoreCase("") && password.equalsIgnoreCase("")) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("আপনার UserId এবং Password সঠিক ভাবে দিন।")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                } else {
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(cm.getActiveNetworkInfo() == null){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("আপানর ইন্টারনেট সংযোগ On করুন।")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }else{
                        checkLogin(email, password);
                    }

                }
            }
        });
    }


    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_LOGIN, new Response.Listener<String>() {

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

                        SharedPreferences sp = getSharedPreferences("MotorService", Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();
                        ed.putString("UserId", email);
                        //ed.putString("Mobile",password);
                        ed.commit();

                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",email);

                        session.setUserId(email);
                        session.setLogin(true);

                        Intent jorori_intent = new Intent(LoginActivity.this, MainActivity.class);
                        jorori_intent.putExtras(bundle);
                        startActivity(jorori_intent);
                        //finish();


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("আপনার UserId অথবা Password ভুল হয়েছে। আবার চেষ্টা করুন।")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
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
                        "দুঃখিত", Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", email);
                params.put("Password", password);

                return params;
            }

        };

        // Adding request to request queue
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

    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 1 * 1000);
        }
    }

}
