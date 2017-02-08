package DIexample2_modules;

import javax.inject.Singleton;

import DependencyInjectionExample2.Compressor_aircond;
import DependencyInjectionExample2.Fan_aircond;
import dagger.Module;
import dagger.Provides;

/**
 * Created by garethlye on 07/02/2017.
 */

@Module public class AircondModule {

    @Provides
    @Singleton
    Compressor_aircond provideCompressor(){
        return new Compressor_aircond();
    }

    @Provides @Singleton
    Fan_aircond provideFan(){
        return new Fan_aircond();
    }

}
