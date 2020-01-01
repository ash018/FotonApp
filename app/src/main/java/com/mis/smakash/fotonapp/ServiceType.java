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

public class ServiceType extends AppCompatActivity {
    private static ImageView mainmenuid;
    private static RadioButton rdoinstallation, rdoperiodical, rdowarranty, rdopaid, rdopostwcvisit;
    private static RadioGroup radiobtngroup;
    private static Button btnprevious, btnnext;
    private int rdBtnVal = 0;
    private String serviceProduct = "0", serviceCall = "0";

    EditServiceRow row = null;
    private String isEdit = "";

    public Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);
        rdBtnVal = 0;

        Intent srvTypeIntent = getIntent();
        serviceProduct = srvTypeIntent.getStringExtra("ServiceProduct");
        serviceCall = srvTypeIntent.getStringExtra("ServiceCallType");
        isEdit = srvTypeIntent.getStringExtra("Edit");

        final Bundle bundle = getIntent().getExtras();
        final String userId = bundle.getString("UserId");

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


        radiobtngroup = (RadioGroup) findViewById(R.id.radiobtngroup);

        rdoinstallation = (RadioButton) findViewById(R.id.rdoinstallation);
        rdoperiodical = (RadioButton) findViewById(R.id.rdoperiodical);
        rdowarranty = (RadioButton) findViewById(R.id.rdowarranty);
        rdopaid = (RadioButton) findViewById(R.id.rdopaid);
        rdopostwcvisit = (RadioButton) findViewById(R.id.rdopostwcvisit);

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ServiceType.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        if(rdBtnVal == 1){
            rdoinstallation.setChecked(true);
        }
        if(rdBtnVal >= 2 && rdBtnVal <= 7){
            rdoperiodical.setChecked(true);
        }
        if(rdBtnVal == 8){
            rdowarranty.setChecked(true);
        }
        if(rdBtnVal == 9){
            rdopaid.setChecked(true);
        }
        if(rdBtnVal == 10){
            rdopostwcvisit.setChecked(true);
        }

        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnnext = (Button) findViewById(R.id.btnnext);

        radiobtngroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case(R.id.rdoinstallation):
                        rdBtnVal = 1;
                        break;
                    case(R.id.rdoperiodical):
                        rdBtnVal = 2;
                        break;
                    case(R.id.rdowarranty):
                        rdBtnVal = 8;
                        break;
                    case(R.id.rdopaid):
                        rdBtnVal = 9;
                        break;
                    case(R.id.rdopostwcvisit):
                        rdBtnVal = 10;
                        break;
                    case(R.id.goodwill):
                        rdBtnVal = 11;
                        break;
                }
            }
        });
        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit.equalsIgnoreCase("1")){
                    Intent nextActivity = new Intent(ServiceType.this, ServiceCall.class);
                    nextActivity.putExtra("RowData", row);
                    nextActivity.putExtra("Edit", "1");
                    nextActivity.putExtra("IsPrevious", "1");
                    nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                    nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                    nextActivity.putExtra("UserId",userId);
                    startActivity(nextActivity);
                    finish();
                }
                if(isEdit.equalsIgnoreCase("0")){
                    Intent previousActivity = new Intent(ServiceType.this, ServiceCall.class);
                    previousActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                    previousActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ServiceType.this);
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
                        if(rdBtnVal == 1){
                            Intent nextActivity = new Intent(ServiceType.this, InstallationEntry.class);
                            nextActivity.putExtra("RowData", row);
                            nextActivity.putExtra("ServiceType",String.valueOf(rdBtnVal) );
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "1");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }
                        if(rdBtnVal >= 2 && rdBtnVal <= 7){

                            Intent nextActivity = new Intent(ServiceType.this, PeriodicalEntryType.class);
                            nextActivity.putExtra("RowData", row);
                            nextActivity.putExtra("ServiceType",String.valueOf(rdBtnVal) );
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "1");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 8){
                            Intent nextActivity = new Intent(ServiceType.this, WarrentyEntry.class);
                            nextActivity.putExtra("RowData", row);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "1");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 9){
                            Intent nextActivity = new Intent(ServiceType.this, PaidEntry.class);
                            nextActivity.putExtra("RowData", row);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "1");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 10){
                            Intent nextActivity = new Intent(ServiceType.this, PostwarrentyEntry.class);
                            nextActivity.putExtra("RowData", row);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "1");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 11){
                            Intent nextActivity = new Intent(ServiceType.this, GoodWillEntry.class);
                            nextActivity.putExtra("RowData", row);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "1");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                    }
                    if(isEdit.equalsIgnoreCase("0")){
                        if(rdBtnVal == 1){
                            Intent nextActivity = new Intent(ServiceType.this, InstallationEntry.class);
                            nextActivity.putExtra("ServiceType",String.valueOf(rdBtnVal) );
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "0");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }
                        if(rdBtnVal >= 2 && rdBtnVal <= 7){
                            Log.d("ServiceType", String.valueOf(rdBtnVal));
                            Intent nextActivity = new Intent(ServiceType.this, PeriodicalEntryType.class);
                            nextActivity.putExtra("ServiceType",String.valueOf(rdBtnVal) );
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "0");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 8){
                            Intent nextActivity = new Intent(ServiceType.this, WarrentyEntry.class);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "0");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 9){
                            Intent nextActivity = new Intent(ServiceType.this, PaidEntry.class);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "0");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 10){
                            Intent nextActivity = new Intent(ServiceType.this, PostwarrentyEntry.class);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "0");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }

                        if(rdBtnVal == 11){
                            Intent nextActivity = new Intent(ServiceType.this, GoodWillEntry.class);
                            nextActivity.putExtra("ServiceType", String.valueOf(rdBtnVal));
                            nextActivity.putExtra("ServiceCallType", String.valueOf(serviceCall));
                            nextActivity.putExtra("ServiceProduct", String.valueOf(serviceProduct));
                            nextActivity.putExtra("Edit", "0");
                            Bundle bundle = new Bundle();
                            bundle.putString("UserId",userId);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);
                        }
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
