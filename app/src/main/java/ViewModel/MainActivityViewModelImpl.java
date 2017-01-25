package ViewModel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.garethlye.vmexercise5.MainActivity;
import com.example.garethlye.vmexercise5.SecondActivity;

/**
 * Created by garethlye on 24/01/2017.
 **/

public class MainActivityViewModelImpl extends BaseObservable implements MainActivityViewModel {

    private final MainActivity mMainActivity;
    private       EditText     mEditText;
    public       String            BtnText       = "Send";
    public final ObservableBoolean EnabledStatus = new ObservableBoolean(false);


    public MainActivityViewModelImpl(final MainActivity mainActivity, EditText mainEditText, Button mainButton) {
        mMainActivity = mainActivity;
        mEditText = mainEditText;

        setup();
    }

    private void setup(){
        mEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EnabledStatus.set(mEditText.length() >0);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EnabledStatus.set(mEditText.length() >0);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            EnabledStatus.set(mEditText.length() >0);
        }
    };

    @Override
    public void onButtonClicked(final View view){
        startSecondActivity();
    }

    private void startSecondActivity(){
        String text = mEditText.getText().toString();
        Intent i = new Intent(mMainActivity, SecondActivity.class);
        i.putExtra("editTextValue", text);
        mMainActivity.startActivity(i);
    }

    @Bindable
    public String getBtnText(){
        return BtnText;
    }

    @Bindable
    public ObservableBoolean getEnabledStatus(){
        return EnabledStatus;
    }

}
