package ViewModel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.garethlye.vmexercise5.R;
import com.example.garethlye.vmexercise5.SeventhActivity;
import com.example.garethlye.vmexercise5.fetchWeatherData;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Handler;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by garethlye on 08/02/2017.
 **/

public class SeventhActivityViewModelImpl implements SeventhActivityViewModel {

    private String textValue  = "Results Start Below";
    private String textValue2 = "Results Start Below";
    private SeventhActivity mSeventhActivity;

    private Spinner mCitySpinner;
    private ObservableField<String> selectedCity = new ObservableField<>();
    private ObservableField<String> chosenCity   = new ObservableField<>();
    private ObservableField<String> temp         = new ObservableField<>();
    private ObservableField<String> time         = new ObservableField<>();
    private ObservableField<String> desc         = new ObservableField<>();
    private Handler mHandler;

    public SeventhActivityViewModelImpl(SeventhActivity seventhActivity, Spinner citySpinner) {
        mSeventhActivity = seventhActivity;
        mHandler = new Handler();
        mCitySpinner = citySpinner;
        //rxJava_firstStyle();
        //rxJava_secondStyle();
        setup1();
    }


    private void setup1() {
        mCitySpinner.setOnItemSelectedListener(SpinnerListener);
    }

    private AdapterView.OnItemSelectedListener SpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
            selectedCity.set(mCitySpinner.getSelectedItem().toString());
            fetchWeatherData2(mCitySpinner.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(final AdapterView<?> adapterView) {

        }
    };

    private void fetchWeatherData2(final String city) {
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
    }

    private void renderWeather(JSONObject json) {
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
                    String.format("%.2f", main.getDouble("temp")) + " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt") * 1000));
            time.set("Last update: " + updatedOn);

        } catch (Exception e) {
            Log.e("Weather Error", "One or more fields not found in the JSON data");
        }
    }


    ////////////////This is the most verbose way to write down observable and subscriber....
    private void rxJava_firstStyle() {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onNext("Yo! Second Text :D");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                setText(s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };


        myObservable.subscribe(mySubscriber);

    }
////////////////////////


    //This can be done in a much shorter way thanks to RXJAVA built-in creation methods

    private void rxJava_secondStyle() {

        Observable<String> myObservable =
         Observable.just("Hello, world!");   //outputs just one line of code


         Action1<String> onNextAction = new Action1<String>() {
        @Override public void call(String s) {
        setText2(s);
        setText2("Haha second text here :P");
        }
        };

         myObservable.subscribe(onNextAction); // you can add ,onErrorAction, onCompleteAction too

        ///but an even shorter wayyy is to combine both methods together!

        Observable.just("Hello, world!")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        setText2(s);
                        setText2("Haha hola, second text here :P");
                    }
                });


    }

    /////////////////////////////////
    private void setText(String text) {
        textValue = textValue + "\n" + text;
    }

    private void setText2(String text) {
        textValue2 = textValue2 + "\n" + text;
    }

    @Override
    public String getTextValue() {
        return textValue;
    }

    @Override
    public String getTextValue2() {
        return textValue2;
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
