package dexter.com.isec17.adapter;

/**
 * Created by dexter on 20/1/17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dexter.com.isec17.R;
import dexter.com.isec17.model.HotelDetail;
import dexter.com.isec17.model.WorkshopDetail;

/**
 * Created by dexter on 19/1/17.
 */
public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<WorkshopDetail> workshopDetailArrayList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title , time , area;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.tvtitle);
            time = (TextView)view.findViewById(R.id.tvtime);
            area = (TextView)view.findViewById(R.id.tvArea);
        }
    }


    public WorkshopAdapter(Context context,ArrayList<WorkshopDetail> workshopDetailArrayList) {
        mContext = context;
        this.workshopDetailArrayList = workshopDetailArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WorkshopDetail workshopDetail = workshopDetailArrayList.get(position);
        holder.title.setText(workshopDetail.getTitle());
        holder.time.setText(workshopDetail.getStartTime() + " - "+ workshopDetail.getEndTime());
        holder.area.setText(workshopDetail.getArea());
    }


    @Override
    public int getItemCount() {
        return workshopDetailArrayList.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private WorkshopAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final WorkshopAdapter.ClickListener clickListener) {
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
