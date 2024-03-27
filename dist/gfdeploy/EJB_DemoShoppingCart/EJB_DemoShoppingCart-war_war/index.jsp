<%-- 
    Document   : index
    Created on : Mar 25, 2024, 5:52:49 PM
    Author     : DELL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Home </title>
    </head>
    <body>
        <h1> Shopping Cart Demo</h1>
        <form action="Controller">
            Please, choose your favorite book: </br>
            <select name="cboBook" site="20">
                <option selected>Common Gateway Interface - CGI</option>
                <option>Servlet</option>
                <option>JavaServer Page - JSTL</option>
                <option>Tomecat Server</option>
                <option>Structs</option>
                <option>JavaServer Faces - JSF</option>
                <option>Interacting Java with XML - IXJ</option>
                <option>Java Web Service - JWS</option>
                <option>Enterprise Java Beans - EJB</option>
                <option>GlassFish Server</option>
                <option>JBoss Server</option>
            </select></br>
            <input type="submit" value="Add to Cart" name="btAction"/>
            
        </form>
        <c:url var="view" value="Controller">
            <c:param name="btAction" value="view"/>
        </c:url>
        <a href="${view}">View Cart</a>
    </body>
</html>