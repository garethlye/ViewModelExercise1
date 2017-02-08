package DependencyInjectionExample2;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Battery_engine {

    private boolean battery;

    public Battery_engine(){
        battery = true;
    }

    public void disableBattery(){
        battery = false;
    }

    public void enableBattery(){
        battery = true;
    }

}
