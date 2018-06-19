package android.fyp.ecoadminapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    public User getoUser() {
        return oUser;
    }

    public void setoUser(User oUser) {
        this.oUser = oUser;
    }

    public ArrayList<Product> getoListProducts() {
        return oListProducts;
    }

    public void setoListProducts(ArrayList<Product> oListProducts) {
        this.oListProducts = oListProducts;
    }

    private User oUser;
    private ArrayList<Product> oListProducts;
}
