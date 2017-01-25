package com.example.garethlye.vmexercise5;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.garethlye.vmexercise5.databinding.ActivityMainBinding;

import ViewModel.MainActivityViewModelImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_button1)
    Button mButton1;

    @Bind(R.id.main_editText1)
    EditText mEditText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        MainActivityViewModelImpl mainActivityViewModel = new MainActivityViewModelImpl(this, mEditText1, mButton1);
        mainBinding.setViewModel(mainActivityViewModel);

    }
}
