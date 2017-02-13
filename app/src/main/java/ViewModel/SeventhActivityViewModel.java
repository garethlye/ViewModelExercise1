package ViewModel;

import android.databinding.ObservableField;

/**
 * Created by garethlye on 08/02/2017.
 */

public interface SeventhActivityViewModel {

    String getTextValue();

    String getTextValue2();

    ObservableField<String> getTemp();

    ObservableField<String>getDesc();
    ObservableField<String>getTime();
    ObservableField<String>getSelectedCity();
    ObservableField<String>getChosenCity();


}
