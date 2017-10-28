package dexter.com.isec17.model;

/**
 * Created by DU on 1/29/2017.
 */

public class OrganisationPostDetail
{
    String memberName;
    String memberDesignation;

    public OrganisationPostDetail() {
    }

    public OrganisationPostDetail(String memberName,String memberDesignation){
        this.memberName =memberName;
        this.memberDesignation = memberDesignation;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberDesignation() {
        return memberDesignation;
    }

    public void setMemberDesignation(String memberDesignation) {
        this.memberDesignation = memberDesignation;
    }
}