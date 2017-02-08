package DependencyInjectionExample;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Fuel {

    private int remainingFuel;

    public Fuel(){
        remainingFuel = 3;
    }

    public int getRemainingFuel(){
        return remainingFuel;
    }

    public void useFuel(){
        remainingFuel -= 1;
    }

    public void addFuel(){
        remainingFuel += 1;
    }

}
