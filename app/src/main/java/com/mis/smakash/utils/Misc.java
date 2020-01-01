package com.mis.smakash.utils;

import java.util.Calendar;

public class Misc {
    private int mYear;
    private int mMonth;
    private int mDay;

    private String CustomerDetailURL = "http://116.68.205.71:4003/fotonservice/api/v0/getcustomerdetail/";

    public String getGetCustomerDetailURL(){
        return this.CustomerDetailURL;
    }

    public boolean checkPrevDate(int dayOfMonth,int monthOfYear,int year){

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        String now = String.valueOf(mYear)+String.valueOf(mMonth+1)+String.valueOf(mDay);

        int length = (int)(Math.log10(dayOfMonth)+1);
        String parseDate = "";
        if(length<2){
            parseDate = String.valueOf(year)+String.valueOf(monthOfYear+1)+'0'+String.valueOf(dayOfMonth);
        }
        else{
            parseDate = String.valueOf(year)+String.valueOf(monthOfYear+1)+String.valueOf(dayOfMonth);
        }
        int nowV = Integer.parseInt(now);
        int parseV = Integer.parseInt(parseDate);

        if(parseV >= nowV){
            return true;
        }
        else {
            return false;
        }
    }
}
