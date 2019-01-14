package actiknow.com.restaurantsurvey.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import actiknow.com.restaurantsurvey.R;
import actiknow.com.restaurantsurvey.model.Option;
import actiknow.com.restaurantsurvey.model.Question;
import actiknow.com.restaurantsurvey.utils.AppConfigTags;
import actiknow.com.restaurantsurvey.utils.UserDetailsPref;
import actiknow.com.restaurantsurvey.utils.Utils;

public class QuestionFragment extends Fragment {
    TextView tvQuestion;
    TextView tvOne;
    TextView tvTwo;
    TextView tvThree;
    TextView tvFour;
    TextView tvFive;

    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Option> optionList = new ArrayList<>();
    UserDetailsPref userDetailsPref;
    String response = "";
    int index = 0;

    boolean clicked = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question, container,false);
        initView(v);
        initData();
        initListener();
        return v;

    }

    private void initView(View v) {
        tvQuestion = (TextView)v.findViewById(R.id.tvQuestion);
        tvOne = (TextView)v.findViewById(R.id.tvOne);
        tvTwo = (TextView)v.findViewById(R.id.tvTwo);
        tvThree = (TextView)v.findViewById(R.id.tvThree);
        tvFour = (TextView)v.findViewById(R.id.tvFour);
        tvFive = (TextView)v.findViewById(R.id.tvFive);
    }

    private void initData() {
        userDetailsPref = UserDetailsPref.getInstance();
        response = userDetailsPref.getStringPref(getActivity(), UserDetailsPref.RESPONSE);
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
                Utils.showToast(getActivity(), message, true);
            }
        } catch (Exception e) {
            Utils.showToast(getActivity(), "api error", true);
            e.printStackTrace();
        }
    }

    private void initListener() {
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked) {
                    clicked = true;
                    if (questionList.size() == index + 1) {
                        sendToRatingActivity();
                    } else {
                        index = index + 1;
                        questionChange();
                    }
                }
            }
        });

        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked) {
                    clicked = true;
                    if (questionList.size() == index + 1) {
                        sendToRatingActivity();
                    } else {
                        index = index + 1;
                        questionChange();
                    }
                }
            }
        });

        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked) {
                    clicked = true;
                    if (questionList.size() == index + 1) {
                        sendToRatingActivity();
                    } else {
                        index = index + 1;
                        questionChange();
                    }
                }
            }
        });

        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked) {
                    clicked = true;
                    if (questionList.size() == index + 1) {
                        sendToRatingActivity();
                    } else {
                        index = index + 1;
                        questionChange();
                    }
                }
            }
        });

        tvFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!clicked) {
                    clicked = true;
                    if (questionList.size() == index + 1) {
                        sendToRatingActivity();
                    } else {
                        index = index + 1;
                        questionChange();
                    }
                }
            }
        });

    }

    private void sendToRatingActivity() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_switch, new RatingFragment());
        fragmentTransaction.commit();
    }

    private void questionChange(){
        clicked = false;
        switch (userDetailsPref.getStringPref(getActivity(), UserDetailsPref.LANGUAGE_TYPE)){
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
