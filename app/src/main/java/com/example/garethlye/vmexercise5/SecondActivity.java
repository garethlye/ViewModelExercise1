package com.example.garethlye.vmexercise5;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.garethlye.vmexercise5.databinding.ActivitySecondBinding;

import ViewModel.SecondActivityViewModel;
import ViewModel.SecondActivityViewModelImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @Bind(R.id.card_type)
    TextView mTextView;

    @Bind(R.id.second_warningTextView)
    TextView mWarningTextView;

    @Bind(R.id.second_button)
    Button mButton;

    @Bind(R.id.second_editText)
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySecondBinding secondBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        ButterKnife.bind(this);
        SecondActivityViewModelImpl secondActivityViewModel = new SecondActivityViewModelImpl(this, mEditText, mButton, mTextView, mWarningTextView);
        secondBinding.setViewModel(secondActivityViewModel);
    }
}
