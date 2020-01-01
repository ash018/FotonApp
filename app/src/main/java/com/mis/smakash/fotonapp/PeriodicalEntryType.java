package com.mis.smakash.fotonapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mis.smakash.utils.EditServiceRow;


public class PeriodicalEntryType extends AppCompatActivity {
    private static ImageView mainmenuid;
    private static RadioButton rdo1, rdo2, rdo3, rdo4, rdo5, rdo6;
    private static RadioGroup radiobtngroup;
    private String serviceProduct = "0", serviceCall = "0", serviceType = "0";
    EditServiceRow row = null;
    private String isEdit = "";
    private int rdBtnVal = 0;
    private static Button btnprevious, btnnext;
    public Boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_entry_type);
        rdBtnVal = 0;
        Intent srvTypeIntent = getIntent();
        serviceProduct = srvTypeIntent.getStringExtra("ServiceProduct");
        serviceCall = srvTypeIntent.getStringExtra("ServiceCallType");
        serviceType = srvTypeIntent.getStringExtra("ServiceType");
        isEdit = srvTypeIntent.getStringExtra("Edit");

        final Bundle bundle = getIntent().getExtras();
        final String userId = bundle.getString("UserId");

        radiobtngroup = (RadioGroup) findViewById(R.id.radiobtngroup);
        rdo1 = (RadioButton) findViewById(R.id.rdo1);
        rdo2 = (RadioButton) findViewById(R.id.rdo2);
        rdo3 = (RadioButton) findViewById(R.id.rdo3);
        rdo4 = (RadioButton) findViewById(R.id.rdo4);
        rdo5 = (RadioButton) findViewById(R.id.rdo5);
        rdo6 = (RadioButton) findViewById(R.id.rdo6);

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(PeriodicalEntryType.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });
        Log.d("ServiceType", serviceType);
        if(isEdit.equalsIgnoreCase("1")){
            row = (EditServiceRow) srvTypeIntent.getSerializableExtra("RowData");
            //rdBtnVal = Integer.parseInt(row.getKEY_SERVICE_TYPE());

            String isPrevious = srvTypeIntent.getStringExtra("IsPrevious");
            try{
                if(isPrevious.equalsIgnoreCase("1")){
                    String preSelect  = srvTypeIntent.getStringExtra("ServiceType");
                    rdBtnVal = Integer.valueOf(preSelect);
                }
                else{
                    rdBtnVal = Integer.parseInt(row.getKEY_SERVICE_TYPE());
                }
            }catch(NullPointerException np){
                rdBtnVal = Integer.parseInt(row.getKEY_SERVICE_TYPE());
                np.printStackTrace();
            }
        }
        else{
            try{
                String preSelect  = srvTypeIntent.getStringExtra("ServiceType");
                if(preSelect.equalsIgnoreCase("")){
                    rdBtnVal = 0;
                }
                else{
                    rdBtnVal = Integer.valueOf(preSelect);
                }
            }catch(NullPointerException np){
                rdBtnVal = 0;
                np.printStackTrace();
            }
        }

        if(rdBtnVal == 2){
            rdo1.setChecked(true);
        }
        if(rdBtnVal == 3){
            rdo2.setChecked(true);
        }
        if(rdBtnVal == 4){
            rdo3.setChecked(true);
        }
        if(rdBtnVal == 5){
            rdo4.setChecked(true);
        }
        if(rdBtnVal == 6){
            rdo5.setChecked(true);
        }
        if(rdBtnVal == 7){
            rdo6.setChecked(true);
        }

            radiobtngroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case(R.id.rdo1):
                        rdBtnVal = 2;
                        break;
                    case(R.id.rdo2):
                        rdBtnVal = 3;
                        break;
                    case(R.id.rdo3):
                        rdBtnVal = 4;
                        break;
                    case(R.id.rdo4):
                        rdBtnVal = 5;
                        break;
                    case(R.id.rdo5):
                        rdBtnVal = 6;
                        break;
                    case(R.id.rdo6):
                        rdBtnVal = 7;
                        break;
                }
            }
        });

        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnnext = (Button) findViewById(R.id.btnnext);

        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit.equalsIgnoreCase("1")){
                    Intent nextActivity = new Intent(PeriodicalEntryType.this, ServiceType.class);
                    nextActivity.putExtra("RowData", row);
                    nextActivity.putExtra("Edit", "1");
                    nextActivity.putExtra("IsPrevious", "1");
                    nextActivity.putExtra("ServiceType", serviceType);
                    nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                    nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    nextActivity.putExtra("UserId",userId);
                    startActivity(nextActivity);
                    finish();
                }
                if(isEdit.equalsIgnoreCase("0")){
                    Intent previousActivity = new Intent(PeriodicalEntryType.this, ServiceType.class);
                    previousActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                    previousActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    previousActivity.putExtra("ServiceType", serviceType);
                    previousActivity.putExtra("Edit", "0");
                    previousActivity.putExtra("UserId",userId);
                    startActivity(previousActivity);
                    finish();
                }
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdBtnVal == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PeriodicalEntryType.this);
                    builder.setMessage("দয়া করে একটি অপশন সিলেক্ট করন")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    if(isEdit.equalsIgnoreCase("1")){
                        Intent nextActivity = new Intent(PeriodicalEntryType.this, PeriodicalEntry.class);
                        nextActivity.putExtra("RowData", row);
                        nextActivity.putExtra("Edit", "1");
                        nextActivity.putExtra("IsPrevious", "1");
                        nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                        nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                        nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                        nextActivity.putExtra("UserId",userId);
                        startActivity(nextActivity);
                        finish();
                    }
                    else{
                        Intent nextActivity = new Intent(PeriodicalEntryType.this, PeriodicalEntry.class);
                        nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                        nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                        nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                        nextActivity.putExtra("Edit", "0");
                        nextActivity.putExtra("UserId",userId);
                        startActivity(nextActivity);
                        finish();
                    }
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
