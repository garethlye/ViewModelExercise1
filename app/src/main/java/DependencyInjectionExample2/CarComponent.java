package DependencyInjectionExample2;

import com.example.garethlye.vmexercise5.SixthActivity;

import javax.inject.Singleton;

import DIexample2_modules.AircondModule;
import DIexample2_modules.EngineModule;
import DIexample2_modules.GPSModule;
import ViewModel.SixthActivityViewModelImpl;
import dagger.Component;

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

    void inject(SixthActivityViewModelImpl sixthActivityViewModelImpl);
}
