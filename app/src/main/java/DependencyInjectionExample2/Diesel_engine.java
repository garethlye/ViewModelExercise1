package DependencyInjectionExample2;

/**
 * Created by garethlye on 07/02/2017.
 */

public class Diesel_engine {

    private boolean diesel;

    public Diesel_engine(){
        diesel = true;
    }

    public void disableDiesel(){
        diesel = false;
    }

    public void enableDiesel(){
        diesel = true;
    }

}
