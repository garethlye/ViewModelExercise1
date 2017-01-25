package ViewModel;

import android.view.View;

/**
 * Created by garethlye on 24/01/2017.
 */

public interface MainActivityViewModel {

    void onButtonClicked(View view);

    void setBtnText(String text);

    String getBtnText();
}
