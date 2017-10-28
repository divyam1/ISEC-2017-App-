package dexter.com.isec17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dexter.com.isec17.R;
import dexter.com.isec17.model.OrganisationDetail;

/**
 * Created by DU on 1/28/2017.
 */

public class OrganisationAdapter extends  RecyclerView.Adapter<OrganisationAdapter.OrganisationDetailHolder> {
    private ArrayList<OrganisationDetail> mOrgDetail;
    private static MyClickListener myClickListener;

    public static class OrganisationDetailHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView postName;

        public OrganisationDetailHolder(View itemView) {
            super(itemView);
            postName = (TextView) itemView.findViewById(R.id.organisaiton_post_name);
            //  Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public OrganisationAdapter(ArrayList<OrganisationDetail> myOrgDetail) {
        mOrgDetail = myOrgDetail;
    }

    @Override
    public OrganisationDetailHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_organisation, parent, false);

        OrganisationDetailHolder detailHolder = new OrganisationDetailHolder(view);
        return detailHolder;
    }

    @Override
    public void onBindViewHolder(OrganisationDetailHolder holder, int position) {
        holder.postName.setText(mOrgDetail.get(position).getItemName());

    }

    public void addItem(OrganisationDetail detail, int index) {
        mOrgDetail.add(index, detail);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mOrgDetail.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mOrgDetail.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private OrganisationAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final OrganisationAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
