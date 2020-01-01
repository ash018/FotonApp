package com.mis.smakash.fotonapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


public class ServicePerformance extends AppCompatActivity {

    private static RadioGroup radiobtngroupservicecall;
    private static Button btnprevious, btnnext;
    private static ImageView mainmenuid;

    private int rdBtnVal = 0;
    private String userId;

    public Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_performance);


        final Bundle bundle = getIntent().getExtras();
        final String userId = bundle.getString("UserId");
        //Log.d("userid",userId);
        radiobtngroupservicecall = (RadioGroup) findViewById(R.id.radiobtngroupservicecall);

        radiobtngroupservicecall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case(R.id.serviceRatio):
                        rdBtnVal = 1;
                        break;
                    case(R.id.customerSatisfaction):
                        rdBtnVal = 2;
                        break;

                    case(R.id.sixHourService):
                        rdBtnVal = 3;
                        break;
                }
            }
        });

        btnnext = (Button) findViewById(R.id.btnnext);

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ServicePerformance.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ServicePerformance.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdBtnVal == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ServicePerformance.this);
                    builder.setMessage("দয়া করে একটি অপশন সিলেক্ট করন")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                if(rdBtnVal == 1) {
                    Intent nextActivity = new Intent(ServicePerformance.this, ServiceRatio.class);

//                    nextActivity.putExtra("UserId", userId);
                    Bundle bundle = new Bundle();
                    bundle.putString("UserId",userId);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }

                if(rdBtnVal == 2){
                    Intent nextActivity = new Intent(ServicePerformance.this, CustomerSatisfaction.class);
                    nextActivity.putExtra("UserId", userId);
                    bundle.putString("UserId",userId);
                    startActivity(nextActivity);
                }

                if(rdBtnVal == 3){
                    Intent nextActivity = new Intent(ServicePerformance.this, SixHourActivity.class);
                    nextActivity.putExtra("UserId", userId);
                    startActivity(nextActivity);
                }
            }
        });
    }

    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "ফিরে যান বাটনটি ব্যবহার করুন",
                    Toast.LENGTH_SHORT).show();

        }
    }
}
