package actiknow.com.restaurantsurvey.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import actiknow.com.restaurantsurvey.R;
import actiknow.com.restaurantsurvey.model.Option;
import actiknow.com.restaurantsurvey.model.Question;
import actiknow.com.restaurantsurvey.utils.AppConfigTags;
import actiknow.com.restaurantsurvey.utils.Constants;
import actiknow.com.restaurantsurvey.utils.UserDetailsPref;
import actiknow.com.restaurantsurvey.utils.Utils;

public class QuestionActivity extends AppCompatActivity {

    CoordinatorLayout clMain;
    TextView tvQuestion;
    TextView tvOne;
    TextView tvTwo;
    TextView tvThree;
    TextView tvFour;
    TextView tvFive;

    TextView tvEnglish;
    TextView tvHindi;

    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Option> optionList = new ArrayList<>();
    UserDetailsPref userDetailsPref;
    String response = "";
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        clMain = (CoordinatorLayout)findViewById(R.id.clMain);
        tvQuestion = (TextView)findViewById(R.id.tvQuestion);
        tvOne = (TextView)findViewById(R.id.tvOne);
        tvTwo = (TextView)findViewById(R.id.tvTwo);
        tvThree = (TextView)findViewById(R.id.tvThree);
        tvFour = (TextView)findViewById(R.id.tvFour);
        tvFive = (TextView)findViewById(R.id.tvFive);
        tvEnglish = (TextView)findViewById(R.id.tvEnglish);
        tvHindi = (TextView)findViewById(R.id.tvHindi);
    }

    private void initData() {
        userDetailsPref = UserDetailsPref.getInstance();
        response = userDetailsPref.getStringPref(QuestionActivity.this, UserDetailsPref.RESPONSE);
        try {
            JSONObject jsonObj = new JSONObject(response);
            boolean error = jsonObj.getBoolean(AppConfigTags.ERROR);
            String message = jsonObj.getString(AppConfigTags.MESSAGE);
            if (!error) {
                JSONArray jsonArrayQuestion = jsonObj.getJSONArray(AppConfigTags.QUESTIONS);
                JSONArray jsonArrayOption = jsonObj.getJSONArray(AppConfigTags.OPTIONS);
                for (int i = 0; i < jsonArrayQuestion.length(); i++) {
                    JSONObject jsonObjQuestion = jsonArrayQuestion.getJSONObject(i);
                    Question question = new Question();
                    question.setQues_id(jsonObjQuestion.getInt(AppConfigTags.QUESTION_ID));
                    question.setQues_english(jsonObjQuestion.getString(AppConfigTags.QUESTION_ENGLISH));
                    question.setQues_hindi(new String(jsonObjQuestion.getString(AppConfigTags.QUESTION_HINDI).getBytes("ISO-8859-1"), "utf-8"));
                    questionList.add(question);
                }

                for(int j=0; j < jsonArrayOption.length(); j++){
                    JSONObject jsonObjectOption = jsonArrayOption.getJSONObject(j);
                    optionList.add(new Option(jsonObjectOption.getInt(AppConfigTags.OPTION_ID),
                            jsonObjectOption.getString(AppConfigTags.OPTION_ENGLISH),
                            new String(jsonObjectOption.getString(AppConfigTags.OPTION_HINDI).getBytes("ISO-8859-1"), "utf-8")
                    ));
                }
                questionChange();
            } else {
                Utils.showSnackBar(QuestionActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
            }
        } catch (Exception e) {
            Utils.showSnackBar(QuestionActivity.this, clMain, getResources().getString(R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
            e.printStackTrace();
        }
    }

    private void initListener() {
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionList.size() == index + 1) {
                    sendToRatingActivity();
                }else {
                    index = index + 1;
                    questionChange();
                }

            }
        });

        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionList.size() == index + 1) {
                    sendToRatingActivity();
                }else {
                    index = index + 1;
                    questionChange();
                }
            }
        });

        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionList.size() == index + 1) {
                    sendToRatingActivity();
                }else {
                    index = index + 1;
                    questionChange();
                }
            }
        });

        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionList.size() == index + 1) {
                    sendToRatingActivity();
                }else {
                    index = index + 1;
                    questionChange();
                }
            }
        });

        tvFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionList.size() == index + 1) {
                    sendToRatingActivity();
                }else {
                    index = index + 1;
                    questionChange();
                }
            }
        });

        tvEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetailsPref.putStringPref(QuestionActivity.this, UserDetailsPref.LANGUAGE_TYPE, Constants.lang_english);
                questionChange();
            }
        });

        tvHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetailsPref.putStringPref(QuestionActivity.this, UserDetailsPref.LANGUAGE_TYPE, Constants.lang_hindi);
                questionChange();
            }
        });
    }

    private void sendToRatingActivity() {
        Intent intent = new Intent(this, RatingActivity.class);
        startActivity(intent);
    }

    private void questionChange(){
        switch (userDetailsPref.getStringPref(QuestionActivity.this, UserDetailsPref.LANGUAGE_TYPE)){
            case "english":
                tvOne.setText(optionList.get(0).getOpt_english());
                tvTwo.setText(optionList.get(1).getOpt_english());
                tvThree.setText(optionList.get(2).getOpt_english());
                tvFour.setText(optionList.get(3).getOpt_english());
                tvFive.setText(optionList.get(4).getOpt_english());

                tvQuestion.setText(questionList.get(index).getQues_english());
                break;

            case "hindi":
                tvOne.setText(optionList.get(0).getOpt_hindi());
                tvTwo.setText(optionList.get(1).getOpt_hindi());
                tvThree.setText(optionList.get(2).getOpt_hindi());
                tvFour.setText(optionList.get(3).getOpt_hindi());
                tvFive.setText(optionList.get(4).getOpt_hindi());

                tvQuestion.setText(questionList.get(index).getQues_hindi());
                break;
        }
    }

}
