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
import android.widget.TextView;

import com.example.garethlye.vmexercise5.SecondActivity;
import com.example.garethlye.vmexercise5.ThirdActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by garethlye on 24/01/2017.
 **/

public class SecondActivityViewModelImpl extends BaseObservable implements SecondActivityViewModel {

    private final SecondActivity mSecondActivity;
    private       EditText       mEditText;
    private       Button         mButton;
    private       TextView       mTextView;
    private       TextView       mWarningTextView;

    public final ObservableBoolean EnabledStatus = new ObservableBoolean(false);
    public       String            warningText   = "You have inputted a wrong format!\nPlease key in and try again!";


    public SecondActivityViewModelImpl(final SecondActivity secondActivity, EditText editText, Button button, TextView textView, TextView warningTextView) {
        mSecondActivity = secondActivity;
        mEditText = editText;
        mButton = button;
        mTextView = textView;
        mWarningTextView = warningTextView;
        EnabledStatus.set(mEditText.length() >0);
        setup();
    }

    private void setup() {
        mEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            if(mEditText.length() > 0){
            if ((mEditText.getText().toString().length() != 16)) {
                mWarningTextView.setVisibility(View.VISIBLE);
                EnabledStatus.set(false);
            }
            else
                if (checkNumber()){
                    mWarningTextView.setVisibility(View.INVISIBLE);
                    EnabledStatus.set(true);
                }
            }
            else {
                EnabledStatus.set(false);
                mWarningTextView.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            if(mEditText.length() > 0){
                if ((mEditText.getText().toString().length() != 16)){
                    mWarningTextView.setVisibility(View.VISIBLE);
                    EnabledStatus.set(false);
            }
                else
                if (checkNumber()) {
                    mWarningTextView.setVisibility(View.INVISIBLE);
                    EnabledStatus.set(true);
                }
            }
            else {
                EnabledStatus.set(false);
                mWarningTextView.setVisibility(View.INVISIBLE);
            }
            /**mButton.setEnabled((mEditText.length() > 0));

            if ((mEditText.getText().toString().length() != 16))
                mWarningTextView.setVisibility(View.VISIBLE);
            else
                mWarningTextView.setVisibility(View.INVISIBLE);**/

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(mEditText.length() > 0){
                if ((mEditText.getText().toString().length() != 16)){
                    mWarningTextView.setVisibility(View.VISIBLE);
                    EnabledStatus.set(false);
            }
                else
                    if (checkNumber()) {
                        mWarningTextView.setVisibility(View.INVISIBLE);
                        EnabledStatus.set(true);
                    }
            }
            else {
                EnabledStatus.set(false);
                mWarningTextView.setVisibility(View.INVISIBLE);
            }

            /**mButton.setEnabled((mEditText.length() > 0));

            if ((mEditText.getText().toString().length() != 16))
                mWarningTextView.setVisibility(View.VISIBLE);
            else
                mWarningTextView.setVisibility(View.INVISIBLE);**/
        }
    };

    private boolean checkNumber(){
        String input = mEditText.getText().toString();
        String regex = "^[0-9]+$";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if(matcher.find()){
            return true;
        }
        else
            return false;
    }

    @Override
    public void nextActivity(final View view){
        startThirdActivity();
    }

    private void startThirdActivity(){
        String text = mEditText.getText().toString();
        Intent i = new Intent(mSecondActivity, ThirdActivity.class);
        i.putExtra("editTextValue", text);
        mSecondActivity.startActivity(i);
    }

    private void whatCard(){
        String input = mEditText.getText().toString();

    }


    /**
     input = ed.getText().toString();
     String regex ="^[0-9]+$";
     Matcher matcher = Pattern.compile( regex ).matcher(input);
     if (matcher.find( ))
     {
     result = matcher.group();
     Toast.makeText(MainActivity.this, "Matches",1000 ).show();
     System.out.println("number="+result);
     }
     else
     {
     Toast.makeText(MainActivity.this, " No Matches",1000 ).show();
     System.out.println("no match found");
     }
     }
     */


    @Override
    public String getWarningText(){
        return warningText;
    }

    @Bindable
    public ObservableBoolean getEnabledStatus(){
        return EnabledStatus;
    }


}
