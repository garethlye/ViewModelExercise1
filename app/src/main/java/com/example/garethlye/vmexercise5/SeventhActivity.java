package com.example.garethlye.vmexercise5;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import ViewModel.DaggerFetchDataComponent;
import ViewModel.FetchDataModule;
import ViewModel.FetchDataComponent;

import com.example.garethlye.vmexercise5.databinding.ActivitySeventhBinding;

import javax.inject.Inject;

import ViewModel.FetchDataViewModel;
import ViewModel.SeventhActivityViewModelImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SeventhActivity extends AppCompatActivity {

    @Bind(R.id.seventhSpinner)
    Spinner citySpinner;

    @Inject
    FetchDataViewModel mFetchDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySeventhBinding seventhBinding = DataBindingUtil.setContentView(this, R.layout.activity_seventh);
        ButterKnife.bind(this);

        FetchDataComponent fetchDataComponent = DaggerFetchDataComponent.builder().fetchDataModule(new FetchDataModule()).build();
        fetchDataComponent.inject(this);

        SeventhActivityViewModelImpl seventhActivityViewModel = new SeventhActivityViewModelImpl(this, citySpinner, mFetchDataViewModel);
        seventhBinding.setViewModel(seventhActivityViewModel);

        if(mFetchDataViewModel == null){
            Log.w("ERROR ON FetchData", "FD is NULL!");
        }
        else
            Log.w("ERROR ON FetchData", "FD is NOT NULL!");

    }
}
