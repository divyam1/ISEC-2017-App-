package dexter.com.isec17.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import dexter.com.isec17.R;
import dexter.com.isec17.adapter.OrganisationAdapter;
import dexter.com.isec17.adapter.OrganisationDetailAdapter;
import dexter.com.isec17.model.OrganisationDetail;
import dexter.com.isec17.model.OrganisationPostDetail;

public class OrganisationDetailActivity extends AppCompatActivity {

    TextView tvmemberName,tvmemberDesignation,postName;
    RecyclerView mdetailRecyclerView;
    OrganisationDetailAdapter mdetailAdapter;
    ArrayList<OrganisationPostDetail> organisationPostDetailArrayList;
    LinearLayoutManager mLayoutManager;
    String memberName[],memberDesignation[];
    OrganisationDetail organisationDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_detail);
        postName = (TextView) findViewById(R.id.post_name);
        tvmemberName = (TextView) findViewById(R.id.member_name);
        tvmemberDesignation = (TextView) findViewById(R.id.member_designation);
         organisationDetail= (OrganisationDetail) getIntent().getSerializableExtra("OrgDATA");

        memberName = getResources().getStringArray(R.array.organisation_memberName);
        memberDesignation = getResources().getStringArray(R.array.organisation_memberDesignation);

        postName.setText(organisationDetail.getItemName());
        mdetailRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mdetailRecyclerView.setLayoutManager(mLayoutManager);
        mdetailAdapter = new OrganisationDetailAdapter(getPostDetails());
        mdetailRecyclerView.setAdapter(mdetailAdapter);
    }
    private ArrayList<OrganisationPostDetail> getPostDetails() {
        organisationPostDetailArrayList = new ArrayList<OrganisationPostDetail>(24);
        if(organisationDetail.getItemName().equals("General Chair")==true)
        {
            for (int index = 0; index < 1; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Program Chair")==true)
        {
            for (int index = 1; index < 3; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Industry Track Program Chair")==true)
        {
            for (int index = 3; index < 5; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Mobile SE Track Program Chair")==true)
        {
            for (int index = 5; index < 7; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Proceedings Track Chair")==true)
        {
            for (int index = 7; index < 9; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Student Travel Grant Chair")==true)
        {
            for (int index = 9; index < 10; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Workshop Chair")==true)
        {
            for (int index = 10; index < 12; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Tutorial Chair")==true)
        {
            for (int index = 12; index < 14; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Finance Chair")==true)
        {
            for (int index = 14; index < 15; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Publicity Chair")==true)
        {
            for (int index = 15; index < 19; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else  if(organisationDetail.getItemName().equals("Local Organizing Chair")==true)
        {
            for (int index = 19; index < 22; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        else if(organisationDetail.getItemName().equals("Publicity Members")==true)
        {
            for (int index = 22; index < 24; index++) {
                OrganisationPostDetail obj = new OrganisationPostDetail();
                //Log.e("post name",postName[index].toString());

                obj.setMemberName(memberName[index].toString());
                obj.setMemberDesignation(memberDesignation[index].toString());

                organisationPostDetailArrayList.add(obj);
            }
        }
        return organisationPostDetailArrayList;
    }
}
