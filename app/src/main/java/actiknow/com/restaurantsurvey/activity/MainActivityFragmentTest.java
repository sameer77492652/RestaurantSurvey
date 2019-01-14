
package actiknow.com.restaurantsurvey.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import actiknow.com.restaurantsurvey.R;
import actiknow.com.restaurantsurvey.fragment.FragmentOne;
import actiknow.com.restaurantsurvey.fragment.FragmentTwo;

public class MainActivityFragmentTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test);
    }

    public void FragmentOneClick(View view) {
        Fragment myfragment;
        myfragment = new FragmentOne();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, myfragment);
        fragmentTransaction.commit();

    }

    public void FragmentTwoClick(View view) {
        Fragment myfragment;
        myfragment = new FragmentTwo();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, myfragment);
        fragmentTransaction.commit();

    }
}