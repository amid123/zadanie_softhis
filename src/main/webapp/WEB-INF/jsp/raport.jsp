
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zadanie</title>

    </head>
    <body>
        <h1>Raport</h1>
        <table>
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>

                <th>Kwota zakupów </th>
                <th>Ostatnie zamowienie </th>
                <th>Trzy najlepsze zamowienia </th>


                <c:forEach items="${raport}" var="row">
                <tr>
                    <th>${row.name}</th>
                    <th>${row.surname}</th>
                    <th>
                        <fmt:formatNumber value="${row.totalAmount}"  type="currency" currencySymbol=" "/>
                    </th>
                    <th> 
                        <fmt:formatDate type="date" value="${row.lastOrderDate}" timeZone="Poland" pattern="dd MM yyyy"/>
                    </th>

                    <th>
                        <c:forEach items="${row.bestThreeOrders}" var="order">
                            <p>  
                                <fmt:formatDate type="date" value="${order.date}" timeZone="Poland" pattern="dd MM yyyy" /> 
                                Kwota: <fmt:formatNumber value="${order.amount}"  type="currency" currencySymbol=" "/>
                            </p>
                        </c:forEach>
                    </th>

                </tr>

            </c:forEach>
        </table> 
    </body>
</html>
