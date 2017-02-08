package com.example.garethlye.vmexercise5;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.garethlye.vmexercise5.databinding.ActivitySixthBinding;


import javax.inject.Inject;

import DIexample2_modules.AircondModule;
import DIexample2_modules.EngineModule;
import DIexample2_modules.GPSModule;
import DependencyInjectionExample2.Battery_engine;
import DependencyInjectionExample2.CarComponent;
import DependencyInjectionExample2.Compressor_aircond;
import DependencyInjectionExample2.DaggerCarComponent;
import DependencyInjectionExample2.Diesel_engine;
import DependencyInjectionExample2.Fan_aircond;
import DependencyInjectionExample2.Sensor_gps;
import DependencyInjectionExample2.Speaker_gps;
import ViewModel.SixthActivityViewModelImpl;
import butterknife.ButterKnife;

public class SixthActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySixthBinding sixthBinding = DataBindingUtil.setContentView(this, R.layout.activity_sixth);
        ButterKnife.bind(this);

        CarComponent carComponent = DaggerCarComponent.builder().aircondModule(new AircondModule()).engineModule(new EngineModule())
                .gPSModule(new GPSModule()).build();
        carComponent.inject(this);

        SixthActivityViewModelImpl sixthActivityViewModel = new SixthActivityViewModelImpl(this, carComponent);
        sixthBinding.setViewModel(sixthActivityViewModel);

    }
}
