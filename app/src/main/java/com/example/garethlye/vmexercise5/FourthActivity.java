package com.example.garethlye.vmexercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ViewModel.FourthActivityViewModelImpl;
import android.databinding.DataBindingUtil;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.garethlye.vmexercise5.databinding.ActivityFourthBinding;

public class FourthActivity extends AppCompatActivity {
    @Bind(R.id.city_spinner)
    Spinner citySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFourthBinding fourthBinding = DataBindingUtil.setContentView(this, R.layout.activity_fourth);
        ButterKnife.bind(this);
        FourthActivityViewModelImpl fourthActivityViewModel = new FourthActivityViewModelImpl(this, citySpinner);
        fourthBinding.setViewModel(fourthActivityViewModel);
    }
}
