package dexter.com.isec17.adapter;

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

/**
 * Created by dexter on 19/1/17.
 */
public class HotelItemAdapter extends RecyclerView.Adapter<HotelItemAdapter.MyViewHolder> {

    private ArrayList<HotelDetail> hotelDetailArrayList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,address,price,rating,contact;
        ImageView icon ;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.tvName);
            address = (TextView)view.findViewById(R.id.tvAddress);
            price = (TextView)view.findViewById(R.id.tvPrice);
            rating = (TextView)view.findViewById(R.id.tvRate);
            contact = (TextView)view.findViewById(R.id.tvcontact);
            icon = (ImageView)view.findViewById(R.id.ivHotel);
        }
    }


    public HotelItemAdapter(Context context,ArrayList<HotelDetail> hotelDetailArrayList) {
        mContext = context;
        this.hotelDetailArrayList = hotelDetailArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            HotelDetail hotelDetail = hotelDetailArrayList.get(position);
        holder.name.setText(hotelDetail.getName());
        holder.address.setText(hotelDetail.getAddress());
        holder.contact.setText(hotelDetail.getContact());
        holder.rating.setText(hotelDetail.getRating());
        holder.price.setText(hotelDetail.getPrice());
        System.out.println("LINK : " + hotelDetail.getImageLink());
        try{
            Glide.with(mContext).load(hotelDetail.getImageLink()).placeholder(R.drawable.ic_hotel_black_36dp).into(holder.icon);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return hotelDetailArrayList.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private HotelItemAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final HotelItemAdapter.ClickListener clickListener) {
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
