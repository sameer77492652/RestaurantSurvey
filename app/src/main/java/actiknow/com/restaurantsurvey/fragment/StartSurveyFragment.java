package actiknow.com.restaurantsurvey.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import actiknow.com.restaurantsurvey.R;
import actiknow.com.restaurantsurvey.utils.Constants;
import actiknow.com.restaurantsurvey.utils.UserDetailsPref;

public class StartSurveyFragment extends Fragment {

    Button btStartSurvey;
    UserDetailsPref userDetailsPref;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start_survey, container,false);
        initView(v);
        initData();
        initListener();
        return v;



    }

    private void initData() {
        userDetailsPref = UserDetailsPref.getInstance();
        if(userDetailsPref.getStringPref(getActivity(), UserDetailsPref.LANGUAGE_TYPE).length() == 0){
            userDetailsPref.putStringPref(getActivity(), UserDetailsPref.LANGUAGE_TYPE, Constants.lang_english);
        }
    }

    private void initListener() {
        btStartSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment myfragment;
                myfragment = new QuestionFragment();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_switch, myfragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void initView(View v) {
        btStartSurvey = (Button)v.findViewById(R.id.btStartSurvey);
    }
}
