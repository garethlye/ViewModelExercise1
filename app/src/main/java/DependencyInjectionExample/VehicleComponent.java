package DependencyInjectionExample;

import com.example.garethlye.vmexercise5.FifthActivity;

import javax.inject.Singleton;

import ViewModel.SixthActivityViewModelImpl;
import dagger.Component;

@Singleton
@Component(modules = {
        VehicleModule.class
})

public interface VehicleComponent {

    Vehicle provideVehicle();

    Fuel provideFuel();

    void inject(FifthActivity activity);

    void inject(SixthActivityViewModelImpl sixthActivityViewModelImpl);

}
