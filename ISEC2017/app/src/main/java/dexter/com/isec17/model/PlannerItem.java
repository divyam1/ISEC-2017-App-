package dexter.com.isec17.model;

/**
 * Created by dexter on 21/1/17.
 */
public class PlannerItem {
    private int id ;
    private String title ;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    private String subtitle ;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String endTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date ;
    public PlannerItem(){

    }
    public PlannerItem(int id , String title , String type , String place , String date , String time){
        this.id = id ;
        this.title = title;
        this.type = type ;
        this.place = place ;
        this.date = date ;
        this.startTime = time ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    private String type ;
    private String startTime ;
    private String place ;

    @Override
    public String toString() {
        return id + " " + title + " " + startTime ;
    }
}
