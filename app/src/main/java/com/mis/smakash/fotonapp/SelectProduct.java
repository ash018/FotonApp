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

public class SelectProduct extends AppCompatActivity {
    private static ImageView mainmenuid;
    private static RadioButton rdotractor, rdopowertiller, rdortransplanter, rdoharvester,rdoriper, rdodiselengine, rdoothers;
    private static RadioGroup radiobtngroupselectproduct;

    private static Button btnprevious, btnnext;


    private int rdBtnVal = 0;
    EditServiceRow row = null;
    private String isEdit = "";
    public Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);
        rdBtnVal = 0;



        Intent selProIntent = getIntent();
        isEdit = selProIntent.getStringExtra("Edit");

        final Bundle bundle = getIntent().getExtras();
        final String userId = bundle.getString("UserId");

        if(isEdit.equalsIgnoreCase("1")){
            row = (EditServiceRow) selProIntent.getSerializableExtra("RowData");
            String isPrevious = selProIntent.getStringExtra("IsPrevious");
            try{
                if(isPrevious.equalsIgnoreCase("1")){
                    String preSelect  = selProIntent.getStringExtra("ServiceProduct");
                    rdBtnVal = Integer.valueOf(preSelect);
                }
                else{
                    rdBtnVal = Integer.parseInt(row.getKEY_PRODUCT());
                }

            }catch(NullPointerException np){
                rdBtnVal = Integer.parseInt(row.getKEY_PRODUCT());
                np.printStackTrace();
            }

        }
        else{
            String preSelect = selProIntent.getStringExtra("ServiceProduct");
            try{
                if(preSelect.equalsIgnoreCase("")){
                    rdBtnVal = 0;
                }
                else{
                    rdBtnVal = Integer.valueOf(preSelect);
                }
            }
            catch(NullPointerException np){
                rdBtnVal = 0;
                np.printStackTrace();
            }

        }
        radiobtngroupselectproduct = (RadioGroup) findViewById(R.id.radiobtngroupselectproduct);

        rdotractor = (RadioButton) findViewById(R.id.rdotractor);
        rdopowertiller = (RadioButton) findViewById(R.id.rdopowertiller);
        rdortransplanter = (RadioButton) findViewById(R.id.rdortransplanter);
        rdoharvester = (RadioButton) findViewById(R.id.rdoharvester);
        rdoriper = (RadioButton) findViewById(R.id.rdoriper);
        rdodiselengine = (RadioButton) findViewById(R.id.rdodiselengine);
        rdoothers = (RadioButton) findViewById(R.id.rdoothers);

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(SelectProduct.this, MainActivity.class);
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
        if(rdBtnVal == 3){
            rdortransplanter.setChecked(true);
        }
        if(rdBtnVal == 4){
            rdoharvester.setChecked(true);
        }
        if(rdBtnVal == 5){
            rdoriper.setChecked(true);
        }
        if(rdBtnVal == 6){
            rdodiselengine.setChecked(true);
        }
        if(rdBtnVal == 7){
            rdoothers.setChecked(true);
        }

        btnprevious = (Button) findViewById(R.id.btnprevious);
        btnnext = (Button) findViewById(R.id.btnnext);

        radiobtngroupselectproduct.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case(R.id.rdotractor):
                        rdBtnVal = 1;
                        break;
                    case(R.id.rdopowertiller):
                        rdBtnVal = 2;
                        break;
                    case(R.id.rdortransplanter):
                        rdBtnVal = 3;
                        break;
                    case(R.id.rdoharvester):
                        rdBtnVal = 4;
                        break;
                    case(R.id.rdoriper):
                        rdBtnVal = 5;
                        break;
                    case(R.id.rdodiselengine):
                        rdBtnVal = 6;
                        break;
                    case(R.id.rdoothers):
                        rdBtnVal = 7;
                        break;
                }
            }
        });


        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previousActivity = new Intent(SelectProduct.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                previousActivity.putExtras(bundle);
                startActivity(previousActivity);
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdBtnVal == 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(SelectProduct.this);
                    builder.setMessage("দয়া করে একটি অপশন সিলেক্ট করন")
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
                        Intent nextActivity = new Intent(SelectProduct.this, ServiceCall.class);
                        nextActivity.putExtra("RowData", row);
                        nextActivity.putExtra("Edit", "1");
                        nextActivity.putExtra("ServiceProduct", String.valueOf(rdBtnVal));
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",userId);
                        nextActivity.putExtras(bundle);
                        startActivity(nextActivity);
                    }
                    if(isEdit.equalsIgnoreCase("0")){
                        Intent nextActivity = new Intent(SelectProduct.this, ServiceCall.class);
                        nextActivity.putExtra("ServiceProduct", String.valueOf(rdBtnVal));
                        nextActivity.putExtra("Edit", "0");
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId",userId);
                        nextActivity.putExtras(bundle);
                        startActivity(nextActivity);
                    }

                    //finish();
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
