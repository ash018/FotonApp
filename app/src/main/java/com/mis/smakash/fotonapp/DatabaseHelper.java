package com.mis.smakash.fotonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mis.smakash.utils.EditServiceRow;

import com.mis.smakash.utils.EditServiceRow;
import com.mis.smakash.utils.ServiceRow;
import com.mis.smakash.utils.SynchDataRow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DATABASEHELPER";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MotorService.db";

    private static final String TABLE_SERVICE_MANAGER = "servicemanager";


    private static final String KEY_ID = "id";
    private static final String KEY_SERVER_MASTER_ID = "server_id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_EDITED_AT = "edited_at";
    private static final String KEY_PRODUCT = "product_type";
    private static final String KEY_CALL_TYPE = "call_type";
    private static final String KEY_SERVICE_TYPE = "service_type";
    private static final String KEY_IS_SYNCH = "is_synch";
    private static final String KEY_EDIT_LOG_COUNT = "log_count";
    private static final String KEY_IS_EDIT = "is_edited";

    private static final String KEY_CUSTOMER_NAME = "customer_name";
    private static final String KEY_CUSTOMER_MOBILE = "customer_mobile";
    private static final String KEY_BUYING_DATE = "buy_date";
    private static final String KEY_RUNNING_HOUER = "runnign_houer";
    private static final String KEY_INSTALLAION_DATE = "installation_date";

    private static final String KEY_CALL_SERVICE_DATE = "service_call_date";
    private static final String KEY_SERVICE_START_DATE = "service_start_date";
    private static final String KEY_SERVICE_END_DATE = "service_end_date";

    private static final String KEY_SERVICE_INCOME = "service_income";
    private static final String KEY_VISITED_DATE = "visited_date";

    private static final String KEY_USER = "user_id";

    private static final String KEY_RATING = "service_rating";

    private static final String KEY_CHASSIS = "service_chassis";

    private static final String KEY_DRIVER_NAME = "driver_name";

    private static final String KEY_DRIVER_NUMBER = "driver_number";

    private static final String CREATE_TABLE_SERVICE_MANAGER = "CREATE TABLE " + TABLE_SERVICE_MANAGER
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SERVER_MASTER_ID + " INTEGER DEFAULT 0, "
            + KEY_PRODUCT + " TEXT DEFAULT '0', " + KEY_CALL_TYPE + " TEXT DEFAULT '0', " + KEY_SERVICE_TYPE + " TEXT DEFAULT '0', "
            + KEY_IS_SYNCH + " TEXT DEFAULT '0', " + KEY_EDIT_LOG_COUNT + " TEXT DEFAULT '0', " + KEY_IS_EDIT + " TEXT DEFAULT '0', "
            + KEY_CUSTOMER_NAME + " TEXT, " + KEY_CUSTOMER_MOBILE + " TEXT, " + KEY_BUYING_DATE + " DATETIME DEFAULT (datetime('now','localtime')), "
            + KEY_RUNNING_HOUER + " TEXT, " + KEY_INSTALLAION_DATE + " DATETIME DEFAULT (datetime('now','localtime')), "
            + KEY_CALL_SERVICE_DATE + " DATETIME DEFAULT (datetime('now','localtime')), " + KEY_SERVICE_START_DATE + " DATETIME DEFAULT (datetime('now','localtime')),  "
            + KEY_SERVICE_END_DATE + " DATETIME DEFAULT (datetime('now','localtime')), " + KEY_SERVICE_INCOME + " TEXT DEFAULT '0', "
            + KEY_VISITED_DATE + " DATETIME DEFAULT (datetime('now','localtime')), " + KEY_CREATED_AT + " DATETIME DEFAULT (datetime('now','localtime')),"
            + KEY_USER + " TEXT ,"
            + KEY_RATING + " TEXT ,"
            + KEY_CHASSIS + " TEXT ,"
            + KEY_DRIVER_NAME + " TEXT ,"
            + KEY_DRIVER_NUMBER + " TEXT ,"
            + KEY_EDITED_AT + " DATETIME DEFAULT (datetime('now','localtime')));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE", this.CREATE_TABLE_SERVICE_MANAGER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_SERVICE_MANAGER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_MANAGER);
        onCreate(db);
    }

    public boolean addInstallationService(String productType, String callType, String serviceType,
                                          String customerName, String mobile, String buyingDate,
                                          String houers, String installationDate, String userId,
                                          String rating, String chassis, String driverName, String driverNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_BUYING_DATE, formatDate(buyingDate));
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_INSTALLAION_DATE, formatDate(installationDate));
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, "1");
        contentValues.put(KEY_IS_EDIT, "N");
        contentValues.put(KEY_USER, userId);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_CHASSIS, chassis);
        contentValues.put(KEY_DRIVER_NAME, driverName);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);

        Log.d("userId",userId);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateInstallationService(EditServiceRow row, String productType, String callType,
                                             String serviceType, String customerName, String mobile,
                                             String buyingDate, String houers, String installationDate,
                                             String chassisText, String drivername,
                                             String driverNumber) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_BUYING_DATE, formatDate(buyingDate));
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_INSTALLAION_DATE, formatDate(installationDate));
        contentValues.put(KEY_CHASSIS, chassisText);
        contentValues.put(KEY_DRIVER_NAME, drivername);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, String.valueOf(Integer.parseInt(row.getKEY_EDIT_LOG_COUNT()) + 1));
        contentValues.put(KEY_IS_EDIT, "Y");
        contentValues.put(KEY_EDITED_AT, currentTime);

        //long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        long result = db.update(TABLE_SERVICE_MANAGER, contentValues, KEY_ID + " = " + row.getKEY_ID(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean addPerodicEntryService(String productType, String callType, String serviceType,
                                          String customerName, String mobile, String houers,
                                          String buyingDate, String installationDate, String insserviceenddate,
                                          String userId, String rating, String chassis, String driverName,
                                          String driverNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, "1");
        contentValues.put(KEY_IS_EDIT, "N");
        contentValues.put(KEY_USER, userId);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_CHASSIS, chassis);
        contentValues.put(KEY_DRIVER_NAME, driverName);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatePerodicEntryService(EditServiceRow row, String productType, String callType, String serviceType, String customerName, String mobile, String houers, String buyingDate, String installationDate, String insserviceenddate) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, String.valueOf(Integer.parseInt(row.getKEY_EDIT_LOG_COUNT()) + 1));
        contentValues.put(KEY_IS_EDIT, "Y");
        contentValues.put(KEY_EDITED_AT, currentTime);

        //long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        long result = db.update(TABLE_SERVICE_MANAGER, contentValues, KEY_ID + " = " + row.getKEY_ID(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addWarrentyEntry(String productType, String callType, String serviceType,
                                    String customerName, String mobile, String houers,
                                    String buyingDate, String installationDate, String insserviceenddate,
                                    String userId, String rating, String chassis, String driverName, String driverNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, "1");
        contentValues.put(KEY_IS_EDIT, "N");
        contentValues.put(KEY_USER, userId);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_CHASSIS, chassis);
        contentValues.put(KEY_DRIVER_NAME, driverName);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateWarrentyEntry(EditServiceRow row, String productType, String callType, String serviceType, String customerName, String mobile, String houers, String buyingDate, String installationDate, String insserviceenddate) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, String.valueOf(Integer.parseInt(row.getKEY_EDIT_LOG_COUNT()) + 1));
        contentValues.put(KEY_IS_EDIT, "Y");
        contentValues.put(KEY_EDITED_AT, currentTime);

        //long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        long result = db.update(TABLE_SERVICE_MANAGER, contentValues, KEY_ID + " = " + row.getKEY_ID(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addPaidEntry(String productType, String callType, String serviceType, String customerName,
                                String mobile, String houers, String buyingDate, String installationDate,
                                String insserviceenddate, String serincome, String userId, String rating,
                                String chassis, String driverName, String driverNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_SERVICE_INCOME, serincome);
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, "1");
        contentValues.put(KEY_IS_EDIT, "N");
        contentValues.put(KEY_USER, userId);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_CHASSIS, chassis);
        contentValues.put(KEY_DRIVER_NAME, driverName);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addGoodWillEntry(String productType, String callType, String serviceType, String customerName,
                                    String mobile, String houers, String buyingDate, String installationDate,
                                    String insserviceenddate, String serincome, String userId, String rating,
                                    String chassis, String driverName, String driverNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_SERVICE_INCOME, serincome);
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, "1");
        contentValues.put(KEY_IS_EDIT, "N");
        contentValues.put(KEY_USER, userId);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_CHASSIS, chassis);
        contentValues.put(KEY_DRIVER_NAME, driverName);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatePaidEntry(EditServiceRow row, String productType, String callType, String serviceType, String customerName, String mobile, String houers, String buyingDate, String installationDate, String insserviceenddate, String serincome) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));
        contentValues.put(KEY_SERVICE_START_DATE, formatDate(installationDate));
        contentValues.put(KEY_SERVICE_END_DATE, formatDate(insserviceenddate));
        contentValues.put(KEY_SERVICE_INCOME, serincome);
        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, String.valueOf(Integer.parseInt(row.getKEY_EDIT_LOG_COUNT()) + 1));
        contentValues.put(KEY_IS_EDIT, "Y");
        contentValues.put(KEY_EDITED_AT, currentTime);
        /*long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }*/

        long result = db.update(TABLE_SERVICE_MANAGER, contentValues, KEY_ID + " = " + row.getKEY_ID(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addPostWarrentyEntry(String productType, String callType, String serviceType,
                                        String customerName, String mobile, String houers,
                                        String buyingDate, String userId, String rating, String chassis,
                                        String driverName, String driverNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));

        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, "1");
        contentValues.put(KEY_IS_EDIT, "N");
        contentValues.put(KEY_USER, userId);
        contentValues.put(KEY_RATING, rating);
        contentValues.put(KEY_CHASSIS, chassis);
        contentValues.put(KEY_DRIVER_NAME, driverName);
        contentValues.put(KEY_DRIVER_NUMBER, driverNumber);
        long result = db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updatePostWarrentyEntry(EditServiceRow row, String productType, String callType, String serviceType, String customerName, String mobile, String houers, String buyingDate) {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE_TYPE, serviceType);
        contentValues.put(KEY_CALL_TYPE, callType);
        contentValues.put(KEY_PRODUCT, productType);
        contentValues.put(KEY_CUSTOMER_NAME, customerName);
        contentValues.put(KEY_CUSTOMER_MOBILE, mobile);
        contentValues.put(KEY_RUNNING_HOUER, houers);
        contentValues.put(KEY_CALL_SERVICE_DATE, formatDate(buyingDate));

        contentValues.put(KEY_IS_SYNCH, "N");
        contentValues.put(KEY_EDIT_LOG_COUNT, String.valueOf(Integer.parseInt(row.getKEY_EDIT_LOG_COUNT()) + 1));
        contentValues.put(KEY_IS_EDIT, "Y");
        contentValues.put(KEY_EDITED_AT, currentTime);


        long result = db.update(TABLE_SERVICE_MANAGER, contentValues, KEY_ID + " = " + row.getKEY_ID(), null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<ServiceRow> getDataForAdaptor(String userId) {
        ArrayList<ServiceRow> recList = new ArrayList<ServiceRow>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_SERVICE_MANAGER + " WHERE " + KEY_USER +"='" + userId +"' order by id desc;";
        Log.d("selectQuery",selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);
        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String customerName = cursor.getString(cursor.getColumnIndex("customer_name"));
                String customerMobile = cursor.getString(cursor.getColumnIndex("customer_mobile"));
                String houres = cursor.getString(cursor.getColumnIndex("runnign_houer"));
//                String entyDate = cursor.getString(cursor.getColumnIndex("created_at"));
                String sDate = cursor.getString(cursor.getColumnIndex("service_start_date"));
                String eDate = cursor.getString(cursor.getColumnIndex("service_end_date"));
                String isSynch = cursor.getString(cursor.getColumnIndex("is_synch"));
                String rating = cursor.getString(cursor.getColumnIndex("service_rating"));
                System.out.println("Dhorci colorstatus######-->" + customerName);
                ServiceRow rr = new ServiceRow(i, id, customerName, customerMobile, houres, sDate, eDate, isSynch, rating);
                recList.add(rr);
                i = i + 1;
                cursor.moveToNext();
            }
        } else{
            Log.d("errmsg", "NO DATA");
        }

        return recList;
    }

    public EditServiceRow getRowById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        EditServiceRow row = null;

        String selectQuery = "SELECT * FROM " + TABLE_SERVICE_MANAGER + " where id = " + id + ";";

        Cursor cursor = db.rawQuery(selectQuery, null);
        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                int KEY_ID = cursor.getInt(cursor.getColumnIndex("id"));
                String KEY_SERVER_MASTER_ID = cursor.getString(cursor.getColumnIndex("server_id"));
                String KEY_CREATED_AT = cursor.getString(cursor.getColumnIndex("created_at"));
                String KEY_EDITED_AT = cursor.getString(cursor.getColumnIndex("edited_at"));
                String KEY_PRODUCT = cursor.getString(cursor.getColumnIndex("product_type"));
                String KEY_CALL_TYPE = cursor.getString(cursor.getColumnIndex("call_type"));
                String KEY_SERVICE_TYPE = cursor.getString(cursor.getColumnIndex("service_type"));
                String KEY_IS_SYNCH = cursor.getString(cursor.getColumnIndex("is_synch"));
                String KEY_EDIT_LOG_COUNT = cursor.getString(cursor.getColumnIndex("log_count"));
                String KEY_IS_EDIT = cursor.getString(cursor.getColumnIndex("is_edited"));
                String KEY_CUSTOMER_NAME = cursor.getString(cursor.getColumnIndex("customer_name"));
                String KEY_CUSTOMER_MOBILE = cursor.getString(cursor.getColumnIndex("customer_mobile"));
                String KEY_BUYING_DATE = cursor.getString(cursor.getColumnIndex("buy_date"));
                String KEY_RUNNING_HOUER = cursor.getString(cursor.getColumnIndex("runnign_houer"));
                String KEY_INSTALLAION_DATE = cursor.getString(cursor.getColumnIndex("installation_date"));
                String KEY_CALL_SERVICE_DATE = cursor.getString(cursor.getColumnIndex("service_call_date"));
                String KEY_SERVICE_START_DATE = cursor.getString(cursor.getColumnIndex("service_start_date"));
                String KEY_SERVICE_END_DATE = cursor.getString(cursor.getColumnIndex("service_end_date"));
                String KEY_SERVICE_INCOME = cursor.getString(cursor.getColumnIndex("service_income"));
                String KEY_VISITED_DATE = cursor.getString(cursor.getColumnIndex("visited_date"));
                String KEY_SERVICE_RATING = cursor.getString(cursor.getColumnIndex("service_rating"));
                String KEY_CHASSIS = cursor.getString(cursor.getColumnIndex("service_chassis"));
                String KEY_DRIVER_NAME = cursor.getString(cursor.getColumnIndex("driver_name"));
                String KEY_DRIVER_NUMBER = cursor.getString(cursor.getColumnIndex("driver_number"));

                row = new EditServiceRow(
                        KEY_ID,
                        KEY_SERVER_MASTER_ID,
                        KEY_CREATED_AT,
                        KEY_EDITED_AT,
                        KEY_PRODUCT,
                        KEY_CALL_TYPE,
                        KEY_SERVICE_TYPE,
                        KEY_IS_SYNCH,
                        KEY_EDIT_LOG_COUNT,
                        KEY_IS_EDIT,
                        KEY_CUSTOMER_NAME,
                        KEY_CUSTOMER_MOBILE,
                        KEY_BUYING_DATE,
                        KEY_RUNNING_HOUER,
                        KEY_INSTALLAION_DATE,
                        KEY_CALL_SERVICE_DATE,
                        KEY_SERVICE_START_DATE,
                        KEY_SERVICE_END_DATE,
                        KEY_SERVICE_INCOME,
                        KEY_VISITED_DATE,
                        KEY_SERVICE_RATING,
                        KEY_CHASSIS,
                        KEY_DRIVER_NAME,
                        KEY_DRIVER_NUMBER
                );
                cursor.moveToNext();
            }
        }
        return row;
    }

    public List<EditServiceRow> getAllDataToSynch() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<EditServiceRow> allRows = new ArrayList<EditServiceRow>();

        String selectQuery = "SELECT * FROM " + TABLE_SERVICE_MANAGER + " where is_synch = 'N' order by id asc";
        Cursor cursor = db.rawQuery(selectQuery, null);

        int i = 1;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int KEY_ID = cursor.getInt(cursor.getColumnIndex("id"));
                String KEY_SERVER_MASTER_ID = cursor.getString(cursor.getColumnIndex("server_id"));
                String KEY_CREATED_AT = cursor.getString(cursor.getColumnIndex("created_at"));
                String KEY_EDITED_AT = cursor.getString(cursor.getColumnIndex("edited_at"));
                String KEY_PRODUCT = cursor.getString(cursor.getColumnIndex("product_type"));
                String KEY_CALL_TYPE = cursor.getString(cursor.getColumnIndex("call_type"));
                String KEY_SERVICE_TYPE = cursor.getString(cursor.getColumnIndex("service_type"));
                String KEY_IS_SYNCH = cursor.getString(cursor.getColumnIndex("is_synch"));
                String KEY_EDIT_LOG_COUNT = cursor.getString(cursor.getColumnIndex("log_count"));
                String KEY_IS_EDIT = cursor.getString(cursor.getColumnIndex("is_edited"));
                String KEY_CUSTOMER_NAME = cursor.getString(cursor.getColumnIndex("customer_name"));
                String KEY_CUSTOMER_MOBILE = cursor.getString(cursor.getColumnIndex("customer_mobile"));
                String KEY_BUYING_DATE = cursor.getString(cursor.getColumnIndex("buy_date"));
                String KEY_RUNNING_HOUER = cursor.getString(cursor.getColumnIndex("runnign_houer"));
                String KEY_INSTALLAION_DATE = cursor.getString(cursor.getColumnIndex("installation_date"));
                String KEY_CALL_SERVICE_DATE = cursor.getString(cursor.getColumnIndex("service_call_date"));
                String KEY_SERVICE_START_DATE = cursor.getString(cursor.getColumnIndex("service_start_date"));
                String KEY_SERVICE_END_DATE = cursor.getString(cursor.getColumnIndex("service_end_date"));
                String KEY_SERVICE_INCOME = cursor.getString(cursor.getColumnIndex("service_income"));
                String KEY_VISITED_DATE = cursor.getString(cursor.getColumnIndex("visited_date"));
                String KEY_SERVICE_RATING = cursor.getString(cursor.getColumnIndex("service_rating"));
                String KEY_CHASSIS = cursor.getString(cursor.getColumnIndex("service_chassis"));
                String KEY_DRIVER_NAME = cursor.getString(cursor.getColumnIndex("driver_name"));
                String KEY_DRIVER_NUMBER = cursor.getString(cursor.getColumnIndex("driver_number"));

                EditServiceRow row = new EditServiceRow(
                        KEY_ID,
                        KEY_SERVER_MASTER_ID,
                        KEY_CREATED_AT,
                        KEY_EDITED_AT,
                        KEY_PRODUCT,
                        KEY_CALL_TYPE,
                        KEY_SERVICE_TYPE,
                        KEY_IS_SYNCH,
                        KEY_EDIT_LOG_COUNT,
                        KEY_IS_EDIT,
                        KEY_CUSTOMER_NAME,
                        KEY_CUSTOMER_MOBILE,
                        KEY_BUYING_DATE,
                        KEY_RUNNING_HOUER,
                        KEY_INSTALLAION_DATE,
                        KEY_CALL_SERVICE_DATE,
                        KEY_SERVICE_START_DATE,
                        KEY_SERVICE_END_DATE,
                        KEY_SERVICE_INCOME,
                        KEY_VISITED_DATE,
                        KEY_SERVICE_RATING,
                        KEY_CHASSIS,
                        KEY_DRIVER_NAME,
                        KEY_DRIVER_NUMBER
                );
                allRows.add(row);
                cursor.moveToNext();
            }
        }
        return allRows;
    }

    public boolean updateAllSynStatus(int[] recIds) {
        for (int i = 0; i < recIds.length; i++) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_IS_SYNCH, "Y");
            contentValues.put(KEY_IS_EDIT, "N");
            db.update(TABLE_SERVICE_MANAGER, contentValues, KEY_ID + " = " + recIds[i], null);
        }
        return true;
    }

    public boolean isNeedSychForUpdateLocalDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        String recoveryTblSynchSql = "select count(*) as rCount From " + TABLE_SERVICE_MANAGER + ";";
        Cursor cursorRecoveryTbl = db.rawQuery(recoveryTblSynchSql, null);
        int recTblRow = 0;

        if (cursorRecoveryTbl.moveToFirst()) {
            recTblRow = cursorRecoveryTbl.getInt(0);// EventOperator
        }

        if (recTblRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAnyDataForSynch() {
        SQLiteDatabase db = this.getWritableDatabase();

        String recoveryTblSynchSql = "select count(*) as rCount From " + TABLE_SERVICE_MANAGER + " where " + KEY_IS_SYNCH + " ='N'";
        Cursor cursorRecoveryTbl = db.rawQuery(recoveryTblSynchSql, null);
        int recTblRow = 0;

        if (cursorRecoveryTbl.moveToFirst()) {
            recTblRow = cursorRecoveryTbl.getInt(0);// EventOperator
        }

        if (recTblRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String formatDate(String date) {
        String formateDateTime = "";
        String fDate = date.split(" ")[0];
        String time = date.split(" ")[1];
        String[] tiSize = time.split(":");

        if (tiSize.length == 2) {
            String sDate = tiSize[0] + ":" + tiSize[1] + ":" + "00";
            formateDateTime = fDate.split("-")[2] + "-" + fDate.split("-")[1] + "-" + fDate.split("-")[0] + " " + sDate;
        } else {
            formateDateTime = date;
        }
        return formateDateTime;
    }

    public boolean syncSaveAllData(List<SynchDataRow> myCustomerList, String userId) {

        for (SynchDataRow mCus : myCustomerList) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, mCus.MobileId);
            System.out.println("--"+mCus.CategoryId_id+"--"+mCus.CallTypeId_id+"--"+
                    mCus.ProductId_id+"--"+mCus.CustomerName+"--"+mCus.Mobile+"--"+mCus.TractorPurchaseDate+"--"+mCus.HoursProvided
                    +"--"+mCus.DateOfInstallation+"--"+mCus.ServiceStartDate+"--"+mCus.ServiceEndDate+"--"+mCus.ServiceIncome
            );
            Log.d("CategoryId_id", mCus.CategoryId_id);
            contentValues.put(KEY_SERVICE_TYPE, mCus.CategoryId_id);
            contentValues.put(KEY_CALL_TYPE, mCus.CallTypeId_id);
            contentValues.put(KEY_PRODUCT, mCus.ProductId_id);
            contentValues.put(KEY_CUSTOMER_NAME, mCus.CustomerName);
            contentValues.put(KEY_CUSTOMER_MOBILE, mCus.Mobile);
            contentValues.put(KEY_BUYING_DATE, mCus.TractorPurchaseDate.replace("T", " "));
            contentValues.put(KEY_RUNNING_HOUER, mCus.HoursProvided);
            contentValues.put(KEY_INSTALLAION_DATE, mCus.DateOfInstallation.replace("T", " "));

            contentValues.put(KEY_CALL_SERVICE_DATE, mCus.ServiceDemandDate.replace("T", " "));
            contentValues.put(KEY_SERVICE_START_DATE, mCus.ServiceStartDate.replace("T", " "));
            contentValues.put(KEY_SERVICE_END_DATE, mCus.ServiceEndDate.replace("T", " "));
            contentValues.put(KEY_CHASSIS, mCus.Chassis);

            contentValues.put(KEY_SERVICE_INCOME, mCus.ServiceIncome);
            contentValues.put(KEY_IS_SYNCH, "Y");
            contentValues.put(KEY_USER, userId);


            db.insert(TABLE_SERVICE_MANAGER, null, contentValues);
        }


        return true;
    }
}
