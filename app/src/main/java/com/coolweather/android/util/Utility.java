package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mick2017 on 2017/8/24.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)) {
            try{
                JSONArray allProvinces=new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
        }


    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)) {
            try{
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceID(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCountyResponse(String response,int cityId){
        Log.d("next", "handleCountyResponse: ");
        if (!TextUtils.isEmpty(response)) {
            Log.d("next1", "handleCountyResponse: ");
            try{
                JSONArray allCounties=new JSONArray(response);
                Log.d("next2", "handleCountyResponse: ");
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();
                }
                Log.d("follow", "handleCountyResponse: ");
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static Weather hanleWeatherResponse(String response){
        try{
            StringBuilder sb=new StringBuilder(response);

            Log.d(sb.toString(), "hanleWeatherResponse: ");
            if(response!=null){
                Log.d("first", "hanleWeatherResponse: ");
            }
            if(response.startsWith("\ufeff")){
                response=response.substring(1);
                Log.d("h1", "hanleWeatherResponse: ");
            }
                JSONObject jsonObject=new JSONObject(response);

                JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");

                String weatherContent=jsonArray.getJSONObject(0).toString();

                return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    }

