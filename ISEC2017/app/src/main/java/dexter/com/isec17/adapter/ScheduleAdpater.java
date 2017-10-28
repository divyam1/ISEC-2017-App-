package dexter.com.isec17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.model.PlannerItem;

/**
 * Created by dexter on 23/1/17.
 */
public class ScheduleAdpater extends RecyclerView.Adapter<ScheduleAdpater.MyViewHolder> {


    private List<PlannerItem> scheduleList;

    public ScheduleAdpater(List<PlannerItem> PlannerItemList) {
        this.scheduleList = PlannerItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PlannerItem item = scheduleList.get(position);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getStartTime() + "-" + item.getEndTime());
        holder.area.setText(item.getPlace());
        String string = item.getSubtitle();
        if(string.length() > 2){
            holder.subtitle.setVisibility(View.VISIBLE);
            holder.subtitle.setText(item.getSubtitle());
        }else{
            holder.subtitle.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ScheduleAdpater.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ScheduleAdpater.ClickListener clickListener) {
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, area,subtitle;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvtitle);
            time = (TextView) view.findViewById(R.id.tvtime);
            area = (TextView) view.findViewById(R.id.tvlocation);
            subtitle = (TextView) view.findViewById(R.id.tvsubtitle);
        }
    }
}
