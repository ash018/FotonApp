package com.mis.smakash.fotonapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mis.smakash.utils.EditServiceRow;


public class ServiceCall extends AppCompatActivity {
    private static ImageView mainmenuid;
    private static RadioButton rdotractor, rdopowertiller;
    private static RadioGroup radiobtngroupservicecall;
    private static Button btnprevious, btnnext;

    private int rdBtnVal = 0;
    public String serviceProduct = "0";

    EditServiceRow row = null;
    private String isEdit = "";

    public Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_call);
        rdBtnVal = 0;
        Intent selProIntent = getIntent();
//        serviceProduct = selProIntent.getStringExtra("ServiceProduct");
        isEdit = selProIntent.getStringExtra("Edit");

        final Bundle bundle = getIntent().getExtras();
        final String userId = bundle.getString("UserId");

        if(isEdit.equalsIgnoreCase("1")){
            row = (EditServiceRow) selProIntent.getSerializableExtra("RowData");
            String isPrevious = selProIntent.getStringExtra("IsPrevious");
            try{
                if(isPrevious.equalsIgnoreCase("1")){
                    String preSelect  = selProIntent.getStringExtra("ServiceCallType");
                    rdBtnVal = Integer.valueOf(preSelect);
                }
                else{
                    rdBtnVal = Integer.parseInt(row.getKEY_CALL_TYPE());
                }
            }catch(NullPointerException np){
                rdBtnVal = Integer.parseInt(row.getKEY_CALL_TYPE());
                np.printStackTrace();
            }
        }
        else{
            try{
                String preSelect  = selProIntent.getStringExtra("ServiceCallType");
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

        //System.out.println("====ServiceProduct22==="+serviceProduct);
        radiobtngroupservicecall = (RadioGroup) findViewById(R.id.radiobtngroupservicecall);

        rdotractor = (RadioButton) findViewById(R.id.rdotractor);
        rdopowertiller = (RadioButton) findViewById(R.id.rdopowertiller);

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ServiceCall.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        if(rdBtnVal == 1){
            rdotractor.setChecked(true);
        }
        if(rdBtnVal == 2){
            rdopowertiller.setChecked(true);
        }

        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnnext = (Button) findViewById(R.id.btnnext);

        radiobtngroupservicecall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case(R.id.rdotractor):
                        rdBtnVal = 1;
                        break;
                    case(R.id.rdopowertiller):
                        rdBtnVal = 2;
                        break;
                }
            }
        });

        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit.equalsIgnoreCase("1")){
                    Intent nextActivity = new Intent(ServiceCall.this, MainActivity.class);
                    nextActivity.putExtra("RowData", row);
                    nextActivity.putExtra("Edit", "1");
                    nextActivity.putExtra("IsPrevious", "1");
                    nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    Bundle bundle = new Bundle();
                    bundle.putString("UserId",userId);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                    finish();
                }
                if(isEdit.equalsIgnoreCase("0")){
                    Intent previousActivity = new Intent(ServiceCall.this, MainActivity.class);
                    previousActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    previousActivity.putExtra("Edit", "0");
                    Bundle bundle = new Bundle();
                    bundle.putString("UserId",userId);
                    previousActivity.putExtras(bundle);
                    startActivity(previousActivity);
                    finish();
                }
                /*Intent previousActivity = new Intent(ServiceCall.this, SelectProduct.class);
                startActivity(previousActivity);*/
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdBtnVal == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ServiceCall.this);
                    builder.setMessage("দয়া করে একটি অপশন সিলেক্ট করন")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    if(isEdit.equalsIgnoreCase("1")){
                        Intent nextActivity = new Intent(ServiceCall.this, ServiceType.class);
                        nextActivity.putExtra("RowData", row);
                        nextActivity.putExtra("Edit", "1");
                        nextActivity.putExtra("ServiceCallType", String.valueOf(rdBtnVal));
                        nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",userId);
                        nextActivity.putExtras(bundle);
                        startActivity(nextActivity);
                    }
                    if(isEdit.equalsIgnoreCase("0")){
                        Intent nextActivity = new Intent(ServiceCall.this, ServiceType.class);
                        nextActivity.putExtra("ServiceCallType", String.valueOf(rdBtnVal));
                        nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                        nextActivity.putExtra("Edit", "0");
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",userId);
                        nextActivity.putExtras(bundle);
                        startActivity(nextActivity);
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
