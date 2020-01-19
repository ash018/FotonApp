package com.mis.smakash.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EditServiceRow implements Serializable {
    public int KEY_ID;
    public String KEY_SERVER_MASTER_ID;
    public String KEY_CREATED_AT;
    public String KEY_EDITED_AT;
    public String KEY_PRODUCT;
    public String KEY_CALL_TYPE;
    public String KEY_SERVICE_TYPE;
    public String KEY_IS_SYNCH;
    public String KEY_EDIT_LOG_COUNT;
    public String KEY_IS_EDIT;

    public String KEY_CUSTOMER_NAME;
    public String KEY_CUSTOMER_MOBILE;
    public String KEY_BUYING_DATE;
    public String KEY_RUNNING_HOUER;
    public String KEY_INSTALLAION_DATE;

    public String KEY_CALL_SERVICE_DATE;
    public String KEY_SERVICE_START_DATE;
    public String KEY_SERVICE_END_DATE;

    public String KEY_SERVICE_INCOME;
    public String KEY_VISITED_DATE;

    public String KEY_RATING;
    public String KEY_CHASSIS;

    public String KEY_DRIVER_NAME;
    public String KEY_DRIVER_NUMBER;

    public EditServiceRow(int KEY_ID,
                          String KEY_SERVER_MASTER_ID,
                          String KEY_CREATED_AT,
                          String KEY_EDITED_AT,
                          String KEY_PRODUCT,
                          String KEY_CALL_TYPE,
                          String KEY_SERVICE_TYPE,
                          String KEY_IS_SYNCH,
                          String KEY_EDIT_LOG_COUNT,
                          String KEY_IS_EDIT,
                          String KEY_CUSTOMER_NAME,
                          String KEY_CUSTOMER_MOBILE,
                          String KEY_BUYING_DATE,
                          String KEY_RUNNING_HOUER,
                          String KEY_INSTALLAION_DATE,
                          String KEY_CALL_SERVICE_DATE,
                          String KEY_SERVICE_START_DATE,
                          String KEY_SERVICE_END_DATE,
                          String KEY_SERVICE_INCOME,
                          String KEY_VISITED_DATE,
                          String KEY_RATING,
                          String KEY_CHASSIS,
                          String KEY_DRIVER_NAME,
                          String KEY_DRIVER_NUMBER){
                this.KEY_ID = KEY_ID;
                this.KEY_SERVER_MASTER_ID = KEY_SERVER_MASTER_ID;
                this.KEY_CREATED_AT = KEY_CREATED_AT;
                this.KEY_EDITED_AT = KEY_EDITED_AT;
                this.KEY_PRODUCT = KEY_PRODUCT;
                this.KEY_CALL_TYPE = KEY_CALL_TYPE;
                this.KEY_SERVICE_TYPE = KEY_SERVICE_TYPE;
                this.KEY_IS_SYNCH = KEY_IS_SYNCH;
                this.KEY_EDIT_LOG_COUNT = KEY_EDIT_LOG_COUNT;
                this.KEY_IS_EDIT = KEY_IS_EDIT;
                this.KEY_CUSTOMER_NAME = KEY_CUSTOMER_NAME;
                this.KEY_CUSTOMER_MOBILE = KEY_CUSTOMER_MOBILE;
                this.KEY_BUYING_DATE = KEY_BUYING_DATE;
                this.KEY_RUNNING_HOUER = KEY_RUNNING_HOUER;
                this.KEY_INSTALLAION_DATE = KEY_INSTALLAION_DATE;
                this.KEY_CALL_SERVICE_DATE = KEY_CALL_SERVICE_DATE;
                this.KEY_SERVICE_START_DATE = KEY_SERVICE_START_DATE;
                this.KEY_SERVICE_END_DATE = KEY_SERVICE_END_DATE;
                this.KEY_SERVICE_INCOME = KEY_SERVICE_INCOME;
                this.KEY_VISITED_DATE = KEY_VISITED_DATE;
                this.KEY_RATING = KEY_RATING;
                this.KEY_CHASSIS =KEY_CHASSIS;
                this.KEY_DRIVER_NAME = KEY_DRIVER_NAME;
                this.KEY_DRIVER_NUMBER = KEY_DRIVER_NUMBER;
    }

    public int getKEY_ID() {
        return KEY_ID;
    }

    public void setKEY_ID(int KEY_ID) {
        this.KEY_ID = KEY_ID;
    }

    public String getKEY_SERVER_MASTER_ID() {
        return KEY_SERVER_MASTER_ID;
    }

    public void setKEY_SERVER_MASTER_ID(String KEY_SERVER_MASTER_ID) {
        this.KEY_SERVER_MASTER_ID = KEY_SERVER_MASTER_ID;
    }

    public String getKEY_CREATED_AT() {
        return KEY_CREATED_AT;
    }

    public void setKEY_CREATED_AT(String KEY_CREATED_AT) {
        this.KEY_CREATED_AT = KEY_CREATED_AT;
    }

    public String getKEY_EDITED_AT() {
        return KEY_EDITED_AT;
    }

    public void setKEY_EDITED_AT(String KEY_EDITED_AT) {
        this.KEY_EDITED_AT = KEY_EDITED_AT;
    }

    public String getKEY_PRODUCT() {
        return KEY_PRODUCT;
    }

    public void setKEY_PRODUCT(String KEY_PRODUCT) {
        this.KEY_PRODUCT = KEY_PRODUCT;
    }

    public String getKEY_CALL_TYPE() {
        return KEY_CALL_TYPE;
    }

    public void setKEY_CALL_TYPE(String KEY_CALL_TYPE) {
        this.KEY_CALL_TYPE = KEY_CALL_TYPE;
    }

    public String getKEY_SERVICE_TYPE() {
        return KEY_SERVICE_TYPE;
    }

    public void setKEY_SERVICE_TYPE(String KEY_SERVICE_TYPE) {
        this.KEY_SERVICE_TYPE = KEY_SERVICE_TYPE;
    }

    public String getKEY_IS_SYNCH() {
        return KEY_IS_SYNCH;
    }

    public void setKEY_IS_SYNCH(String KEY_IS_SYNCH) {
        this.KEY_IS_SYNCH = KEY_IS_SYNCH;
    }

    public String getKEY_EDIT_LOG_COUNT() {
        return KEY_EDIT_LOG_COUNT;
    }

    public void setKEY_EDIT_LOG_COUNT(String KEY_EDIT_LOG_COUNT) {
        this.KEY_EDIT_LOG_COUNT = KEY_EDIT_LOG_COUNT;
    }

    public String getKEY_IS_EDIT() {
        return KEY_IS_EDIT;
    }

    public void setKEY_IS_EDIT(String KEY_IS_EDIT) {
        this.KEY_IS_EDIT = KEY_IS_EDIT;
    }

    public String getKEY_CUSTOMER_NAME() {
        return KEY_CUSTOMER_NAME;
    }

    public void setKEY_CUSTOMER_NAME(String KEY_CUSTOMER_NAME) {
        this.KEY_CUSTOMER_NAME = KEY_CUSTOMER_NAME;
    }

    public String getKEY_CUSTOMER_MOBILE() {
        return KEY_CUSTOMER_MOBILE;
    }

    public void setKEY_CUSTOMER_MOBILE(String KEY_CUSTOMER_MOBILE) {
        this.KEY_CUSTOMER_MOBILE = KEY_CUSTOMER_MOBILE;
    }

    public String getKEY_BUYING_DATE() {
        return KEY_BUYING_DATE;
    }

    public void setKEY_BUYING_DATE(String KEY_BUYING_DATE) {
        this.KEY_BUYING_DATE = KEY_BUYING_DATE;
    }

    public String getKEY_RUNNING_HOUER() {
        return KEY_RUNNING_HOUER;
    }

    public void setKEY_RUNNING_HOUER(String KEY_RUNNING_HOUER) {
        this.KEY_RUNNING_HOUER = KEY_RUNNING_HOUER;
    }

    public String getKEY_INSTALLAION_DATE() {
        return KEY_INSTALLAION_DATE;
    }

    public void setKEY_INSTALLAION_DATE(String KEY_INSTALLAION_DATE) {
        this.KEY_INSTALLAION_DATE = KEY_INSTALLAION_DATE;
    }

    public String getKEY_CALL_SERVICE_DATE() {
        return KEY_CALL_SERVICE_DATE;
    }

    public void setKEY_CALL_SERVICE_DATE(String KEY_CALL_SERVICE_DATE) {
        this.KEY_CALL_SERVICE_DATE = KEY_CALL_SERVICE_DATE;
    }

    public String getKEY_SERVICE_START_DATE() {
        return KEY_SERVICE_START_DATE;
    }

    public void setKEY_SERVICE_START_DATE(String KEY_SERVICE_START_DATE) {
        this.KEY_SERVICE_START_DATE = KEY_SERVICE_START_DATE;
    }

    public String getKEY_SERVICE_END_DATE() {
        return KEY_SERVICE_END_DATE;
    }

    public void setKEY_SERVICE_END_DATE(String KEY_SERVICE_END_DATE) {
        this.KEY_SERVICE_END_DATE = KEY_SERVICE_END_DATE;
    }

    public String getKEY_SERVICE_INCOME() {
        return KEY_SERVICE_INCOME;
    }

    public void setKEY_SERVICE_INCOME(String KEY_SERVICE_INCOME) {
        this.KEY_SERVICE_INCOME = KEY_SERVICE_INCOME;
    }

    public String getKEY_VISITED_DATE() {
        return KEY_VISITED_DATE;
    }

    public void setKEY_VISITED_DATE(String KEY_VISITED_DATE) {
        this.KEY_VISITED_DATE = KEY_VISITED_DATE;
    }

    public String getKEY_RATING() { return KEY_RATING; }

    public void setKEY_RATING() {
        this.KEY_RATING =  KEY_RATING;
    }

    public String getKEY_CHASSIS() { return KEY_CHASSIS; }

    public void setKEY_CHASSIS() {
        this.KEY_CHASSIS =  KEY_CHASSIS;
    }

    public String getKEY_DRIVER_NAME() { return KEY_DRIVER_NAME; }

    public void setDRIVER_NAME() {
        this.KEY_DRIVER_NAME =  KEY_DRIVER_NAME;
    }

    public String getKEY_DRIVER_NUMBER() { return KEY_DRIVER_NUMBER; }

    public void setDRIVER_NUMBER() {
        this.KEY_DRIVER_NUMBER =  KEY_DRIVER_NUMBER;
    }
}
