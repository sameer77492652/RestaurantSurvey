package actiknow.com.restaurantsurvey.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import actiknow.com.restaurantsurvey.R;
import actiknow.com.restaurantsurvey.model.Response;
import actiknow.com.restaurantsurvey.utils.AppConfigTags;
import actiknow.com.restaurantsurvey.utils.AppConfigURL;
import actiknow.com.restaurantsurvey.utils.Constants;
import actiknow.com.restaurantsurvey.utils.NetworkConnection;
import actiknow.com.restaurantsurvey.utils.Utils;

public class RatingFragment extends Fragment {

    RatingBar rbRating;
    EditText etComment;
    TextView tvSubmitSurvey;
    ProgressDialog progressDialog;
    ArrayList<Response>responseList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rating, container, false);
        responseList = getArguments().getParcelableArrayList("response_list");
        Log.e("response",""+ responseList.get(0).getResponse() + "-" + responseList.get(1));
        initView(v);
        initData();
        initListener();
        return v;
    }


    private void initListener() {
        tvSubmitSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initData() {
        progressDialog = new ProgressDialog(getActivity());
    }

    private void initView(View v) {
        rbRating = (RatingBar)v.findViewById(R.id.rbRating);
        etComment = (EditText)v.findViewById(R.id.etComment);
        tvSubmitSurvey = (TextView)v.findViewById(R.id.tvSubmitSurvey);
    }

    private void sendResponseToServer () {
        if (NetworkConnection.isNetworkAvailable(getActivity())) {
            Utils.showProgressDialog(progressDialog, getResources().getString(R.string.progress_dialog_text_please_wait), true);
            Utils.showLog(Log.INFO, "" + AppConfigTags.URL, AppConfigURL.URL_INIT, true);
            StringRequest strRequest1 = new StringRequest(Request.Method.GET, AppConfigURL.URL_INIT,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Utils.showLog(Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);

                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    boolean error = jsonObj.getBoolean(AppConfigTags.ERROR);
                                    String message = jsonObj.getString(AppConfigTags.MESSAGE);
                                    if (!error) {


                                    } else {
                                        Utils.showToast(getActivity(), message, true);
                                        //Utils.showSnackBar(MainActivityNew.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                    progressDialog.dismiss();
                                } catch (Exception e) {
                                    progressDialog.dismiss();
                                    Utils.showToast(getActivity(), "API ERROR", true);
                                    //Utils.showSnackBar(MainActivityNew.this, clMain, getResources().getString(R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace();
                                }
                            } else {
                                //Utils.showSnackBar(MainActivityNew.this, clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                                Utils.showToast(getActivity(), "API ERROR", true);
                                Utils.showLog(Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            progressDialog.dismiss();
                            //swipeRefreshLayout.setRefreshing (false);
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // swipeRefreshLayout.setRefreshing (false);
                            progressDialog.dismiss();
                            Utils.showLog(Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString(), true);
                            Utils.showToast(getActivity(), "API ERROR", true);
                            //Utils.showSnackBar(MainActivityNew.this, clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                        }
                    }) {


                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String>();

                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put(AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    params.put(AppConfigTags.HEADER_USER_LOGIN_KEY, "c5ebcee3af05a5ae1b6a09c668ba798c");
                    Utils.showLog(Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            Utils.sendRequest(strRequest1, 60);
        } else {
            /*Utils.showSnackBar(this, clMain, getResources().getString(R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_go_to_settings), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dialogIntent = new Intent(Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
            });*/
            Utils.showToast(getActivity(), "API ERROR", true);
        }
    }


}