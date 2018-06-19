package android.fyp.ecoadminapp.Adapter;

import android.content.Context;
import android.fyp.ecoadminapp.R;
import android.fyp.ecoadminapp.models.Order;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Waseem on 9/28/2017.
 */

public class OrdersListAdapter extends ArrayAdapter<Order> {
    private final Context mcontext;
    ArrayList<Order> mOrders;

    public OrdersListAdapter(Context mcontext, ArrayList<Order> oListOrder) {
        super(mcontext, R.layout.single_item_layout,oListOrder);
        this.mcontext = mcontext;
        this.mOrders = oListOrder;
    }

    @Override
    public int getPosition(@Nullable Order item) {
        return super.getPosition(item);
    }

    @Nullable
    @Override
    public Order getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Order oOrder=getItem(position);
        View mrowView=inflater.inflate(R.layout.single_item_layout,parent,false);
        TextView lessonTV=(TextView) mrowView.findViewById(R.id.tvName);
        lessonTV.setText(oOrder.getoUser().getName());
        TextView chapterTV=(TextView)mrowView.findViewById(R.id.tvPhone);
        chapterTV.setText(oOrder.getoUser().getPhone());
        TextView EmailTv=(TextView)mrowView.findViewById(R.id.tvEmail);
        EmailTv.setText(oOrder.getoUser().getEmail());
        return mrowView;
    }
}
