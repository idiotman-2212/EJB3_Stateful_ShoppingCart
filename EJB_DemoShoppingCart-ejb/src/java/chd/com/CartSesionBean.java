package chd.com;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class CartSesionBean implements CartSesionBeanLocal {
    private HashMap<String, Integer> cart;
    private String customerID;

    @Override
    public void setCustomer(String customerID) {
    }
    @PostConstruct
    public void construct() {
        this.cart = new HashMap<String, Integer>();
        this.customerID = "0000";
    }
    public void addBook(String title, int quantity) {
        Integer quan = cart.get(title);
        int curQuan = 0;
        if (quan != null) {
            curQuan = quan.intValue();
        }
        curQuan += quantity;
        cart.put(title, curQuan);
    }
    public void removeBook(String title) {
        Integer quan = cart.get(title);
        if (quan != null) {
            cart.remove(title);
        }
    }
    @Remove
    public void checkout() {
    }
    @Override
    public HashMap<String, Integer> getCart() {
        return this.cart;
    }
}
