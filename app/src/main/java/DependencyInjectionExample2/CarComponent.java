package DependencyInjectionExample2;

import com.example.garethlye.vmexercise5.SixthActivity;

import javax.inject.Singleton;

import DIexample2_modules.AircondModule;
import DIexample2_modules.EngineModule;
import DIexample2_modules.GPSModule;
import dagger.Component;

/**
 * Created by garethlye on 07/02/2017.
* */

@Singleton
@Component(modules = {
        AircondModule.class,
        EngineModule.class,
        GPSModule.class
})

public interface CarComponent {

    Compressor_aircond provideCompressor();

    Fan_aircond provideFan();

    Battery_engine provideBattery();

    Diesel_engine provideDiesel();

    Sensor_gps provideSensor();

    Speaker_gps provideSpeaker();

    void inject(SixthActivity activity);

}
