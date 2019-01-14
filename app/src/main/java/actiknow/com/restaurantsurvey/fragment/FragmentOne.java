package actiknow.com.restaurantsurvey.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import actiknow.com.restaurantsurvey.R;

public class FragmentOne extends Fragment {
 
 public View onCreateView(LayoutInflater inflater,ViewGroup container,
 Bundle savedInstanceState) {
 
 return inflater.inflate(R.layout.fragment_test_one, container, false);
 
 }
}