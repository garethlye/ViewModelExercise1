package com.example.garethlye.vmexercise5;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.garethlye.vmexercise5.databinding.ActivityThirdBinding;

import ViewModel.ThirdActivityViewModelImpl;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ThirdActivity extends AppCompatActivity {

    @Bind(R.id.third_textView)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityThirdBinding thirdBinding = DataBindingUtil.setContentView(this, R.layout.activity_third);
        ButterKnife.bind(this);
        ThirdActivityViewModelImpl thirdActivityViewModel = new ThirdActivityViewModelImpl(this, mTextView);
        thirdBinding.setViewModel(thirdActivityViewModel);

    }
}
