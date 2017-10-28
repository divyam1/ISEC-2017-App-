package dexter.com.isec17.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import dexter.com.isec17.R;
import dexter.com.isec17.adapter.OrganisationAdapter;
import dexter.com.isec17.model.OrganisationDetail;

public class OrganisationActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    OrganisationAdapter mAdapter;
    String postName[];
    ArrayList<OrganisationDetail> organisationDetailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);
        postName =getResources().getStringArray(R.array.organisation_post);
        mRecyclerView = (RecyclerView) findViewById(R.id.organization_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new OrganisationAdapter(getDetails());
        mRecyclerView.setAdapter(mAdapter);

        int len =postName.length;
        Log.e("length : ", String.valueOf(len));
    }
    @Override
    protected void onResume() {
        super.onResume();
        ((OrganisationAdapter) mAdapter).setOnItemClickListener(new OrganisationAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
               // Log.i(LOG_TAG, " Clicked on Item " + position);
                Intent intent = new Intent(getApplicationContext(), OrganisationDetailActivity.class);
                intent.putExtra("OrgDATA", organisationDetailArrayList.get(position));

                startActivity(intent);
            }
        });
    }

    private ArrayList<OrganisationDetail> getDetails() {
        organisationDetailArrayList = new ArrayList<OrganisationDetail>();
        for (int index = 0; index < 12; index++) {
            OrganisationDetail obj = new OrganisationDetail();
            //Log.e("post name",postName[index].toString());
            obj.setItemName(postName[index].toString());
            organisationDetailArrayList.add(index,obj);
        }
        return organisationDetailArrayList;
    }
}
