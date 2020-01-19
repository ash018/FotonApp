package com.mis.smakash.fotonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ServiceSatisfaction extends AppCompatActivity {

    private static ImageView mainmenuid;
    private DatabaseHelper db;
    private ImageView rating100, rating50, rating25;
    private Button btnprevious, btnnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_satisfaction);

        db = new DatabaseHelper(getApplicationContext());
        db.getWritableDatabase();

        final Bundle bundle = getIntent().getExtras();
        final String UserId = bundle.getString("UserId");
        final String serviceProduct = bundle.getString("serviceProduct");
        final String serviceCall = bundle.getString("serviceCall");
        final String serviceType = bundle.getString("serviceType");
        final String customerName = bundle.getString("customerName");
        final String mobile = bundle.getString("mobile");
        final String hours = bundle.getString("hours");
        final String buyingDate = bundle.getString("buyingDate");
        final String installationDate = bundle.getString("installationDate");
        final String chassis = bundle.getString("chassis");
        final String entryType = bundle.getString("entryType");
        final String driverName = bundle.getString("driverName");
        final String driverNumber = bundle.getString("driverNumber");
        String insServiceEndDate="",inservice="";

        if(entryType.equals("GoodWill") || entryType.equals("Paid")) {
            insServiceEndDate = bundle.getString("insServiceEndDate");
            inservice = bundle.getString("inservice");
        }

        if(entryType.equals("Periodical")) {
            insServiceEndDate = bundle.getString("insServiceEndDate");
        }

        if(entryType.equals("Warrenty")) {
            insServiceEndDate = bundle.getString("insServiceEndDate");
        }

        rating100 = (ImageView) findViewById(R.id.rating100);
        rating50 = (ImageView) findViewById(R.id.rating50);
        rating25 = (ImageView) findViewById(R.id.rating25);

        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ServiceSatisfaction.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",UserId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        final String finalInsServiceEndDate = insServiceEndDate;
        final String finalInservice = inservice;
        rating100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entryType.equals("GoodWill")) {
                    db.addGoodWillEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile, hours, buyingDate,
                            installationDate, finalInsServiceEndDate, finalInservice, UserId, "100", chassis,
                            driverName, driverNumber
                    );
                }

                if(entryType.equals("Installation")) {
                    db.addInstallationService(serviceProduct, serviceCall,
                                serviceType, customerName, mobile, buyingDate, hours, installationDate,
                                UserId, "100",chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Periodical")) {
                    db.addPerodicEntryService(serviceProduct, serviceCall,
                                serviceType, customerName, mobile,  hours, buyingDate,
                            installationDate, finalInsServiceEndDate, UserId, "100", chassis,
                            driverName, driverNumber
                    );
                }

                if(entryType.equals("Warrenty")) {
                    db.addWarrentyEntry(serviceProduct, serviceCall,
                                serviceType, customerName, mobile,  hours, buyingDate,
                                installationDate, finalInsServiceEndDate, UserId, "100", chassis,
                                driverName, driverNumber
                        );
                }

                if(entryType.equals("Paid")) {
                    db.addPaidEntry(serviceProduct, serviceCall,
                                serviceType, customerName, mobile,  hours, buyingDate,
                                installationDate, finalInsServiceEndDate, finalInservice, UserId,
                            "100", chassis, driverName, driverNumber
                        );
                }

                if(entryType.equals("PostWarranty")) {
                    db.addPostWarrentyEntry(serviceProduct, serviceCall,
                                serviceType, customerName, mobile,  hours, buyingDate, UserId, "100",
                            chassis, driverName, driverNumber
                        );
                }

                Intent nextActivity = new Intent(ServiceSatisfaction.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",UserId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        rating50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entryType.equals("GoodWill")) {
                    db.addGoodWillEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile, hours, buyingDate,
                            installationDate, finalInsServiceEndDate, finalInservice, UserId, "50", chassis,
                            driverName, driverNumber
                    );
                }

                if(entryType.equals("Installation")) {
                    db.addInstallationService(serviceProduct, serviceCall,
                            serviceType, customerName, mobile, buyingDate, hours, installationDate,
                            UserId, "50", chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Periodical")) {
                    db.addPerodicEntryService(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate, installationDate,
                            finalInsServiceEndDate, UserId, "50", chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Warrenty")) {
                    db.addWarrentyEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate,
                            installationDate, finalInsServiceEndDate, UserId, "50", chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Paid")) {
                    db.addPaidEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate,
                            installationDate, finalInsServiceEndDate, finalInservice, UserId, "50",
                            chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("PostWarranty")) {
                    db.addPostWarrentyEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate, UserId, "50", chassis,
                            driverName, driverNumber
                    );
                }

                Intent nextActivity = new Intent(ServiceSatisfaction.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",UserId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        rating25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(entryType.equals("GoodWill")) {
                    db.addGoodWillEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile, hours, buyingDate,
                            installationDate, finalInsServiceEndDate, finalInservice, UserId, "25", chassis,
                            driverName, driverNumber
                    );
                }

                if(entryType.equals("Installation")) {
                    db.addInstallationService(serviceProduct, serviceCall,
                            serviceType, customerName, mobile, buyingDate, hours, installationDate,
                            UserId, "25", chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Periodical")) {
                    db.addPerodicEntryService(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate, installationDate,
                            finalInsServiceEndDate, UserId, "25", chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Warrenty")) {
                    db.addWarrentyEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate,
                            installationDate, finalInsServiceEndDate, UserId, "25", chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("Paid")) {
                    db.addPaidEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate,
                            installationDate, finalInsServiceEndDate, finalInservice, UserId, "25",
                            chassis, driverName, driverNumber
                    );
                }

                if(entryType.equals("PostWarranty")) {
                    db.addPostWarrentyEntry(serviceProduct, serviceCall,
                            serviceType, customerName, mobile,  hours, buyingDate, UserId, "25", chassis,
                            driverName, driverNumber
                    );
                }

                Intent nextActivity = new Intent(ServiceSatisfaction.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",UserId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

    }
}
