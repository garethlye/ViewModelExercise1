package ViewModel;

import com.example.garethlye.vmexercise5.FifthActivity;
import com.example.garethlye.vmexercise5.SeventhActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garethlye on 13/02/2017.
 */

@Singleton
@Component(modules = {
        FetchDataModule.class
})
public interface FetchDataComponent {

    FetchDataViewModel provideFetchDataViewModel();

    void inject(SeventhActivity activity);
}
