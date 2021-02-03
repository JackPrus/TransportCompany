<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.01.2021
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.localization}"/>
<fmt:setBundle basename="pagecontext"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/leftPane.css"/>
    <title></title>
</head>
<body>

<div class="left-pane">
    <c:if test="${userRole eq 'CLIENT'}">
        <form name="userForm" method="post" action="controller?command=allOrdersForClient" accept-charset="UTF-8">
            <input type="submit" value="<fmt:message key="label.left_pane.client.all_orders"/>"/>
        </form>

        <form name="userForm" method="post" action="controller?command=gotorequest" accept-charset="UTF-8">
            <input type="submit" value="<fmt:message key="label.orderform.new_order"/>"/>
        </form>

    </c:if>


    <c:if test="${userRole eq 'MANAGER'}">
        <form name="adminForm" method="post" action="controller?command=goToNewClientPage">
            <input type="submit" value="<fmt:message key="label.left_pane.manager.add_new_client"/>"/>
        </form>
        <form name="adminForm" method="post" action="controller?command=allClients">
            <input type="submit" value="<fmt:message key="label.left_pane.manager.my_clients"/>"/>
        </form>

        <form name="adminForm" method="post" action="controller?command=goToNewTruckPage">
            <input type="submit" value="<fmt:message key="label.left_pane.manager.add_new_truck"/>"/>
        </form>

        <form name="adminForm" method="post" action="controller?command=truckOfManager">
            <input type="submit" value="<fmt:message key="label.left_pane.manager.my_trucks"/>"/>
        </form>

        </form>


    </c:if>
</div>
</body>
</html>
