package dexter.com.isec17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.model.PlannerItem;
import dexter.com.isec17.model.WorkshopDetail;

/**
 * Created by dexter on 21/1/17.
 */
public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {


    private List<PlannerItem> plannerItemList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title , time , area , type , date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.tvtitle);
            time = (TextView)view.findViewById(R.id.tvtime);
            area = (TextView)view.findViewById(R.id.tvArea);
            type = (TextView) view.findViewById(R.id.tvtype);
            date = (TextView)view.findViewById(R.id.tvDate);
        }
    }


    public PlanAdapter(List<PlannerItem> plannerItemList) {
        this.plannerItemList = plannerItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PlannerItem item = plannerItemList.get(position);
        System.out.println("ITEM : " + item);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getStartTime());
        holder.area.setText(item.getPlace());
        holder.date.setText(item.getDate());
        holder.type.setText(item.getType());
    }


    @Override
    public int getItemCount() {
        return plannerItemList.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PlanAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PlanAdapter.ClickListener clickListener) {
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

    public void update(List<PlannerItem> plannerItemList){
        this.plannerItemList = plannerItemList;
        notifyDataSetChanged();

    }
}
