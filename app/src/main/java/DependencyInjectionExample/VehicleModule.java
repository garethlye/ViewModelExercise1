package DependencyInjectionExample;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garethlye on 07/02/2017.
 **/

@Module
public class VehicleModule {

    @Provides @Singleton
    Motor provideMotor(){
        return new Motor();
    }

    @Provides @Singleton
    Fuel provideFuel(){
        return new Fuel();
    }

    @Provides @Singleton
    Vehicle provideVehicle(){
    return new Vehicle(new Motor(), new Fuel());
    }

    @Provides
    Test provideTest(){
        return new Test();
    }

}
