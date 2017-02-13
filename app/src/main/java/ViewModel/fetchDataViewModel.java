package ViewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.garethlye.vmexercise5.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by garethlye on 13/02/2017.
 */

public class FetchDataViewModel {

    public FetchDataViewModel(){

    }

    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    Observable<JSONObject> getWeatherInfo(final Context context, final String city){
        return Observable.just("")
                .subscribeOn(Schedulers.io())
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

                    }
                });
    }

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

    /**Observable<Response<UserResponse>> authenticate(final String code, final String request_id, final boolean login) {
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
