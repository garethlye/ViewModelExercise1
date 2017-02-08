package DependencyInjectionExample2;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Fan_aircond {

    private boolean fan;

    public Fan_aircond(){
        fan = true;
    }

    public void disableFan(){
        fan = false;
    }

    public void enableFan(){
        fan = true;
    }

}
