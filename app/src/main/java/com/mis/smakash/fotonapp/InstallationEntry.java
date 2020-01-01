package com.mis.smakash.fotonapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mis.smakash.utils.EditServiceRow;
import com.mis.smakash.utils.Misc;

import org.json.JSONException;
import org.json.JSONObject;

import com.mis.smakash.utils.EditServiceRow;
import com.mis.smakash.utils.Misc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InstallationEntry extends AppCompatActivity {
    private static ImageView mainmenuid;
    private String serviceProduct = "0", serviceCall = "0", serviceType = "0";
    private EditText chassis, instcustomername, instmobilenumber, instdateofbuy, insthoureofbuy, instdateofinstallation;
    private Button btnChassis, btnprevious, btnnext;
    DatePickerDialog datePickerDialog;
    private DatabaseHelper db;
    private int mYear;
    private int mMonth;
    private int mDay;
    private ProgressDialog pDialog;
    private int mHour;
    private int mMinute;
    private String date_time = "";

    EditServiceRow row = null;
    private String isEdit = "";
    public Boolean exit = false;
    private AlertDialog.Builder builder1;

    private Misc misc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation_entry);
        db = new DatabaseHelper(getApplicationContext());
        db.getWritableDatabase();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Intent srvTypeIntent = getIntent();
        serviceProduct = srvTypeIntent.getStringExtra("ServiceProduct");
        serviceCall = srvTypeIntent.getStringExtra("ServiceCallType");
        serviceType = srvTypeIntent.getStringExtra("ServiceType");

        isEdit = srvTypeIntent.getStringExtra("Edit");

        chassis = (EditText) findViewById(R.id.chassisNo);
        instcustomername = (EditText) findViewById(R.id.instcustomername);
        instmobilenumber = (EditText) findViewById(R.id.instmobilenumber);
        instdateofbuy = (EditText) findViewById(R.id.instdateofbuy);
        insthoureofbuy = (EditText) findViewById(R.id.insthoureofbuy);
        instdateofinstallation = (EditText) findViewById(R.id.instdateofinstallation);

        final Bundle bundle = getIntent().getExtras();
        final String userId = bundle.getString("UserId");

        misc = new Misc();

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(InstallationEntry.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        if(isEdit.equalsIgnoreCase("1")){
            row = (EditServiceRow) srvTypeIntent.getSerializableExtra("RowData");
            chassis.setText(row.getKEY_CHASSIS());
            instcustomername.setText(row.getKEY_CUSTOMER_NAME());
            instmobilenumber.setText(row.getKEY_CUSTOMER_MOBILE());
            ///instmobilenumber = "+88" + instmobilenumber;

            instdateofbuy.setText(row.getKEY_BUYING_DATE());
            insthoureofbuy.setText(row.getKEY_RUNNING_HOUER());
            instdateofinstallation.setText(row.getKEY_INSTALLAION_DATE());
        }

        btnChassis = (Button) findViewById(R.id.btnChassis);
        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnnext = (Button) findViewById(R.id.btnnext);

        instdateofbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(instdateofbuy);
            }
        });

        instdateofinstallation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(instdateofinstallation);
            }
        });


        btnChassis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chassisNo = chassis.getText().toString();
                if(!chassisNo.equals("")){
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(cm.getActiveNetworkInfo() == null){
                        AlertDialog.Builder builder = new AlertDialog.Builder(InstallationEntry.this);
                        builder.setMessage("আপানর ইন্টারনেট সংযোগ On করুন।")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }else{
                        setChassisData(chassisNo);
                    }
                }
            }
        });

        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit.equalsIgnoreCase("1")){
                    Intent nextActivity = new Intent(InstallationEntry.this, ServiceType.class);
                    nextActivity.putExtra("RowData", row);
                    nextActivity.putExtra("Edit", "1");
                    nextActivity.putExtra("IsPrevious", "1");
                    nextActivity.putExtra("ServiceType", serviceType);
                    nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                    nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    Bundle bundle = new Bundle();
                    bundle.putString("UserId",userId);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                    finish();
                }
                if(isEdit.equalsIgnoreCase("0")){
                    Intent previousActivity = new Intent(InstallationEntry.this, ServiceType.class);
                    previousActivity.putExtra("ServiceType", serviceType);
                    previousActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                    previousActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    previousActivity.putExtra("Edit", "0");
                    Bundle bundle = new Bundle();
                    bundle.putString("UserId",userId);
                    previousActivity.putExtras(bundle);
                    startActivity(previousActivity);
                    finish();
                }
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = instcustomername.getText().toString();
                String mobile = instmobilenumber.getText().toString();
                String buyingDate = instdateofbuy.getText().toString();
                String hours = insthoureofbuy.getText().toString();
                String installationDate = instdateofinstallation.getText().toString();
                String chassisText = chassis.getText().toString();
                //System.out.println("customerName==="+customerName+"==mobile=="+mobile+"==buyingDate=="+buyingDate);
                if(customerName.equalsIgnoreCase("") ||
                        mobile.equalsIgnoreCase("") ||
                        buyingDate.equalsIgnoreCase("") ||
                        hours.equalsIgnoreCase("") ||
                        installationDate.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(InstallationEntry.this);
                    builder.setMessage("দয়া করে সবকটি Field পূরণ করুন ।")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                else{
                    if(isEdit.equalsIgnoreCase("1")){
                        db.updateInstallationService(row, serviceProduct, serviceCall,
                                serviceType, customerName, mobile, buyingDate, hours, installationDate
                        );
                        Intent nextActivity = new Intent(InstallationEntry.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",userId);
                        nextActivity.putExtras(bundle);
                        startActivity(nextActivity);
                        finish();
                    }
                    if(isEdit.equalsIgnoreCase("0")){
//                        db.addInstallationService(serviceProduct, serviceCall,
//                                serviceType, customerName, mobile, buyingDate, hours, installationDate, userId
//                        );
                        Intent nextActivity = new Intent(InstallationEntry.this, ServiceSatisfaction.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",userId);
                        bundle.putString("serviceProduct",serviceProduct);
                        bundle.putString("serviceCall",serviceCall);
                        bundle.putString("serviceType",serviceType);
                        bundle.putString("customerName", customerName);
                        bundle.putString("mobile", mobile);
                        bundle.putString("hours", hours);
                        bundle.putString("buyingDate",buyingDate);
                        bundle.putString("installationDate",installationDate);
                        bundle.putString("chassis", chassisText);
                        bundle.putString("entryType", "Installation");
                        nextActivity.putExtras(bundle);
                        startActivity(nextActivity);
                        finish();
                    }
                }
            }
        });
    }

    private void setChassisData(final String chassisNo) {
        String tag_string_req = "req_login";

        pDialog.setMessage("Fetching Data ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                misc.getGetCustomerDetailURL(), new Response.Listener<String>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
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

                        JSONObject msgObject = new JSONObject(msg);
                        String customerName =  msgObject.getString("customerName");
                        String customerMobile =  msgObject.getString("customerMobile");
                        String invoiceDate =  msgObject.getString("invoiceDate");

                        String[] inv = invoiceDate.split("T");
                        String[] dateInv = inv[0].split("-");
                        String finalInvDate = dateInv[2]+"-"+dateInv[1]+"-"+dateInv[0]+ " "+inv[1];


                        instcustomername.setText(customerName);
                        instmobilenumber.setText(customerMobile);
                        instdateofbuy.setText(finalInvDate);

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(InstallationEntry.this);
                        builder.setMessage("No Data Found!")
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
                params.put("ChassisNo", chassisNo);

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

    private void datePicker(final EditText setTimeView) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        builder1 = new AlertDialog.Builder(this);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        tiemPicker(setTimeView);
//                        if(misc.checkPrevDate(dayOfMonth,monthOfYear,year)){
//                            tiemPicker(setTimeView);
//                        }
//                        else{
//                            builder1.setMessage("You can not pick previous date");
//                            builder1.setCancelable(true);
//                            builder1.setPositiveButton(
//                                    "Dismiss",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.cancel();
//                                    }
//                                }
//                            );
//
//                            AlertDialog alert11 = builder1.create();
//                            alert11.show();
//                            Toast.makeText(getApplicationContext(),"You can not pick previous date for Service Assignment",Toast.LENGTH_LONG);
//                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void tiemPicker(final EditText seTimeView) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;

                        seTimeView.setText(date_time + " " + hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
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
