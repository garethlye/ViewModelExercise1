package ViewModel;

import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by garethlye on 07/02/2017.
 */

public interface SixthActivityViewModel {

    ObservableField<String> getDiesel();
    ObservableField<String> getBattery();
    ObservableField<String> getFan();
    ObservableField<String> getCompressor();
    ObservableField<String> getSpeaker();
    ObservableField<String> getSensor();

    ObservableField<String> getEngineStatus();
    ObservableField<String> getAircondStatus();
    ObservableField<String> getGpsStatus();

    void onDieselClicked(View view);
    void onBatteryClicked(View view);
    void onFanClicked(View view);
    void onCompressorClicked(View view);
    void onSpeakerClicked(View view);
    void onSensorClicked(View view);
}
