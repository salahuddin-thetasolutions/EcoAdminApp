package android.fyp.ecoadminapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.fyp.ecoadminapp.models.Order;
import android.fyp.ecoadminapp.models.Product;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.fyp.ecoadminapp.R;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class OrderDetailFragment extends Fragment {

Order mOrder;
    TableLayout t1;

    @SuppressLint("ValidFragment")
    public OrderDetailFragment(Order objOrder){
        mOrder=new Order();
        this.mOrder=objOrder;
    }
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
             View view= inflater.inflate(R.layout.fragment_order_detail, container, false);
             TextView mName=view.findViewById(R.id.tvoName);
        TextView mEmail=view.findViewById(R.id.tvoEmail);
        TextView mPhone=view.findViewById(R.id.tvoPhone);
        TextView mAddress=view.findViewById(R.id.tvoAddress);
        mName.setText(mOrder.getoUser().getName());
        mEmail.setText(mOrder.getoUser().getEmail());
        mPhone.setText(mOrder.getoUser().getPhone());
        mAddress.setText(mOrder.getoUser().getAddress());
        t1 = (TableLayout)view.findViewById(R.id.main_table);
        TableRow tr_head = new TableRow(getActivity());
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));


        TextView label_date = new TextView(getActivity());
        label_date.setId(20);
        label_date.setText("Item");
        label_date.setTypeface(null, Typeface.BOLD);
        label_date.setTextColor(Color.WHITE);
        label_date.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date);// add the column to the table row here

        TextView label_date2 = new TextView(getActivity());
        label_date2.setId(21);
        label_date2.setText("Category");
        label_date2.setTextColor(Color.WHITE);
        label_date2.setTypeface(null, Typeface.BOLD);

        label_date2.setPadding(5, 5, 5, 5);
        tr_head.addView(label_date2);// add the column to the table row here

        TextView label_weight_kg = new TextView(getActivity());
        label_weight_kg.setId(22);// define id that must be unique
        label_weight_kg.setText("Qty"); // set the text for the header
        label_weight_kg.setTypeface(null, Typeface.BOLD);
        label_weight_kg.setTextColor(Color.WHITE); // set the color
        label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg); // add the column to the table row here
        TextView label_weight_kg2 = new TextView(getActivity());
        label_weight_kg2.setId(23);// define id that must be unique
        label_weight_kg2.setText("Price"); // set the text for the header
        label_weight_kg2.setTextColor(Color.WHITE); // set the color
        label_weight_kg2.setTypeface(null, Typeface.BOLD);
        label_weight_kg2.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_weight_kg2); // add the column to the table row here

        t1.addView(tr_head, new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));


        Integer count=0;
        Double Total=0.0;
        for(Product item: mOrder.getoListProducts()){
            // Create the table row
            TableRow tr = new TableRow(getActivity());
            tr.setBackgroundColor(Color.LTGRAY);
            tr.setId(100+count);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

//Create two columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(getActivity());
            labelDATE.setId(200+count);
            labelDATE.setText(item.getName());
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);

            // Create a TextView to add date
            TextView labelDATE2 = new TextView(getActivity());
            labelDATE2.setId(200+count);
            labelDATE2.setText(item.getCategory());
            labelDATE2.setPadding(2, 0, 5, 0);
            labelDATE2.setTextColor(Color.WHITE);
            tr.addView(labelDATE2);

            // Create a TextView to add date
            TextView labelDATE3 = new TextView(getActivity());
            labelDATE3.setId(200+count);
            labelDATE3.setText(item.getQty()+"");
            labelDATE3.setPadding(2, 0, 5, 0);
            labelDATE3.setTextColor(Color.WHITE);
            tr.addView(labelDATE3);

            TextView labelWEIGHT = new TextView(getActivity());
            labelWEIGHT.setId(200+count);
            labelWEIGHT.setText(item.getPrice()+"");
            labelWEIGHT.setTextColor(Color.WHITE);
            tr.addView(labelWEIGHT);

// finally add this to the table row
            t1.addView(tr, new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
            count++;
            Total+=item.getPrice();
        }
        TableRow tr2 = new TableRow(getActivity());
        tr2.setBackgroundColor(Color.GRAY);
        tr2.setId(500+count);
        tr2.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.FILL_PARENT));
        TextView labelWEIGHT2 = new TextView(getActivity());
        labelWEIGHT2.setId(500+count);
        labelWEIGHT2.setText("Total");
        labelWEIGHT2.setTextColor(Color.WHITE);
        labelWEIGHT2.setTypeface(null, Typeface.BOLD);
        tr2.addView(labelWEIGHT2);
        TextView labelWEIGHT = new TextView(getActivity());
        labelWEIGHT.setId(500+count);
        labelWEIGHT.setText(Total+"");
        labelWEIGHT.setTextColor(Color.WHITE);
        labelWEIGHT.setTypeface(null, Typeface.BOLD);
        tr2.addView(labelWEIGHT);
        t1.addView(tr2, new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        return  view;
    }

}
