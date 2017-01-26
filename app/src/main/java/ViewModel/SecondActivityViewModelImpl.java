package ViewModel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.garethlye.vmexercise5.R;
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

    public final ObservableBoolean       EnabledStatus = new ObservableBoolean(false);
    public       String                  warningText   = "You have inputted a wrong format!\nPlease key in and try again!";
    private      ObservableField<String> cardType      = new ObservableField();
    private boolean validCard = false;


    public SecondActivityViewModelImpl(final SecondActivity secondActivity, EditText editText, Button button, TextView textView, TextView warningTextView) {
        mSecondActivity = secondActivity;
        mEditText = editText;
        mButton = button;
        mTextView = textView;
        mWarningTextView = warningTextView;
        EnabledStatus.set(mEditText.length() > 0);
        setup();

    }

    private void setup() {
        mEditText.addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            if (mEditText.length() > 0) {
                if ((mEditText.getText().toString().length() != 16)) {
                    mWarningTextView.setVisibility(View.VISIBLE);
                    EnabledStatus.set(false);
                }
                else if (checkNumber() && validCard) {
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
            int length = mEditText.length();
            whatCard(length, EnabledStatus);
            if (length > 0) {

                if ((mEditText.getText().toString().length() != 16)) {
                    mWarningTextView.setVisibility(View.VISIBLE);
                    EnabledStatus.set(false);
                }
                else if (checkNumber() && validCard) {
                    mWarningTextView.setVisibility(View.INVISIBLE);
                    EnabledStatus.set(true);
                }
            }
            else {
                EnabledStatus.set(false);
                mWarningTextView.setVisibility(View.INVISIBLE);
                cardType.set("");

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (mEditText.length() > 0) {
                if ((mEditText.getText().toString().length() != 16)) {
                    mWarningTextView.setVisibility(View.VISIBLE);
                    EnabledStatus.set(false);
                }
                else if (checkNumber() && validCard) {
                    mWarningTextView.setVisibility(View.INVISIBLE);
                    EnabledStatus.set(true);
                }
            }
            else {
                EnabledStatus.set(false);
                mWarningTextView.setVisibility(View.INVISIBLE);
            }
        }
    };

    private boolean checkNumber() {
        String input = mEditText.getText().toString();
        String regex = "^[0-9]+$";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.find()) {
            return true;
        }
        else
            return false;
    }

    @Override
    public void nextActivity(final View view) {
        startThirdActivity();
    }

    private void startThirdActivity() {
        String text = mEditText.getText().toString();
        Intent i = new Intent(mSecondActivity, ThirdActivity.class);
        i.putExtra("editTextValue", text);
        mSecondActivity.startActivity(i);
    }

    private void whatCard(int length, ObservableBoolean enabledStatus) {
        String temp = mEditText.getText().toString();
        if (length > 0) {
            String temp2 = temp.substring(0, 1);

            switch (temp2) {
                case "4":
                    if (length < 16 && length > 0)
                        setMessage(mSecondActivity.getString(R.string.Card_ID_text_1), mSecondActivity.getString(R.string.VISA));
                    else if (length == 16 && !enabledStatus.get()){
                        setMessage(mSecondActivity.getString(R.string.Card_ID_text_2), mSecondActivity.getString(R.string.VISA));
                        validCard = true;
                    }
                    else if (length > 16)
                        setMessage(mSecondActivity.getString(R.string.tooManyNumbers), "");
                    else{
                        setMessage("", "");
                        validCard = false;
                    }
                    break;
                case "5":
                    if (length == 16 && !enabledStatus.get() && (temp.substring(1, 2).equals("1") || temp.substring(1, 2).equals("2") || temp.substring(1, 2).equals("3") || temp.substring(1, 2).equals("4") || temp.substring(1, 2).equals("5"))) {
                        setMessage(mSecondActivity.getString(R.string.Card_ID_text_2), mSecondActivity.getString(R.string.MasterCard));
                        validCard = true;
                    }
                    else if (length < 16 && length > 1) {
                        if (temp.substring(1, 2).equals("1") || temp.substring(1, 2).equals("2") || temp.substring(1, 2).equals("3") || temp.substring(1, 2).equals("4") || temp.substring(1, 2).equals("5"))
                            setMessage(mSecondActivity.getString(R.string.Card_ID_text_1), mSecondActivity.getString(R.string.MasterCard));
                        else if (length > 1 && !(temp.substring(1, 2).equals("1") || temp.substring(1, 2).equals("2") || temp.substring(1, 2).equals("3") || temp.substring(1, 2).equals("4") || temp.substring(1, 2).equals("5")))
                            setMessage(mSecondActivity.getString(R.string.UnknownCard), "");
                    }
                    else if (length > 16)
                        setMessage(mSecondActivity.getString(R.string.tooManyNumbers), "");
                    else
                        setMessage("", "");
                    break;
                case "3":
                    if (length < 16 && length >1) {
                        if(temp.substring(1,2).equals("4")||temp.substring(1,2).equals("7"))
                        setMessage(mSecondActivity.getString(R.string.Card_ID_text_1), mSecondActivity.getString(R.string.American_Express));
                        else if (length > 1 && (!temp.substring(1, 2).equals("4") || !temp.substring(1, 2).equals("7")))
                            setMessage(mSecondActivity.getString(R.string.UnknownCard), "");
                    }
                    else if (length == 16 && !enabledStatus.get() && (temp.substring(1, 2).equals("4") || temp.substring(1, 2).equals("7"))){
                        setMessage(mSecondActivity.getString(R.string.Card_ID_text_2), mSecondActivity.getString(R.string.American_Express));
                        validCard = true;
                    }
                    else if (length > 16)
                        setMessage(mSecondActivity.getString(R.string.tooManyNumbers), "");
                    else
                        setMessage("", "");
                    break;
                default:
                    cardType.set(mSecondActivity.getString(R.string.UnknownCard));
            }

        }
    }

    private void setMessage(String one, String two) {
        cardType.set(one + " " + two);
    }

    @Override
    public String getWarningText() {
        return warningText;
    }

    @Bindable
    public ObservableBoolean getEnabledStatus() {
        return EnabledStatus;
    }


    @Bindable
    public ObservableField<String> getCardType() {
        return cardType;
    }

    public void setCardType(ObservableField<String> card) {
        cardType = card;
    }
}
