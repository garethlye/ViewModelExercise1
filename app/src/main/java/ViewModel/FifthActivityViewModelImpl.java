package ViewModel;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.garethlye.vmexercise5.FifthActivity;
import com.example.garethlye.vmexercise5.SixthActivity;

import DependencyInjectionExample.Fuel;
import DependencyInjectionExample.Vehicle;
import DependencyInjectionExample.VehicleComponent;

/**
 * Created by garethlye on 06/02/2017.
 **/

public class FifthActivityViewModelImpl implements FifthActivityViewModel{



    private final FifthActivity mFifthActivity;
    private VehicleComponent mVehicleComponent;
    private EditText mSpeedValue;
    Vehicle vehicle;
    Fuel    fuel;

    public FifthActivityViewModelImpl(final FifthActivity fifthActivity, VehicleComponent vehicleComponent, EditText speedValue) {

        mFifthActivity = fifthActivity;
        mVehicleComponent = vehicleComponent;
        mSpeedValue = speedValue;
        setup();
    }

    private void setup(){

        vehicle = mVehicleComponent.provideVehicle();
        Toast.makeText(mFifthActivity, String.valueOf(vehicle.getSpeed()), Toast.LENGTH_SHORT).show();
        fuel = mVehicleComponent.provideFuel();

    }


    public void increaseBtn(final View view){
        if(TextUtils.isEmpty(mSpeedValue.getText().toString())){
            Toast.makeText(mFifthActivity, String.valueOf("You need to input a digit!"), Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(mSpeedValue.getText().toString()) > 21474836){
            Toast.makeText(mFifthActivity, String.valueOf("You've entered too long a digit"), Toast.LENGTH_SHORT).show();
        }
        else if(fuel.getRemainingFuel()<1){
            Toast.makeText(mFifthActivity, String.valueOf("You've run out of gas! Please refill."), Toast.LENGTH_SHORT).show();
        }
        else if(mSpeedValue.length() > 0){
            vehicle.increaseSpeed(Integer.parseInt(mSpeedValue.getText().toString()));
            fuel.useFuel();
            Toast.makeText(mFifthActivity, String.valueOf(vehicle.getSpeed()+ " | Fuel Remaining : "+fuel.getRemainingFuel()), Toast.LENGTH_SHORT).show();
        }
    }


    public void brakeBtn(final View view){
        vehicle.stop();
        Toast.makeText(mFifthActivity, String.valueOf(vehicle.getSpeed()+ " | Fuel Remaining : "+fuel.getRemainingFuel()), Toast.LENGTH_SHORT).show();
    }

    public void addFuel(final View view){
        fuel.addFuel();
        Toast.makeText(mFifthActivity, String.valueOf("Fuel Remaining: "+fuel.getRemainingFuel()), Toast.LENGTH_SHORT).show();
    }

    public void startSixthActivity(final View view){
        Intent i = new Intent(mFifthActivity, SixthActivity.class);
        mFifthActivity.startActivity(i);
    }

}
