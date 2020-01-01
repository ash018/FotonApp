package com.mis.smakash.fotonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ServiceRatio extends AppCompatActivity {
    private WebView wvContent1;
    private String getcode1;
    Utility utility;
    LinearLayout noIntLl;
    Button retryButton;
    private static Button btnprevious, btnnext;
    private static ImageView mainmenuid;

    public String userId;

    public Boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ratio);

        final Bundle bundle = getIntent().getExtras();
        userId = bundle.getString("UserId");

        utility = new Utility();
        Intent ii = getIntent();
        ///getcode1 = ii.getStringExtra("SERVICERATIO");

        wvContent1 = findViewById(R.id.wvserviceratio);
        retryButton = findViewById(R.id.retryButton1);
        noIntLl = findViewById(R.id.noIntLl);
        if (utility.isConnected(this)) {
            noIntLl.setVisibility(View.GONE);
            loadData();
        } else {
            noIntLl.setVisibility(View.VISIBLE);
        }
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (utility.isConnected(ServiceRatio.this)) {
                    loadData();
                } else {
                    Toast.makeText(ServiceRatio.this, "No internet", Toast.LENGTH_SHORT).show();
                    noIntLl.setVisibility(View.VISIBLE);
                }
            }
        });

        mainmenuid = (ImageView) findViewById(R.id.srmainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ServiceRatio.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });
    }

    private void loadData() {
        noIntLl.setVisibility(View.GONE);
        wvContent1.getSettings().setJavaScriptEnabled(true);
        wvContent1.loadUrl("http://dashboard.acigroup.info/motorservices_mobile_api/service_ratio.php");
        wvContent1.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                wvContent1.loadUrl("javascript:myTerritoryCode('" + userId + "')"); //if passing in an object. Mapping may need to take place
            }
        });
    }


    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Home Icon বাটনটি ব্যবহার করুন",
                    Toast.LENGTH_SHORT).show();

        }
    }
}
