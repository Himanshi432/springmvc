package com.rest.dao;

import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.Date;

public class Client extends BasicDBObject {
    String client_id;
    String client_name;
    String client_dob;
    String client_city;
    String client_state;
    String client_country;
    int client_zip_code;
    int client_landline;
    Long client_mobile;
    String client_email;
    String client_role;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_dob() {
        return client_dob;
    }

    public void setClient_dob(String client_dob) {
        this.client_dob = client_dob;
    }

    public String getClient_city() {
        return client_city;
    }

    public void setClient_city(String client_city) {
        this.client_city = client_city;
    }

    public String getClient_state() {
        return client_state;
    }

    public void setClient_state(String client_state) {
        this.client_state = client_state;
    }

    public String getClient_country() {
        return client_country;
    }

    public void setClient_country(String client_country) {
        this.client_country = client_country;
    }

    public int getClient_zip_code() {
        return client_zip_code;
    }

    public void setClient_zip_code(int client_zip_code) {
        this.client_zip_code = client_zip_code;
    }

    public int getClient_landline() {
        return client_landline;
    }

    public void setClient_landline(int client_landline) {
        this.client_landline = client_landline;
    }

    public Long getClient_mobile() {
        return client_mobile;
    }

    public void setClient_mobile(Long client_mobile) {
        this.client_mobile = client_mobile;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_role() {
        return client_role;
    }

    public void setClient_role(String client_role) {
        this.client_role = client_role;
    }

}
