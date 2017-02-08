package DependencyInjectionExample;

import com.example.garethlye.vmexercise5.FifthActivity;

import javax.inject.Singleton;
import dagger.Component;

/***
 * Created by garethlye on 07/02/2017.
 */

@Singleton
@Component(modules = {
        VehicleModule.class
})

public interface VehicleComponent {

    Vehicle provideVehicle();

    Fuel provideFuel();

    void inject(FifthActivity activity);

}
