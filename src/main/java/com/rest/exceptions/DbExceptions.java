package com.rest.exceptions;

public class DbExceptions extends Exception{
//insert generic exceptions
    public DbExceptions(String dbExceptionMessage){
        super(dbExceptionMessage);}
}
