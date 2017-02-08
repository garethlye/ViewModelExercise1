package DIexample2_modules;

import javax.inject.Singleton;

import DependencyInjectionExample2.Sensor_gps;
import DependencyInjectionExample2.Speaker_gps;
import dagger.Module;
import dagger.Provides;

/**
 * Created by garethlye on 07/02/2017.
 **/

@Module public class GPSModule {

    @Provides
    @Singleton
    Sensor_gps provideSensor(){
        return new Sensor_gps();
    }

    @Provides @Singleton
    Speaker_gps provideSpeaker(){
        return new Speaker_gps();
    }

}
