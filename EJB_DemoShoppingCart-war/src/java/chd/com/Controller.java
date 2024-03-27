/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package chd.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class Controller extends HttpServlet {

    CartSesionBeanLocal cartSesionBean = lookupCartSesionBeanLocal();
    private final String cartPage = "index.jsp";
    private final String showPage = "show.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String button = request.getParameter("btAction");
            if (button.equals("Add to Cart")) {
                HttpSession session = request.getSession();
                CartSesionBeanLocal cartSBLocal = (CartSesionBeanLocal) session.getAttribute("CART");
                if (cartSBLocal == null) {
                    cartSBLocal = lookupCartSesionBeanLocal();
                }
                String title = request.getParameter("cboBook");
                cartSBLocal.addBook(title, 1);
                session.setAttribute("CART", cartSBLocal);
                RequestDispatcher rd = request.getRequestDispatcher(cartPage);
                rd.forward(request, response);

            } else if (button.equals("view")) {
                RequestDispatcher rd = request.getRequestDispatcher(showPage);
                rd.forward(request, response);

            } else if (button.equals("add")) {
                RequestDispatcher rd = request.getRequestDispatcher(cartPage);
                rd.forward(request, response);
                
            } else if (button.equals("Remove")) {
                String[] items = request.getParameterValues("chkItem");
                if (items != null) {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        CartSesionBeanLocal cartSBLocal = (CartSesionBeanLocal) session.getAttribute("CART");
                        if (cartSBLocal != null) {
                            for (int i = 0; i < items.length; i++) {
                                cartSBLocal.removeBook(items[i]);
                            }
                            session.setAttribute("CART", cartSBLocal);
                        }
                    }
                }
                String url = "Controller?btAction=view";
                response.sendRedirect(url);
                
            } else if (button.equals("check")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    CartSesionBeanLocal cartSBLocal = (CartSesionBeanLocal) session.getAttribute("CART");
                    if (cartSBLocal != null) {
                        cartSBLocal.checkout();
                        session.removeAttribute("CART");
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher(cartPage);
                rd.forward(request, response);
            }

        } catch (Exception ex) {
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private CartSesionBeanLocal lookupCartSesionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartSesionBeanLocal) c.lookup("java:global/EJB_DemoShoppingCart/EJB_DemoShoppingCart-ejb/CartSesionBean!chd.com.CartSesionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
