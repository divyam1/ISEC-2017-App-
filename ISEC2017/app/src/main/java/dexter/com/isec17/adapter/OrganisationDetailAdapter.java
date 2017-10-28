package dexter.com.isec17.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.model.OrganisationDetail;
import dexter.com.isec17.model.OrganisationPostDetail;

/**
 * Created by DU on 1/29/2017.
 */

public class OrganisationDetailAdapter extends RecyclerView.Adapter<OrganisationDetailAdapter.OrganisationPostHolder> {

    private ArrayList<OrganisationPostDetail> organisationPostDetailList;


    public class OrganisationPostHolder extends RecyclerView.ViewHolder {
        public TextView name, designation;

        public OrganisationPostHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.member_name);
            designation = (TextView) view.findViewById(R.id.member_designation);

        }
    }


    public OrganisationDetailAdapter(ArrayList<OrganisationPostDetail> organisationDetailList) {
        this.organisationPostDetailList = organisationDetailList;
    }

    @Override
    public OrganisationPostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_organisation_detail, parent, false);

        return new OrganisationPostHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrganisationPostHolder holder, int position) {

        holder.name.setText(organisationPostDetailList.get(position).getMemberName().toString());
        holder.designation.setText(organisationPostDetailList.get(position).getMemberDesignation().toString());

    }

    @Override
    public int getItemCount() {
        return organisationPostDetailList.size();
    }
}
