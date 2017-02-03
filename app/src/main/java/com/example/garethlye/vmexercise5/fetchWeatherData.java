package com.example.garethlye.vmexercise5;

/**
 * Created by garethlye on 02/02/2017.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class fetchWeatherData {

    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&APPID=561849bb92b0871e3d22e80747a6a8be";

    public static JSONObject getJSON(Context context, String city){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            Log.e("URl", "Url string format passed");
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            Log.e("HTTP", "http connection open passed");

            connection.addRequestProperty("x-api-key",
                    context.getString(R.string.open_weather_maps_app_id));
            Log.e("connection", "request x-api-key passed");

            StringBuilder json = new StringBuilder(1024);
            Log.e("String Buffer", "String buffer passed");

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),8024);
            Log.e("Reader", "Reader Passed");

            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                Log.e("", "");
                return null;
            }

            if(data.getInt("cod")== 404){
                Log.e("Request","Request failed");
            }

            return data;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
