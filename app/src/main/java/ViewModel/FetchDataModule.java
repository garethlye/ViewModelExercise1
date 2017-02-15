package ViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garethlye on 13/02/2017.
 */

@Module
public class FetchDataModule {

    @Provides
    @Singleton
    FetchDataViewModel provideFetchDataViewModel(){
        return new FetchDataViewModel();
    }

}
