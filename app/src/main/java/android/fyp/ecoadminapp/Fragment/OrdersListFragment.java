package android.fyp.ecoadminapp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.fyp.ecoadminapp.Adapter.OrdersListAdapter;
import android.fyp.ecoadminapp.MainActivity;
import android.fyp.ecoadminapp.R;
import android.fyp.ecoadminapp.models.Order;
import android.fyp.ecoadminapp.models.Product;
import android.fyp.ecoadminapp.models.User;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class OrdersListFragment extends Fragment {
    ArrayList<Order> mOrderList;
    private DatabaseReference mFirebaseDatabaseOrders;
    private FirebaseDatabase mFirebaseInstance;
    OrdersListAdapter ordersListAdapter ;
    ProgressDialog progressDialog;
    ListView mlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders_list, container, false);
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabaseOrders = mFirebaseInstance.getReference("Orders");
        mlist=(ListView) view.findViewById(R.id.ordersList);
        mOrderList=new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading...");
        progressDialog.setTitle("Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order oOrder=mOrderList.get(position);
                OrderDetailFragment ofr=new OrderDetailFragment(oOrder);
                getFragmentManager().beginTransaction().replace(R.id.frlayout,ofr).addToBackStack("fr2").commit();
            }
        });
        GetAllProducts();
        return view;

    }

    //  List get
    private void GetAllProducts() {
        // User data change listener
        mFirebaseDatabaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    User oUser=snapshot.child("User").getValue(User.class);
                    int Counter=0;
                    ArrayList<Product> oListProducts=new ArrayList<>();
                    for (DataSnapshot snapshotchild:snapshot.getChildren())
                    {
                        if (snapshotchild.getKey().equals("Product"+Counter)){
                            Product oProduct = snapshotchild.getValue(Product.class);
                            oListProducts.add(oProduct);
                            Counter++;
                        }
                    }
                    Order objorder = new Order();
                    objorder.setoUser(oUser);
                    objorder.setoListProducts(oListProducts);
                    mOrderList.add(objorder);
                }
                Object dataSnapshotsChat = dataSnapshot.getValue();
                ordersListAdapter =new OrdersListAdapter(getActivity().getApplicationContext(),mOrderList);
                mlist.setAdapter(ordersListAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed", error.toException());
            }
        });
    }
}
