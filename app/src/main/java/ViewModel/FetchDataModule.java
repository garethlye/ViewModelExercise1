package ViewModel;

import android.content.Context;

import com.example.garethlye.vmexercise5.SeventhActivity;

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
