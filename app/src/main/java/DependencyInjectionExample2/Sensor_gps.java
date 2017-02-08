package DependencyInjectionExample2;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Sensor_gps {

    private boolean sensor;

    public Sensor_gps(){
        sensor = true;
    }

    public void disableSensor(){
        sensor = false;
    }

    public void enableSensor(){
        sensor = true;
    }

}
