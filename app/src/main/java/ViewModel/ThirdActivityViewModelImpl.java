package ViewModel;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.TextView;

import com.example.garethlye.vmexercise5.ThirdActivity;

/**
 * Created by garethlye on 25/01/2017.
 **/

public class ThirdActivityViewModelImpl extends BaseObservable implements ThirdActivityViewModel {

    private final ThirdActivity mThirdActivity;
    private TextView mTextView;

    public ThirdActivityViewModelImpl(ThirdActivity thirdActivity, TextView textView){
        mThirdActivity = thirdActivity;
        mTextView = textView;
    }

    @Override
    public void onToggleClick(View view){
        if(mTextView.getVisibility() == View.VISIBLE)
            mTextView.setVisibility(View.INVISIBLE);
        else
            mTextView.setVisibility(View.VISIBLE);
    }

}
