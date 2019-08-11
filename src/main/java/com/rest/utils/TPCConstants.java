package com.rest.utils;

public class TPCConstants {
    public final static String DB_STRING = "mongodb://himanshi:himanshi@localhost:27017/TPC";
    public final static String DB_NAME = "tpc";
    public final static String MONGO_CLIENT_COLLECTION = "client_meta";
    public final static String MONGO_ROLE_COLLECTION = "role_meta";
    public final static String MONGO_ADDRESS_COLLECTION = "address_meta";
    public final static String MONGO_CREATED = "New row inserted sucessfully";
    public final static String MONGO_NO_MATCH_FOUND = "No rows found";
    public final static String API_NO_MATCH_FOUND = "No row with this primary key exists";
    public final static String MONGO_ID_NULL = "Primary key cannot be null";
    public final static String MONGO_DUPLICATE_KEY = "duplicate key error";
    public final static String API_DUPLICATE_KEY = "This unique id already exist.Try another ID";
}
