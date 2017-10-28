package dexter.com.isec17.model;

import java.io.Serializable;

/**
 * Created by dexter on 22/1/17.
 */
public class SpeakerItem implements Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbstract_note() {
        return abstract_note;
    }

    public void setAbstract_note(String abstract_note) {
        this.abstract_note = abstract_note;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    private String name , abstract_note , bio , position , imgLink ;
}
