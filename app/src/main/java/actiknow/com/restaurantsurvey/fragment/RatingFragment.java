package actiknow.com.restaurantsurvey.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import actiknow.com.restaurantsurvey.R;

public class RatingFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        /*initView(v);
        initData();
        initListener();*/
        return v;
    }



}