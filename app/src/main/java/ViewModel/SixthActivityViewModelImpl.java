package ViewModel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.example.garethlye.vmexercise5.SixthActivity;

import DependencyInjectionExample2.Battery_engine;
import DependencyInjectionExample2.CarComponent;
import DependencyInjectionExample2.Compressor_aircond;
import DependencyInjectionExample2.Diesel_engine;
import DependencyInjectionExample2.Fan_aircond;
import DependencyInjectionExample2.Sensor_gps;
import DependencyInjectionExample2.Speaker_gps;


public class SixthActivityViewModelImpl implements SixthActivityViewModel {

    private final SixthActivity mSixthActivity;
    private final CarComponent  mCarComponent;

    private Battery_engine     mBattery_engine;
    private Compressor_aircond mCompressor_aircond;
    private Diesel_engine      mDiesel_engine;
    private Fan_aircond        mFan_aircond;
    private Sensor_gps         mSensor_gps;
    private Speaker_gps        mSpeaker_gps;

    private ObservableField<String> Diesel     = new ObservableField<>();
    private ObservableField<String> Battery    = new ObservableField<>();
    private ObservableField<String> Fan        = new ObservableField<>();
    private ObservableField<String> Compressor = new ObservableField<>();
    private ObservableField<String> Speaker    = new ObservableField<>();
    private ObservableField<String> Sensor     = new ObservableField<>();

    private ObservableField<String> engineStatus  = new ObservableField<>();
    private ObservableField<String> aircondStatus = new ObservableField<>();
    private ObservableField<String> gpsStatus     = new ObservableField<>();


    public SixthActivityViewModelImpl(SixthActivity sixthActivity, CarComponent carComponent) {
        mSixthActivity = sixthActivity;
        mCarComponent = carComponent;
        setup();
    }

    private void setup() {
        mBattery_engine = mCarComponent.provideBattery();
        mCompressor_aircond = mCarComponent.provideCompressor();
        mDiesel_engine = mCarComponent.provideDiesel();
        mFan_aircond = mCarComponent.provideFan();
        mSensor_gps = mCarComponent.provideSensor();
        mSpeaker_gps = mCarComponent.provideSpeaker();
        setup2();
    }

    private void setup2() {
        if (mBattery_engine != null)
            Log.e("Battery Check", "Battery OK!");
        if (mCompressor_aircond != null)
            Log.e("Compressor Check", "Compressor OK!");
        if (mDiesel_engine != null)
            Log.e("Diesel Check", "Diesel OK!");
        if (mFan_aircond != null)
            Log.e("Fan Check", "Fan OK!");
        if (mSensor_gps != null)
            Log.e("Sensor Check", "Sensor OK!");
        if (mSpeaker_gps != null)
            Log.e("Speaker Check", "Speaker OK!");

        Diesel.set("Diesel : OFF");
        Battery.set("Battery : OFF");
        Fan.set("Fan : OFF");
        Compressor.set("Compressor : OFF");
        Speaker.set("Speaker : OFF");
        Sensor.set("Sensor : OFF");

        engineStatus.set("Engine OFF");
        aircondStatus.set("Aircond OFF");
        gpsStatus.set("GPS OFF");
    }

    @Override
    public void onDieselClicked(final View view) {
        if ((Diesel.get()) == "Diesel : ON") {
            Diesel.set("Diesel : OFF");
            mDiesel_engine.disableDiesel();
            checkEngine();
        }
        else {
            Diesel.set("Diesel : ON");
            mDiesel_engine.enableDiesel();
            checkEngine();
        }
    }

    @Override
    public void onBatteryClicked(final View view) {
        if ((Battery.get()).equals("Battery : ON")) {
            Battery.set("Battery : OFF");
            mBattery_engine.disableBattery();
            checkEngine();
        }
        else {
            Battery.set("Battery : ON");
            mBattery_engine.enableBattery();
            checkEngine();
        }
    }

    @Override
    public void onFanClicked(final View view) {
        if ((Fan.get()).equals("Fan : ON")) {
            Fan.set("Fan : OFF");
            mFan_aircond.disableFan();
            checkAircond();
        }
        else {
            Fan.set("Fan : ON");
            mFan_aircond.enableFan();
            checkAircond();
        }
    }

    @Override
    public void onCompressorClicked(final View view) {
        if ((Compressor.get()).equals("Compressor : ON")) {
            Compressor.set("Compressor : OFF");
            mCompressor_aircond.disableCompressor();
            checkAircond();
        }
        else {
            Compressor.set("Compressor : ON");
            mCompressor_aircond.enableCompressor();
            checkAircond();
        }
    }

    @Override
    public void onSpeakerClicked(final View view) {
        if ((Speaker.get()).equals("Speaker : ON")) {
            Speaker.set("Speaker : OFF");
            mSpeaker_gps.disableSpeaker();
            checkGPS();
        }
        else {
            Speaker.set("Speaker : ON");
            mSpeaker_gps.enableSpeaker();
            checkGPS();
        }
    }

    @Override
    public void onSensorClicked(final View view) {
        if ((Sensor.get()).equals("Sensor : ON")) {
            Sensor.set("Sensor : OFF");
            mSensor_gps.disableSensor();
            checkGPS();
        }
        else {
            Sensor.set("Sensor : ON");
            mSensor_gps.enableSensor();
            checkGPS();
        }
    }


    private void checkEngine() {
        if (Diesel.get().equals("Diesel : ON") && Battery.get().equals("Battery : ON"))
            engineStatus.set("Engine ON");
        else
            engineStatus.set("Engine OFF");
    }

    private void checkAircond() {
        if (Fan.get().equals("Fan : ON") && Compressor.get().equals("Compressor : ON"))
            aircondStatus.set("Aircond ON");
        else
            aircondStatus.set("Aircond OFF");
    }

    private void checkGPS() {
        if (Speaker.get().equals("Speaker : ON") && Sensor.get().equals("Sensor : ON"))
            gpsStatus.set("GPS ON");
        else
            gpsStatus.set("GPS OFF");
    }


    @Override
    public ObservableField<String> getSensor() {
        return Sensor;
    }

    @Override
    public ObservableField<String> getDiesel() {
        return Diesel;
    }

    @Override
    public ObservableField<String> getBattery() {
        return Battery;
    }

    @Override
    public ObservableField<String> getFan() {
        return Fan;
    }

    @Override
    public ObservableField<String> getCompressor() {
        return Compressor;
    }

    @Override
    public ObservableField<String> getSpeaker() {
        return Speaker;
    }

    @Override
    public ObservableField<String> getEngineStatus() {
        return engineStatus;
    }

    @Override
    public ObservableField<String> getAircondStatus() {
        return aircondStatus;
    }

    @Override
    public ObservableField<String> getGpsStatus() {
        return gpsStatus;
    }

}
