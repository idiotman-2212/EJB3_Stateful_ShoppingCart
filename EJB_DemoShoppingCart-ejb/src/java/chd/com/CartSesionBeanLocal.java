/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package chd.com;

import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author DELL
 */
@Local
public interface CartSesionBeanLocal {

    void setCustomer(String customerID);

    void addBook(String title, int quantity);

    void removeBook(String title);

    void checkout();

    HashMap getCart();

}
