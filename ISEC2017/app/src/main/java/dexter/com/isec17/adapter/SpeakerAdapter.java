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

import java.util.List;

import dexter.com.isec17.R;
import dexter.com.isec17.model.SpeakerItem;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dexter on 22/1/17.
 */
public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.MyViewHolder> {


    private Context mContext;
    private List<SpeakerItem> speakerItemList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbs;
        public TextView name ;

        public MyViewHolder(View view) {
            super(view);
            thumbs = (ImageView) view.findViewById(R.id.ivSpeaker);
            name = (TextView)view.findViewById(R.id.tvSpeaker);
        }
    }


    public SpeakerAdapter(Context context,List<SpeakerItem> speakerItems) {
        mContext = context;
        this.speakerItemList = speakerItems ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.speaker_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpeakerItem item = speakerItemList.get(position);
        holder.name.setText(item.getName());
        System.out.println("LINK : " + item.getImgLink());
        try{
            Glide.with(mContext).load(item.getImgLink()).placeholder(R.drawable.ic_account_circle_white_48dp).bitmapTransform(new CropCircleTransformation(mContext)).into(holder.thumbs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return speakerItemList.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SpeakerAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SpeakerAdapter.ClickListener clickListener) {
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
