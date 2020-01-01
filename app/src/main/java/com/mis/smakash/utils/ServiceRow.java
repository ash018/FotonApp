package com.mis.smakash.utils;

public class ServiceRow {
    public int sl;
    public int id;
    public String customerName;
    public String customerMobile;
    public String houres;
    public String entyDate;
    public String isSynch;
    public String endDate;
    public String rating;

    public ServiceRow(int sl, int id, String customerName, String customerMobile, String houres, String entyDate, String endDate, String isSynch, String rating){
        this.sl = sl;
        this.id = id;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.houres = houres;
        this.entyDate = entyDate;
        this.endDate = endDate;
        this.isSynch = isSynch;
        this.rating = rating;
    }
    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getHoures() {
        return houres;
    }

    public void setHoures(String houres) {
        this.houres = houres;
    }

    public String getEntyDate() {
        return entyDate;
    }

    public void setEntyDate(String entyDate) {
        this.entyDate = entyDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIsSynch() {
        return isSynch;
    }

    public void setIsSynch(String isSynch) {
        this.isSynch = isSynch;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
