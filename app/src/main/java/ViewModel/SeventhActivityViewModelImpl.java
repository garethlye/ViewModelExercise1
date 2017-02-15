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
import rx.functions.Action0;
import rx.functions.Action1;
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
    private FetchDataViewModel mFetchDataViewModel;


    public SeventhActivityViewModelImpl(SeventhActivity seventhActivity, Spinner citySpinner, FetchDataViewModel fetchDataViewModel) {
        mSeventhActivity = seventhActivity;
        mCitySpinner = citySpinner;
        mFetchDataViewModel = fetchDataViewModel;

        //rxJava_firstStyle(); //older method, don't use
        //rxJava_secondStyle(); //older method, don't use
        setup1();
    }


    private void setup1() {
        mCitySpinner.setOnItemSelectedListener(SpinnerListener);
    }

    private AdapterView.OnItemSelectedListener SpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
            selectedCity.set(mCitySpinner.getSelectedItem().toString());
            setWeatherData(mCitySpinner.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(final AdapterView<?> adapterView) {

        }
    };

    private void setWeatherData(final String city) {
        mFetchDataViewModel.fromJSON(mSeventhActivity, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.e("Weather call", "Weather call worked!");
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {
                        Log.e("Weather Data Error", "Failed to obtain/display weather data");
                    }
                })
                .subscribe(new Action1<FetchDataViewModel>() {
                    @Override
                    public void call(final FetchDataViewModel weatherData) {
                        chosenCity.set(weatherData.getCityWeather());
                        temp.set(weatherData.getWeatherTemp());
                        time.set(weatherData.getCityTime());
                        desc.set(weatherData.getWeatherDesc());
                        }
                    });
                }

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
    public ObservableField<String> getSelectedCity() {
        return selectedCity;
    }

    @Override
    public ObservableField<String> getChosenCity() {
        return chosenCity;
    }


    ////////////////////CODES BELOW ARE NOTES, NOTHING MORE//////////////////////////


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
            @Override
            public void call(String s) {
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


}
