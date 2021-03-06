package ViewModel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by garethlye on 24/01/2017.
 */

public interface SecondActivityViewModel {

    String getWarningText();

    ObservableBoolean getEnabledStatus();

    void nextActivity(View view);

    ObservableField<String> getCardType();

    void setCardType(ObservableField<String> card);

}
