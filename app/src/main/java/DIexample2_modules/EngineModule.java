package DIexample2_modules;

import javax.inject.Singleton;

import DependencyInjectionExample2.Battery_engine;
import DependencyInjectionExample2.Diesel_engine;
import dagger.Module;
import dagger.Provides;

/**
 * Created by garethlye on 07/02/2017.
 */

@Module public class EngineModule {

    @Provides
    @Singleton
    Battery_engine provideBattery(){
        return new Battery_engine();
    }

    @Provides @Singleton
    Diesel_engine provideDiesel(){
        return new Diesel_engine();
    }

}
