<%-- 
    Document   : viewcart
    Created on : Mar 25, 2024, 6:07:23 PM
    Author     : DELL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show</title>
    </head>
    <body>
        <h1>Your Shopping Cart</h1>
        <c:catch var="ee">
            <c:set var="cart" value = "${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="Controller">
                        <c:forEach var="map" items="${cart.cart}" varStatus="counter">
                            <c:set var="count" value = "$(count + 1)"/>
                            <tr>
                                <td>${counter.count}</td>
                                <td>${map.key}</td>
                                <td>${map.value}</td>
                                <td><input type="checkbox" name ="chkItem" value="${map.key}" /></td>
                            </tr>                     
                        </c:forEach>
                        <tr>
                            <c:url var="add" value="Controller">
                                <c:param name="btAction" value="add"/>
                            </c:url>
                            <td colspan="3"> <a href="${add}">Add More Cart</a></td>
                            <td><input type="submit" value="Remove" name="btAction" /></td>
                        </tr>
                    </form>
                </tbody>
            </table> <br/>
            <c:url var="check" value="Controller">
                <c:param name="btAction" value="check"/>
            </c:url>
            <a href="${check}">Check Out</a>
        </c:if>           
    </c:catch>
    <c:if test="${not empty ee}">
        Errors occur: <br/>
        <c:out value="${ee}"/>
    </c:if>
</body>
</html>
