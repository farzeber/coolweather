package com.coolweather.android.db;

import android.provider.ContactsContract;

import org.litepal.crud.DataSupport;

/**
 * Created by mick2017 on 2017/8/24.
 */

public class City extends DataSupport{
    private int id;
    private String cityName;
    private int cityCode;
    private int provinceID;
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void setCityName(String cityName){
        this.cityName=cityName;
    }
    public String getCityName(){
        return  cityName;
    }
    public void setCityCode(int cityCode){
        this.cityCode=cityCode;
    }
    public  int getCityCode(){
        return cityCode;
    }
    public void setProvinceID(int provinceID){
        this.provinceID=provinceID;
    }
    public int getProvinceID(){
        return provinceID;
    }

}
