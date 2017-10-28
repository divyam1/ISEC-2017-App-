package dexter.com.isec17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dexter.com.isec17.R;

/**
 * Created by dexter on 18/1/17.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private String[] head;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View background;
        public TextView title ;

        public MyViewHolder(View view) {
            super(view);
            background = (View) view.findViewById(R.id.vOverlay);
            title = (TextView) view.findViewById(R.id.tvName);
        }
    }


    public ItemAdapter(Context context) {
        mContext = context;
        head = mContext.getResources().getStringArray(R.array.menu_type) ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(head[position]);
        switch(position){
            case 0 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_1));
                break;
            case 1 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_2));
                break;
            case 2 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_3));
                break;
            case 3 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_4));
                break;
            case 4 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_5));
                break;
            case 5 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_6));
                break;
            case 6 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_7));
                break;
            case 7 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_5));
                break;
            case 8 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_2));
                break;
            case 9 : holder.background.setBackground(mContext.getResources().getDrawable(R.drawable.g_1));
                break;

        }
    }


    @Override
    public int getItemCount() {
        return head.length;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ItemAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ItemAdapter.ClickListener clickListener) {
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