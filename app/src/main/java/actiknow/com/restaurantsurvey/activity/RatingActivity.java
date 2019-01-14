package actiknow.com.restaurantsurvey.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;

import actiknow.com.restaurantsurvey.R;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

    }
}
