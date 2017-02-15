package ViewModel;

import android.content.Context;
import android.util.Log;

import com.example.garethlye.vmexercise5.R;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by garethlye on 13/02/2017.
 **/

public class FetchDataViewModel {

    private String city;
    private String desc;
    private String temp;
    private String time;
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    public String getCityWeather() {
        return this.city;
    }

    public String getWeatherDesc() {
        return this.desc;
    }

    public String getWeatherTemp() {
        return this.temp;
    }

    public String getCityTime() {
        return this.time;
    }

    public FetchDataViewModel(){

    }


    Observable<FetchDataViewModel> fromJSON(final Context context, final String city) {
        return Observable.just("")
                .map(new Func1<String, FetchDataViewModel>() {
                    @Override
                    public FetchDataViewModel call(final String s) {
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

                            String tmp;
                            while((tmp=reader.readLine())!=null)
                                json.append(tmp).append("\n");
                            reader.close();

                            JSONObject data = new JSONObject(json.toString());

                            if(data.getInt("cod") != 200){
                                Log.e("cod", "cod value does not equal to 200!");
                                return null;
                            }

                            if(data.getInt("cod")== 404){
                                Log.e("Request","Request failed!");
                            }

                            FetchDataViewModel fetchDataViewModel = new FetchDataViewModel();
                            if (data == null) {
                                fetchDataViewModel.city = ("Failed to obtain Weather Data... :(");
                                fetchDataViewModel.desc = context.getString(R.string.place_not_found);
                                fetchDataViewModel.time= context.getString(R.string.place_not_found);
                                fetchDataViewModel.temp = context.getString(R.string.place_not_found);
                            }
                            else {
                                try {
                                    fetchDataViewModel.city = (data.getString("name").toUpperCase(Locale.US) +
                                            ", " +
                                            data.getJSONObject("sys").getString("country"));

                                    JSONObject details = data.getJSONArray("weather").getJSONObject(0);
                                    JSONObject main = data.getJSONObject("main");

                                    fetchDataViewModel.desc = (details.getString("description").toUpperCase(Locale.US) +
                                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

                                    fetchDataViewModel.temp = (String.format("%.2f", main.getDouble("temp")) + " ℃");

                                    DateFormat df = DateFormat.getDateTimeInstance();
                                    String updatedOn = df.format(new Date(data.getLong("dt") * 1000));

                                    fetchDataViewModel.time = ("Last update: " + updatedOn);
                                } catch (Exception e) {
                                    Log.e("Weather Error", "One or more fields not found in the JSON data");

                                }
                            }
                            return fetchDataViewModel;


                        }catch(Exception e){
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
    }


    /**private static JSONObject getWeatherInfo(final String city){
        return Observable.just("")
                    .map(new Func1<String, JSONObject>() {
                        @Override
                        public JSONObject call(final String s){
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

                                String tmp;
                                while((tmp=reader.readLine())!=null)
                                    json.append(tmp).append("\n");
                                reader.close();

                                JSONObject data = new JSONObject(json.toString());

                                if(data.getInt("cod") != 200){
                                    Log.e("cod", "cod value does not equal to 200!");
                                    return null;
                                }

                                if(data.getInt("cod")== 404){
                                    Log.e("Request","Request failed!");
                                }

                                return data;
                            }catch(Exception e){
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }).doOnError(new Action1<Throwable>() {
                @Override
                public void call(final Throwable throwable) {
                    Log.e("Error on API Call!", "Error thrown from API class");
                }
            });
        }**/





/**
    //////older method without delegation, please do not use
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

            String tmp;
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                Log.e("cod", "cod value does not equal to 200!");
                return null;
            }

            if(data.getInt("cod")== 404){
                Log.e("Request","Request failed!");
            }

            return data;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    Observable<Response<UserResponse>> authenticate(final String code, final String request_id, final boolean login) {
     return mCityManager.getCurrentCity()
     .flatMap(new Func1<City, Observable<Response<UserResponse>>>() {
    @Override
    public Observable<Response<UserResponse>> call(final City city) {
    if (login) {
    return mSessionAPI.login(new SessionRequest(code, request_id, city.getSlug()));
    }
    else {
    return mSessionAPI.authenticate(new SessionRequest(code, request_id, city.getSlug()));
    }
    }
    });
     }**/





    /**private void fetchWeatherData2(final String city) {
     Observable.just("")
     .subscribeOn(Schedulers.io())
     .map(new Func1<String, JSONObject>() {

    @Override
    public JSONObject call(final String s) {
    return fetchWeatherData.getJSON(mSeventhActivity, city);
    }
    })
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(new Action1<JSONObject>() {
    @Override
    public void call(final JSONObject jsonObject) {
    new Thread() {
    public void run() {

    if (jsonObject == null) {
    mHandler.post(new Runnable() {
    @Override
    public void run() {
    chosenCity.set("Failed to obtain Weather Data... :(");
    desc.set(mSeventhActivity.getString(R.string.place_not_found));
    time.set(mSeventhActivity.getString(R.string.place_not_found));
    temp.set(mSeventhActivity.getString(R.string.place_not_found));
    }
    });
    }
    else {
    mHandler.post(new Runnable() {
    @Override
    public void run() {
    renderWeather(jsonObject);
    }
    });
    }
    }
    }.start();
    }
    });
     }**/

}
