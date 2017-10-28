package dexter.com.isec17.model;

/**
 * Created by dexter on 19/1/17.
 */
public class HotelDetail {
    private String name;
    private String address;
    private String link;

    public HotelDetail(){

    }
    public HotelDetail(String name , String address , String contact ,String link , String imageLink , String rating , String price){
        this.name = name ;
        this.address = address ;
        this.contact = contact;
        this.link = link ;
        this.imageLink = imageLink ;
        this.rating = rating ;
        this.price = price ;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private String contact;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String imageLink;
    private String rating;
    private String price;

}
