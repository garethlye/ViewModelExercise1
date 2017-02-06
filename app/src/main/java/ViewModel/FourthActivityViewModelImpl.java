package ViewModel;

import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.garethlye.vmexercise5.FifthActivity;
import com.example.garethlye.vmexercise5.FourthActivity;
import com.example.garethlye.vmexercise5.R;
import com.example.garethlye.vmexercise5.fetchWeatherData;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by garethlye on 02/02/2017.
 **/

public class FourthActivityViewModelImpl implements FourthActivityViewModel {

    private final FourthActivity mFourthActivity;
    private       Spinner        mCitySpinner;
    private ObservableField<String> selectedCity = new ObservableField<>();
    private ObservableField<String> chosenCity         = new ObservableField<>();
    private ObservableField<String> temp         = new ObservableField<>();
    private ObservableField<String> time         = new ObservableField<>();
    private ObservableField<String> desc         = new ObservableField<>();
    private Handler mHandler;

    public FourthActivityViewModelImpl(final FourthActivity fourthActivity, Spinner citySpinner) {
        mFourthActivity = fourthActivity;
        mCitySpinner = citySpinner;
        setup();
        mHandler = new Handler();
    }

    private void setup() {
        mCitySpinner.setOnItemSelectedListener(SpinnerListener);
    }

    private AdapterView.OnItemSelectedListener SpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
            selectedCity.set(mCitySpinner.getSelectedItem().toString());
            updateWeatherData(mCitySpinner.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(final AdapterView<?> adapterView) {

        }
    };



    private void updateWeatherData(final String city) {
        //final JSONObject json = fetchWeatherData.getJSON(mFourthActivity, city);

        new Thread() {
            public void run() {
                String citySelected = city.replaceAll("\\s","");
                final JSONObject json = fetchWeatherData.getJSON(mFourthActivity, citySelected);
                if (json == null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            chosenCity.set("Failed to obtain Weather Data... :(");
                            desc.set(mFourthActivity.getString(R.string.place_not_found));
                            time.set(mFourthActivity.getString(R.string.place_not_found));
                            temp.set(mFourthActivity.getString(R.string.place_not_found));
                            //Toast.makeText(mFourthActivity,
                                    //mFourthActivity.getString(R.string.place_not_found),
                                    //Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try {
            chosenCity.set(json.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            desc.set(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            temp.set(
                    String.format("%.2f", main.getDouble("temp"))+ " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            time.set("Last update: " + updatedOn);

        }catch(Exception e){
            Log.e("Weather Error", "One or more fields not found in the JSON data");
        }
    }

    @Override
    public void onNextActivityClicked(final View view){
        startFifthActivity();
    }

    private void startFifthActivity(){
        Intent i = new Intent(mFourthActivity, FifthActivity.class);
        mFourthActivity.startActivity(i);
    }


    @Override
    public ObservableField<String> getTemp() {
        return temp;
    }

    @Override
    public ObservableField<String> getTime() {
        return time;
    }

    @Override
    public ObservableField<String> getDesc() {
        return desc;
    }

    @Override
    public ObservableField<String> getSelectedCity(){
        return selectedCity;
    }

    @Override
    public ObservableField<String> getChosenCity() {
        return chosenCity;
    }

}
