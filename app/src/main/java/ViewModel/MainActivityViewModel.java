package ViewModel;

import android.databinding.ObservableBoolean;
import android.view.View;

/**
 * Created by garethlye on 24/01/2017.
 */

public interface MainActivityViewModel {

    void onButtonClicked(View view);

    ObservableBoolean getEnabledStatus();

    void startSeventhActivity(View view);
}
