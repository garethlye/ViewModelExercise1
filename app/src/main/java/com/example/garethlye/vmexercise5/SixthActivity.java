package com.example.garethlye.vmexercise5;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.garethlye.vmexercise5.databinding.ActivitySixthBinding;

import ViewModel.SixthActivityViewModelImpl;
import butterknife.ButterKnife;

public class SixthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySixthBinding sixthBinding = DataBindingUtil.setContentView(this, R.layout.activity_sixth);
        ButterKnife.bind(this);
        SixthActivityViewModelImpl sixthActivityViewModel = new SixthActivityViewModelImpl(this);
        sixthBinding.setViewModel(sixthActivityViewModel);

    }
}
