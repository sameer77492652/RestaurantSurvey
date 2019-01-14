package actiknow.com.restaurantsurvey.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable{
    int question_id, response;

    public Response(int question_id, int response) {
        this.question_id = question_id;
        this.response = response;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(question_id);
        parcel.writeInt(response);
    }
}
