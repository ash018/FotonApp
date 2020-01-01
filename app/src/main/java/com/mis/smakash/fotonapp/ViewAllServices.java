package com.mis.smakash.fotonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.mis.smakash.utils.EditServiceRow;
import com.mis.smakash.utils.ServiceRow;


import java.util.ArrayList;

public class ViewAllServices extends AppCompatActivity {
    private ListView mRecyclerView;
    private static ImageView mainmenuid;
    //private RecyclerView.Adapter mAdapter;
    private ArrayAdapter<ServiceRow> mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper db;
    ArrayList<ServiceRow> myDataset;

    public String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_services);
        mRecyclerView = (ListView) findViewById(R.id.main_list);
        myDataset = new ArrayList<ServiceRow>();

        final Bundle bundle = getIntent().getExtras();
        userId = bundle.getString("UserId");

        db = new DatabaseHelper(getApplicationContext());
        db.getWritableDatabase();
        myDataset = db.getDataForAdaptor(userId);



        mainmenuid = (ImageView) findViewById(R.id.mainmenuid);
        mainmenuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity = new Intent(ViewAllServices.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId",userId);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
                finish();
            }
        });

        ServiceBaseAdapter sbAdapter = new ServiceBaseAdapter(this, R.layout.view_services_row, myDataset);


        //mAdapter = new ServiceAdaptor(this, myDataset);
        mRecyclerView.setAdapter(sbAdapter);
        mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3){
                ServiceRow value = (ServiceRow) adapter.getItemAtPosition(position);
                //System.out.println("======" + value.getId());

                Intent nextActivity = new Intent(ViewAllServices.this, ServiceCall.class);
                EditServiceRow row = db.getRowById(value.getId());
                nextActivity.putExtra("RowData", row);
                nextActivity.putExtra("Edit", "1");
                nextActivity.putExtra("UserId",userId);
                startActivity(nextActivity);

            }
        });


    }
}
