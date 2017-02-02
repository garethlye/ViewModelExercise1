package ViewModel;

import android.database.Observable;
import android.databinding.ObservableField;

/**
 * Created by garethlye on 02/02/2017.
 */

public interface FourthActivityViewModel {

    ObservableField<String> getChosenCity();

    ObservableField<String> getTemp();

    ObservableField<String> getTime();

    ObservableField<String> getDesc();

    ObservableField<String> getSelectedCity();

}
