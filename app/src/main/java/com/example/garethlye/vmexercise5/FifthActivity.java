package com.example.garethlye.vmexercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import DependencyInjectionExample.DaggerVehicleComponent;
import DependencyInjectionExample.VehicleComponent;
import DependencyInjectionExample.VehicleModule;
import DependencyInjectionExample.Vehicle;
import ViewModel.FifthActivityViewModelImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.widget.EditText;

import com.example.garethlye.vmexercise5.databinding.ActivityFifthBinding;

import javax.inject.Inject;

public class FifthActivity extends AppCompatActivity {

    @Bind(R.id.speedValue)
    EditText speedValue;

    @Inject
    Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFifthBinding fifthBinding = DataBindingUtil.setContentView(this, R.layout.activity_fifth);
        ButterKnife.bind(this);

        VehicleComponent vehicleComponent = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        vehicleComponent.inject(this);
        FifthActivityViewModelImpl fifthActivityViewModel = new FifthActivityViewModelImpl(this, vehicleComponent, speedValue);


        //Can also use this vehicle = vehicleComponent.provideVehicle();



        if(vehicle == null){
            Log.w("ERROR ON VEHICLE", "NULL!");
        }
        else
            Log.w("ERROR ON VEHICLE", "NOT NULL!");

        fifthBinding.setViewModel(fifthActivityViewModel);

    }
}
