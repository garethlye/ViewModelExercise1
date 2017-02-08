package DependencyInjectionExample;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Vehicle {
    private Motor motor;
    private Fuel fuel;

    public Vehicle(Motor motor, Fuel fuel) {
        this.motor = motor;
        this.fuel = fuel;
    }

    public void increaseSpeed(int value){
        motor.accelerate(value);
    }

    public void stop(){
        motor.brake();
    }

    public String getSpeed(){
        return "Current Speed = " + motor.getRpm();
    }
}
