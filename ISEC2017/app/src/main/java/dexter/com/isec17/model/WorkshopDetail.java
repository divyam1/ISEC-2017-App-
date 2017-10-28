package dexter.com.isec17.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by dexter on 20/1/17.
 */
public class WorkshopDetail implements Serializable {
    private String title;
    private String presenters;
    private String affiliations;
    private String startTime;
    private String endTime;
    private String date;
    private String area ;

    public WorkshopDetail(){

    }

    @Override
    public String toString() {
        return title + " " + presenters ;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPresenters() {
        return presenters;
    }

    public void setPresenters(String presenters) {
        this.presenters = presenters;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
