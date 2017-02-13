package com.example.garethlye.vmexercise5;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.garethlye.vmexercise5.databinding.ActivitySeventhBinding;

import ViewModel.SeventhActivityViewModelImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SeventhActivity extends AppCompatActivity {

    @Bind(R.id.seventhSpinner)
    Spinner citySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySeventhBinding seventhBinding = DataBindingUtil.setContentView(this, R.layout.activity_seventh);
        ButterKnife.bind(this);
        SeventhActivityViewModelImpl seventhActivityViewModel = new SeventhActivityViewModelImpl(this, citySpinner);
        seventhBinding.setViewModel(seventhActivityViewModel);

    }
}
