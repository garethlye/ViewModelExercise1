package com.example.garethlye.vmexercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ViewModel.FifthActivityViewModelImpl;
import butterknife.ButterKnife;
import android.databinding.DataBindingUtil;

import com.example.garethlye.vmexercise5.databinding.ActivityFifthBinding;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFifthBinding fifthBinding = DataBindingUtil.setContentView(this, R.layout.activity_fifth);
        ButterKnife.bind(this);
        FifthActivityViewModelImpl fifthActivityViewModel = new FifthActivityViewModelImpl(this);
        fifthBinding.setViewModel(fifthActivityViewModel);

    }
}
